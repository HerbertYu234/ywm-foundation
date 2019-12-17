package ywm.sys;

import com.wolf.Wolf;
import com.wolf.cloud.WolfCloudlet;
import io.swagger.annotations.Api;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Herbert Yu on 2019-12-17 20:10
 */
@Wolf.BootApplication(name = "sys")
@EnableFeignClients
@RestController
@Api(tags = "SysCloudlet API")
public class SysCloudlet implements WolfCloudlet {

    public static void main(String[] args) {
        Wolf.run(SysCloudlet.class, args);
    }
}
