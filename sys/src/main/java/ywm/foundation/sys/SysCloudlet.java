package ywm.foundation.sys;

import com.wolf.Wolf;
import com.wolf.cloud.WolfCloudlet;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Herbert Yu on 2019-12-17 20:10
 */
@Wolf.BootApplication(name = "sys")
@EnableFeignClients
@RestController
public class SysCloudlet implements WolfCloudlet {

    public static void main(String[] args) {
        Wolf.run(SysCloudlet.class, args);
    }
}
