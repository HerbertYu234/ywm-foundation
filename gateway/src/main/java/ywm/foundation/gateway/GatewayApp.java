package ywm.foundation.gateway;

import com.wolf.Wolf;
import com.wolf.cloud.WolfCloudlet;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Herbert Yu on 2019-08-24 18:23
 */
@Wolf.BootApplication(name = "gateway")
@EnableFeignClients
@EnableZuulProxy
public class GatewayApp implements WolfCloudlet {

    public static void main(String[] args) {
        Wolf.run(GatewayApp.class, args);
    }
}
