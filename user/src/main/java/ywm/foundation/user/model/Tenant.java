package ywm.foundation.user.model;

import com.wolf.mongo.model.MongoEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Herbert Yu on 2019-08-15 17:29
 */
@Document(collection = "tenant")
public class Tenant extends MongoEntity {

    private String name;

    /**
     * 唯一code
     */
    @Indexed(unique = true)
    private String code;

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
}
