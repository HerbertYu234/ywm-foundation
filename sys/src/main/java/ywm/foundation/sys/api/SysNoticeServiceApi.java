package ywm.foundation.sys.api;

import com.wolf.lang.helper.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ywm.foundation.sys.model.SysNotice;
import ywm.foundation.sys.service.SysNoticeService;
import ywm.foundation.sys.term.SysNoticeTerm;

/**
 * Created by Herbert Yu on 2019-12-17 20:21
 */
@Api(tags = "SysCloudlet SysNotice API")
@RestController
@RequestMapping("/notice")
public class SysNoticeServiceApi {

    @Autowired
    private SysNoticeService sysNoticeService;


    @ApiOperation("保存")
    @PostMapping("/save")
    public SysNotice save(@RequestBody SysNotice sysNotice) {
        if(Strings.isNotBlank(sysNotice.getId())){
            SysNotice db = sysNoticeService.findById(sysNotice.getId());
            db.setTitle(sysNotice.getTitle());
            db.setContent(sysNotice.getContent());
            db.setStatus(sysNotice.getStatus());
            db.setPlayable(sysNotice.getPlayable());
            sysNotice = db;
        }
        SysNotice save = sysNoticeService.save(sysNotice);
        return save;
    }

    @ApiOperation("删除")
    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        return sysNoticeService.remove(ids);
    }

    @ApiOperation("详情")
    @GetMapping("/detail/{id}")
    public SysNotice detail(@PathVariable String id) {
        return sysNoticeService.findById(id);
    }

    @ApiOperation("检索")
    @GetMapping("/search")
    public Page<SysNotice> search(SysNoticeTerm term, Pageable pageable) {
        Page<SysNotice> page = sysNoticeService.search(term, pageable);
        return page;
    }


}
