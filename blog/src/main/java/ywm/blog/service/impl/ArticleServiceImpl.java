package ywm.blog.service.impl;

import com.wolf.core.support.WolfPageImpl;
import com.wolf.mongo.WolfMongoOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ywm.blog.Transformer;
import ywm.blog.model.Article;
import ywm.blog.repository.ArticleRepository;
import ywm.blog.service.ArticleService;
import ywm.blog.term.ArticleTerm;
import ywm.blog.view.VArticle;
import ywm.library.shared.utils.Bool;

import java.util.List;

/**
 * Created by Herbert Yu on 2019-11-12 22:03
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private WolfMongoOperations wolfMongoOperations;

    @Autowired
    private Transformer transformer;

    @Override
    public Article save(Article article) {
        article.setUpdateTime(System.currentTimeMillis());
        Article save = articleRepository.save(article);
        logger.info("保存Article 数据成功！[{}]", save);
        return save;
    }

    @Override
    public boolean remove(String id) {
        articleRepository.delete(id);
        return true;
    }

    @Override
    public Page<VArticle> search(ArticleTerm term, Pageable pageable) {
        Query query = mongoQuery(term);
        long count = count(term);

        if (pageable.getPageNumber() <= 0) {
            query.limit(pageable.getPageSize());
        } else {
            int skip = pageable.getPageSize() * pageable.getPageNumber();
            query.skip(skip).limit(pageable.getPageSize());
        }

        Sort sort = pageable.getSort();
        if (sort == null) {
            sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
        }
        List<Article> articles = wolfMongoOperations.find(query.with(sort), Article.class);
        Page<Article> page = new WolfPageImpl<>(articles, pageable, count);
        return ((WolfPageImpl<Article>) page).map(article->transformer.vArticle(article));
    }

    @Override
    public long count(ArticleTerm term) {
        return wolfMongoOperations.count(mongoQuery(term), Article.class);
    }

    @Override
    public Article findById(String id) {
        return articleRepository.findOne(id);
    }

    @Override
    public boolean remove(String[] ids) {
        for (String id : ids) {
            try {
                remove(id);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean changeStatus(Integer status, String[] ids) {
        for (String id : ids) {
            try {
                Article article = findById(id);
                if(null!=article){
                    article.setStatus(status);
                    save(article);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public VArticle detail(String id) {
        return transformer.vArticle(this.findById(id));
    }

    private Query mongoQuery(ArticleTerm term) {
        Criteria criteria = new Criteria();
        Bool.with(term)
                .whenNotBlank(t -> t.getAuthor()).andThen(t -> criteria.and("author").is(t.getAuthor()))
                .whenNotBlank(t -> t.getUser()).andThen(t -> criteria.and("user").is(t.getUser()))
                .whenNotBlank(t -> t.getTitle()).andThen(t -> criteria.and("title").regex(t.getTitle()))
                .whenNonNull(t -> t.getType()).andThen(t -> criteria.and("type").is(t.getType()))
                .whenNonNull(t -> t.getTop()).andThen(t -> criteria.and("top").is(t.getTop()))
                .whenCollsNotEmpty(t -> t.getTags()).andThen(t -> criteria.and("tags").in(t.getAuthor()))
                .whenNotBlank(t-> t.getKeyword()).andThen(t ->
                    criteria.orOperator(
                        Criteria.where("title").regex(t.getKeyword()),
                        Criteria.where("author").regex(t.getKeyword())
                    )
                );

        Query query = Query.query(criteria);
        return query;
    }
}
