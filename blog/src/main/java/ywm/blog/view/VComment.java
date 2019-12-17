package ywm.blog.view;

import lombok.Data;
import ywm.blog.model.ArticleComment;

import java.util.List;

/**
 * Created by Herbert Yu on 2019-12-16 17:06
 */
@Data
public class VComment extends ArticleComment {

    public VComment(ArticleComment comment) {
        if (null != comment) {
            setId(comment.getId());
            setCreateTime(comment.getCreateTime());
            setUpdateTime(comment.getUpdateTime());
            setUser(comment.getUser());
            setContent(comment.getContent());
            setComment(comment.getComment());
            setArticle(comment.getArticle());
            setImages(comment.getImages());
        }
    }

    private String username;

    private String responseToUsername;

    private List<VComment> comments;
}
