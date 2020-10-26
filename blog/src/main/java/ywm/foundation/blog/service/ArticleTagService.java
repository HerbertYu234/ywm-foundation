package ywm.foundation.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ywm.foundation.blog.model.ArticleTag;

import java.util.Collection;
import java.util.List;

/**
 * Created by Herbert Yu on 2019-11-23 21:20
 */
public interface ArticleTagService {

    ArticleTag findById(String id);

    List<ArticleTag> findByIds(Collection<String> ids);

    ArticleTag save(ArticleTag tag);

    boolean remove(String id);

    long count();

    Page<ArticleTag> page(Pageable pageable);

    boolean remove(String[] ids);
}
