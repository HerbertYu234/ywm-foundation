package ywm.blog.term;


import ywm.library.shared.model.ArticleType;
import ywm.library.shared.term.SearchTerm;

import java.util.List;

/**
 * Created by Herbert Yu on 2019-11-13 09:40
 */
public class ArticleTerm implements SearchTerm {

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 用户
     */
    private String user;

    /**
     * 类别
     */
    private ArticleType type;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 是否置顶
     */
    private Boolean top;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
