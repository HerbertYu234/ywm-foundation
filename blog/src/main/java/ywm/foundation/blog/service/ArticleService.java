package ywm.foundation.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ywm.foundation.blog.model.Article;
import ywm.foundation.blog.term.ArticleTerm;
import ywm.foundation.blog.view.VArticle;

/**
 * Created by Herbert Yu on 2019-11-12 22:03
 */
public interface ArticleService {


    Article save(Article article);

    boolean remove(String id);

    Page<VArticle> search(ArticleTerm term, Pageable pageable);

    long count(ArticleTerm term);

    Article findById(String id);

    boolean remove(String[] ids);

    boolean changeStatus(Integer status, String[] ids);

    VArticle detail(String id);
}
