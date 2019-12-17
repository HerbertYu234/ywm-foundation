package ywm.blog.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ywm.foundation.lib.support.Entity;
import ywm.library.shared.model.ArticleStatus;
import ywm.library.shared.model.ArticleType;
import ywm.library.shared.model.EidtorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herbert Yu on 2019-11-12 16:33
 */
@Data
@Document(collection = "article")
public class Article extends Entity.Mongo {

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 用户
     */
    private String user;

    /**
     * 内容
     */
    private String content;

    /**
     * 类别
     */
    private ArticleType type;

    /**
     * 状态
     */
    private Integer status = ArticleStatus.DEPLOY.getCode();

    /**
     * 标签
     */
    private List<String> tags = new ArrayList<>();

    /**
     * 是否置顶
     */
    private Boolean top = false;

    /**
     * 开启评论
     */
    private Boolean commentAllowed = true;

    /**
     * 综合排序
     */
    private double order;

    /**
     * 编辑器
     */
    private EidtorType editorType = EidtorType.WANGEDITOR;
}
