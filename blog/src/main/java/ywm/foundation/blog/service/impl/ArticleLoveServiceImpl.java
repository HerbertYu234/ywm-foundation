package ywm.foundation.blog.service.impl;

import com.wolf.mongo.WolfMongoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ywm.foundation.blog.model.ArticleLove;
import ywm.foundation.blog.repository.ArticleLoveRepository;
import ywm.foundation.blog.service.ArticleLoveService;
import ywm.foundation.blog.term.ArticleLoveTerm;
import ywm.library.shared.utils.Bool;

/**
 * Created by Herbert Yu on 2019-12-16 18:04
 */
@Service
public class ArticleLoveServiceImpl implements ArticleLoveService {

    @Autowired
    private ArticleLoveRepository articleLoveRepository;

    @Autowired
    private WolfMongoOperations wolfMongoOperations;


    @Override
    public ArticleLove save(ArticleLove articleLove) {
        return articleLoveRepository.save(articleLove);
    }

    @Override
    public boolean remove(String id) {
        articleLoveRepository.delete(id);
        return true;
    }

    @Override
    public long count(ArticleLoveTerm term) {
        return wolfMongoOperations.count(mongoQuery(term), ArticleLove.class);
    }

    private Query mongoQuery(ArticleLoveTerm term) {
        Criteria criteria = new Criteria();
        Bool.with(term)
                .whenNotBlank(t -> t.getUserId()).andThen(t -> criteria.and("userId").is(t.getUserId()))
                .whenNotBlank(t -> t.getArticleId()).andThen(t -> criteria.and("articleId").is(t.getArticleId()));

        Query query = Query.query(criteria);
        return query;
    }
}
