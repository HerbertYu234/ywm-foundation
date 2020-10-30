package ywm.foundation.gateway.support;

import ywm.library.shared.security.SecurityUser;

/**
 * Created by Herbert Yu on 2020-10-26 15:59
 */
public interface SecurityManager {

    /**
     * 登录
     */
    SecurityUser login(String username, String password, boolean rememberMe);

    /**
     * 根据ticket获取用户对象
     *
     * @param ticket ticket
     * @return User
     */
    SecurityUser getUser(String ticket);

    /**
     * 退出
     */
    void logout(String ticket);

    /**
     * ticket是否登录
     */
    boolean validate(String ticket);
}
