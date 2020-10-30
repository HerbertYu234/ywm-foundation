package ywm.foundation.gateway;

import com.wolf.Wolf;
import com.wolf.cloud.WolfCloudlet;
import com.wolf.web.config.WolfWebConfigurerAdapter;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import ywm.foundation.user.service.UserService;
import ywm.foundation.user.service.impl.UserServiceImpl;

/**
 * Created by Herbert Yu on 2019-08-24 18:23
 */
@Wolf.BootApplication(name = "gateway")
@EnableFeignClients
@EnableZuulProxy
public class GatewayApp extends WolfWebConfigurerAdapter implements WolfCloudlet {

    public static void main(String[] args) {
        Wolf.run(GatewayApp.class, args);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseRegisteredSuffixPatternMatch(true); //将http://www/ww.xx的链接同http://www/ww等价，配置完后会在匹配servlet时将.xx自动忽略
        /**
         * 是否包含.*来映射请求
         *      * 假设RequestMapping注解中指定的路径是/test
         *      * 如果设置成True，那么对于/test.do,/test.a等任何包含.的请求都会映射到/test上去；
         *      * 如果设置成False，那么对于这种请求不会进行映射。
         */
        configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }
}
