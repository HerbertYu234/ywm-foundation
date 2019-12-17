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
import ywm.blog.model.ArticleComment;
import ywm.blog.repository.CommentRepository;
import ywm.blog.service.ArticleCommentService;
import ywm.blog.term.ArticleCommentTerm;
import ywm.blog.view.VComment;
import ywm.library.shared.utils.Bool;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Herbert Yu on 2019-11-12 22:06
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    private Logger logger = LoggerFactory.getLogger(ArticleCommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private WolfMongoOperations wolfMongoOperations;


    @Override
    public ArticleComment findById(String id) {
        return commentRepository.findOne(id);
    }

    @Override
    public ArticleComment save(ArticleComment comment) {
        comment.setUpdateTime(System.currentTimeMillis());
        ArticleComment save = commentRepository.save(comment);
        logger.info("保存Comment 数据成功！[{}]", save);
        return save;
    }

    @Override
    public boolean remove(String id) {
        commentRepository.delete(id);
        return true;
    }

    @Override
    public long count(ArticleCommentTerm term) {
        return commentRepository.count();
    }


    private List<VComment> findByParent(String parent, ArticleCommentTerm term) {
        if (null == term) {
            term = new ArticleCommentTerm();
        }
        term.setComment(parent);
        Query query = mongoQuery(term);
        List<VComment> comments = wolfMongoOperations.find(query, VComment.class);
        return comments;
    }

    private List<VComment> recursion(List<VComment> comments, ArticleCommentTerm term) {
        if (null != comments) {
            return comments.stream().map(vComment -> {
                List<VComment> sons = findByParent(vComment.getId(), term);
                if (null != sons) {
                    sons = recursion(sons, term);
                }
                vComment.setComments(sons);
                return vComment;
            }).collect(Collectors.toList());
        }
        return null;
    }


    @Override
    public Page<VComment> search(ArticleCommentTerm term, Pageable pageable) {
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
        List<VComment> comments = recursion(wolfMongoOperations.find(query.with(sort), VComment.class), term);
        Page<VComment> page = new WolfPageImpl<>(comments, pageable, count);
        return page;
    }

    private Query mongoQuery(ArticleCommentTerm term) {
        Criteria criteria = new Criteria();
        Bool.with(term)
                .whenNotBlank(t -> t.getArticle()).andThen(t -> criteria.and("article").is(t.getArticle()))
                .whenNotBlank(t -> t.getKeyword()).andThen(t ->
                criteria.orOperator(
                        Criteria.where("content").regex(t.getKeyword())
                )
        );

        Query query = Query.query(criteria);
        return query;
    }
}
