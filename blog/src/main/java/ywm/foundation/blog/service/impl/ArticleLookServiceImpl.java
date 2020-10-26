package ywm.foundation.blog.service.impl;

import com.wolf.mongo.WolfMongoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ywm.foundation.blog.model.ArticleLook;
import ywm.foundation.blog.repository.ArticleLookRepository;
import ywm.foundation.blog.service.ArticleLookService;
import ywm.foundation.blog.term.ArticleLookTerm;
import ywm.library.shared.utils.Bool;

/**
 * Created by Herbert Yu on 2019-12-16 18:04
 */
@Service
public class ArticleLookServiceImpl implements ArticleLookService {

    @Autowired
    private ArticleLookRepository articleLookRepository;

    @Autowired
    private WolfMongoOperations wolfMongoOperations;


    @Override
    public ArticleLook save(ArticleLook articleLook) {
        return articleLookRepository.save(articleLook);
    }

    @Override
    public boolean remove(String id) {
        articleLookRepository.delete(id);
        return true;
    }

    @Override
    public long count(ArticleLookTerm term) {
        return wolfMongoOperations.count(mongoQuery(term), ArticleLook.class);
    }


    private Query mongoQuery(ArticleLookTerm term) {
        Criteria criteria = new Criteria();
        Bool.with(term)
                .whenNotBlank(t -> t.getUserId()).andThen(t -> criteria.and("userId").is(t.getUserId()))
                .whenNotBlank(t -> t.getArticleId()).andThen(t -> criteria.and("articleId").is(t.getArticleId()));

        Query query = Query.query(criteria);
        return query;
    }
}
