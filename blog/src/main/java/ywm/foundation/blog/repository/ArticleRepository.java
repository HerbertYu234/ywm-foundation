package ywm.foundation.blog.repository;

import com.wolf.mongo.repository.WolfMongoRepository;
import ywm.foundation.blog.model.Article;

/**
 * Created by Herbert Yu on 2019-11-12 22:03
 */
public interface ArticleRepository extends WolfMongoRepository<Article, String> {
}
