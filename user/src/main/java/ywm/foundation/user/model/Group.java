package ywm.foundation.user.model;

import com.wolf.mongo.model.MongoEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Herbert Yu on 2019-08-15 17:05
 * 用户组 （组织/单位/公司...）
 */
@Document(collection = "group")
public class Group extends MongoEntity {

    /**
     * 租户
     */
    @Indexed
    private String tenant;

    /**
     * 名称
     */
    private String name;

    /**
     * 唯一编码
     */
    @Indexed(unique = true)
    private String code;

    private boolean hasChildren;

    private boolean hasParents;

    /**
     * 父 code
     */
    private String parent;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isHasParents() {
        return hasParents;
    }

    public void setHasParents(boolean hasParents) {
        this.hasParents = hasParents;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
