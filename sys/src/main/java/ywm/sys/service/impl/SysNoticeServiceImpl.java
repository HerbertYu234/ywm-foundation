package ywm.sys.service.impl;

import com.wolf.core.support.WolfPageImpl;
import com.wolf.mongo.WolfMongoOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ywm.library.shared.utils.Bool;
import ywm.sys.model.SysNotice;
import ywm.sys.repository.SysNoticeRepository;
import ywm.sys.service.SysNoticeService;
import ywm.sys.term.SysNoticeTerm;

import java.util.List;

/**
 * Created by Herbert Yu on 2019-12-17 20:09
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    private Logger logger = LoggerFactory.getLogger(SysNoticeServiceImpl.class);

    @Autowired
    private SysNoticeRepository sysNoticeRepository;

    @Autowired
    private WolfMongoOperations wolfMongoOperations;

    @Override
    public SysNotice save(SysNotice sysNotice) {
        SysNotice save = sysNoticeRepository.save(sysNotice);
        logger.info("保存SysNotice 数据成功！[{}]", save);
        return save;
    }

    @Override
    public boolean remove(String[] ids) {
        for (String id : ids) {
            try {
                sysNoticeRepository.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public long count(SysNoticeTerm term) {
        return wolfMongoOperations.count(mongoQuery(term), SysNotice.class);
    }

    @Override
    public SysNotice findById(String id) {
        SysNotice sysNotice = sysNoticeRepository.findOne(id);
        return sysNotice;
    }

    @Override
    public Page<SysNotice> search(SysNoticeTerm term, Pageable pageable) {
        Query query = mongoQuery(term);
        long count = count(term);

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
        List<SysNotice> notices = wolfMongoOperations.find(query.with(sort), SysNotice.class);
        Page<SysNotice> page = new WolfPageImpl<>(notices, pageable, count);
        return page;
    }


    private Query mongoQuery(SysNoticeTerm term) {
        Criteria criteria = new Criteria();
        Bool.with(term)
                .whenNotBlank(t -> t.getUserId()).andThen(t -> criteria.and("userId").is(t.getUserId()))
                .whenNonNull(t -> t.getStatus()).andThen(t -> criteria.and("status").is(t.getStatus()))
                .whenNonNull(t -> t.getPlayable()).andThen(t -> criteria.and("playable").is(t.getPlayable())
        );
        Query query = Query.query(criteria);
        return query;
    }
}
