package ywm.foundation.sys.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ywm.foundation.lib.support.Entity;

/**
 * Created by Herbert Yu on 2019-12-17 13:20
 * 通知公告
 */
@Data
@Document(collection = "sysnotice")
public class SysNotice extends Entity.Mongo{

    /**
     * 创建人
     */
    private String userId;

    /**
     * 状态
     * 1 启用
     * -1 禁用
     */
    private int status = 1;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 轮播展示
     */
    private Boolean playable;
}
