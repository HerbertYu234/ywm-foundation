package ywm.foundation.blog.service;

import ywm.foundation.blog.model.ArticleLove;
import ywm.foundation.blog.term.ArticleLoveTerm;

/**
 * Created by Herbert Yu on 2019-12-16 18:03
 */
public interface ArticleLoveService {

    ArticleLove save(ArticleLove articleLove);

    boolean remove(String id);

    long count(ArticleLoveTerm term);
}
