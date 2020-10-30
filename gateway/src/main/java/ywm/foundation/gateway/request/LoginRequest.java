package ywm.foundation.gateway.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


@Data
public class LoginRequest implements Serializable {

    @NotEmpty(message = "必须输入用户名")
    private String username;

    @NotEmpty(message = "必须输入密码")
    private String password;

    private boolean remember;

    @NotEmpty(message = "必须提供跳转页面")
    private String refer;

    /**
     * 当前访问的应用
     */
    private String target;
}