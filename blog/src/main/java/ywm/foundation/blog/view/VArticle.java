package ywm.foundation.blog.view;

import lombok.Data;
import ywm.foundation.blog.model.Article;
import ywm.foundation.blog.model.ArticleTag;
import ywm.library.shared.model.ArticleStatus;

import java.util.List;

/**
 * Created by Herbert Yu on 2019-12-14 18:16
 */
@Data
public class VArticle extends Article {

    public VArticle(Article article) {
        if (null != article) {
            setId(article.getId());
            setCreateTime(article.getCreateTime());
            setUpdateTime(article.getUpdateTime());
            setUser(article.getUser());
            setAuthor(article.getAuthor());
            setTitle(article.getTitle());
            setContent(article.getContent());
            setType(article.getType());
            setTypeDesc(null!=article.getType() ? article.getType().getDesc() : null);
            setStatus(article.getStatus());
            ArticleStatus articleStatus = ArticleStatus.fromCode(article.getStatus());
            if (null != articleStatus) {
                setStatusName(articleStatus.getDesc());
            }
            setTags(article.getTags());
            setTop(article.getTop());
            setCommentAllowed(article.getCommentAllowed());
            setOrder(article.getOrder());
            setEditorType(article.getEditorType());
        }
    }

    /**
     * 评论数
     */
    private long commitNum;

    /**
     * 阅读数
     */
    private long lookNum;

    /**
     * 喜欢/点赞 数
     */
    private long loveNum;

    /**
     * 标签
     */
    private List<ArticleTag> tagDetail;

    /**
     * 状态描述
     */
    private String statusName;

    /**
     * 类型描述
     */
    private String typeDesc;

}
