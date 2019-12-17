package ywm.blog.service.impl;

import com.wolf.core.support.WolfPageImpl;
import com.wolf.lang.helper.Strings;
import com.wolf.mongo.WolfMongoOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ywm.blog.model.ArticleTag;
import ywm.blog.repository.ArticleTagRepository;
import ywm.blog.service.ArticleTagService;
import ywm.library.shared.support.Uuid;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Herbert Yu on 2019-11-23 21:20
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    private Logger logger = LoggerFactory.getLogger(ArticleTagServiceImpl.class);

    @Autowired
    private ArticleTagRepository articleTagRepository;

    @Autowired
    private WolfMongoOperations wolfMongoOperations;

    @Override
    public ArticleTag findById(String id) {
        return articleTagRepository.findOne(id);
    }

    @Override
    public List<ArticleTag> findByIds(Collection<String> ids) {
        if (null != ids) {
            return ids.stream().map(id -> findById(id)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public ArticleTag save(ArticleTag tag) {
        if (Strings.isBlank(tag.getId())) {
            String id = Uuid.ArticleTag.generate();
            if (findById(id) != null) {
                return save(tag);
            }
            tag.setId(id);
        }
        tag.setUpdateTime(System.currentTimeMillis());
        ArticleTag save = articleTagRepository.save(tag);
        logger.info("保存ArticleTag 数据成功！[{}]", save);
        return save;
    }

    @Override
    public boolean remove(String id) {
        articleTagRepository.delete(id);
        return true;
    }

    @Override
    public long count() {
        return articleTagRepository.count();
    }

    @Override
    public Page<ArticleTag> page(Pageable pageable) {
        Query query = new Query();

        if (pageable.getPageNumber() <= 0) {
            query.limit(pageable.getPageSize());
        } else {
            int skip = pageable.getPageSize() * pageable.getPageNumber();
            query.skip(skip).limit(pageable.getPageSize());
        }

        Sort sort = pageable.getSort();
        if (sort == null) {
            sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
        }
        List<ArticleTag> tags = wolfMongoOperations.find(query.with(sort), ArticleTag.class);
        Page<ArticleTag> page = new WolfPageImpl<>(tags, pageable, count());

        return page;
    }

    @Override
    public boolean remove(String[] ids) {
        for (String id : ids) {
            try {
                remove(id);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
