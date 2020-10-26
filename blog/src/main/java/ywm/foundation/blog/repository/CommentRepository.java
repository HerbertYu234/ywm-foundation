package ywm.foundation.blog.repository;

import com.wolf.mongo.repository.WolfMongoRepository;
import ywm.foundation.blog.model.ArticleComment;

/**
 * Created by Herbert Yu on 2019-11-12 22:05
 */
public interface CommentRepository extends WolfMongoRepository<ArticleComment, String> {
}
