package ywm.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ywm.blog.model.ArticleComment;
import ywm.blog.term.ArticleCommentTerm;
import ywm.blog.view.VComment;

/**
 * Created by Herbert Yu on 2019-11-12 22:06
 */
public interface ArticleCommentService {

    ArticleComment findById(String id);

    ArticleComment save(ArticleComment comment);

    boolean remove(String id);

    long count(ArticleCommentTerm term);

    Page<VComment> search(ArticleCommentTerm term, Pageable pageable);
}
