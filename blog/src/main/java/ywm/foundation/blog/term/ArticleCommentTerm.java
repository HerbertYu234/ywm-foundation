package ywm.foundation.blog.term;

import ywm.library.shared.term.SearchTerm;

/**
 * Created by Herbert Yu on 2019-12-16 16:57
 */
public class ArticleCommentTerm implements SearchTerm {

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 目标id
     */
    private String article;

    /**
     * 父级 评论id
     */
    private String comment;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
