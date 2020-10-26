package ywm.foundation.blog.service;

import ywm.foundation.blog.model.ArticleLook;
import ywm.foundation.blog.term.ArticleLookTerm;

/**
 * Created by Herbert Yu on 2019-12-16 18:03
 */
public interface ArticleLookService {

    ArticleLook save(ArticleLook articleLook);

    boolean remove(String id);

    long count(ArticleLookTerm term);
}
