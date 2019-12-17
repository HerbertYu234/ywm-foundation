package ywm.blog.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ywm.foundation.lib.support.Entity;

import java.util.Date;

@Data
@Document(collection = "article.look")
public class ArticleLook extends Entity.Mongo {

    private String articleId;
    private String userId;
    private String userIp;
    private Date lookTime;

}

