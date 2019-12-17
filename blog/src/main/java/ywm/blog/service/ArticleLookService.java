package ywm.blog.service;

import ywm.blog.model.ArticleLook;
import ywm.blog.term.ArticleLookTerm;

/**
 * Created by Herbert Yu on 2019-12-16 18:03
 */
public interface ArticleLookService {

    ArticleLook save(ArticleLook articleLook);

    boolean remove(String id);

    long count(ArticleLookTerm term);
}
