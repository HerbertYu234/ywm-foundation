package ywm.blog.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ywm.foundation.lib.support.Entity;

/**
 * Created by Herbert Yu on 2019-11-23 21:16
 */
@Data
@Document(collection = "article.tag")
public class ArticleTag extends Entity.Mongo {

    private String name;

    private String desc;
}
