package ywm.blog.repository;

import com.wolf.mongo.repository.WolfMongoRepository;
import ywm.blog.model.ArticleComment;

/**
 * Created by Herbert Yu on 2019-11-12 22:05
 */
public interface CommentRepository extends WolfMongoRepository<ArticleComment, String> {
}
