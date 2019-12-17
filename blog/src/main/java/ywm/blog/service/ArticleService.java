package ywm.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ywm.blog.model.Article;
import ywm.blog.term.ArticleTerm;
import ywm.blog.view.VArticle;

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
