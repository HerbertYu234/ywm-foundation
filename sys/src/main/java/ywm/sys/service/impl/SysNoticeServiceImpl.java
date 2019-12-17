package ywm.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ywm.sys.repository.SysNoticeRepository;
import ywm.sys.service.SysNoticeService;

/**
 * Created by Herbert Yu on 2019-12-17 20:09
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    private SysNoticeRepository sysNoticeRepository;
}
