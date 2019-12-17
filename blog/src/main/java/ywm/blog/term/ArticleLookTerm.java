package ywm.blog.term;

import ywm.library.shared.term.SearchTerm;

/**
 * Created by Herbert Yu on 2019-12-16 18:10
 */
public class ArticleLookTerm implements SearchTerm {

    private String userId;

    private String articleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
