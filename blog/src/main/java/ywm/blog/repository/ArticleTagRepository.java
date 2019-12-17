package ywm.blog.repository;

import com.wolf.mongo.repository.WolfMongoRepository;
import ywm.blog.model.ArticleTag;

/**
 * Created by Herbert Yu on 2019-11-12 22:03
 */
public interface ArticleTagRepository extends WolfMongoRepository<ArticleTag, String> {
}
