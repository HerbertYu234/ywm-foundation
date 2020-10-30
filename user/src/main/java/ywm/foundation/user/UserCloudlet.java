package ywm.foundation.user;

import com.wolf.Wolf;
import com.wolf.cloud.WolfCloudlet;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Herbert Yu on 2019-11-17 18:00
 */
@Wolf.BootApplication(name = "user")
@EnableFeignClients
@RestController
@Api(tags = "UserCloudlet API")
public class UserCloudlet implements WolfCloudlet {

    public static void main(String[] args) {
        Wolf.run(UserCloudlet.class, args);
    }

}
