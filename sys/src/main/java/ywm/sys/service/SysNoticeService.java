package ywm.sys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ywm.sys.model.SysNotice;
import ywm.sys.term.SysNoticeTerm;

/**
 * Created by Herbert Yu on 2019-12-17 20:09
 */
public interface SysNoticeService {
    SysNotice save(SysNotice sysNotice);

    boolean remove(String[] ids);

    SysNotice findById(String id);

    Page<SysNotice> search(SysNoticeTerm term, Pageable pageable);
}
