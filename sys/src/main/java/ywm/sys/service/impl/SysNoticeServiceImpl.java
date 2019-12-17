package ywm.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ywm.sys.model.SysNotice;
import ywm.sys.repository.SysNoticeRepository;
import ywm.sys.service.SysNoticeService;
import ywm.sys.term.SysNoticeTerm;

/**
 * Created by Herbert Yu on 2019-12-17 20:09
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    private SysNoticeRepository sysNoticeRepository;

    @Override
    public SysNotice save(SysNotice sysNotice) {
        SysNotice save = sysNoticeRepository.save(sysNotice);
        return save;
    }

    @Override
    public boolean remove(String[] ids) {
        return false;
    }

    @Override
    public SysNotice findById(String id) {
        SysNotice sysNotice = sysNoticeRepository.findOne(id);
        return sysNotice;
    }

    @Override
    public Page<SysNotice> search(SysNoticeTerm term, Pageable pageable) {
        return null;
    }
}
