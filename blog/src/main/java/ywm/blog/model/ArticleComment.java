package ywm.blog.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ywm.foundation.lib.support.Entity;

import java.util.List;

/**
 * Created by Herbert Yu on 2019-11-12 18:22
 * 评论、留言
 */
@Data
@Document(collection = "article.comment")
public class ArticleComment extends Entity.Mongo {

    /**
     * 目标id
     */
    private String article;

    /**
     * 父级 评论id
     */
    private String comment;

    /**
     * 用户
     */
    private String user;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 图片
     */
    private List<String> images;

}
