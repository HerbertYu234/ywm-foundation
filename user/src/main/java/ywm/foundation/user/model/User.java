package ywm.foundation.user.model;

import com.wolf.lang.Landholder;
import com.wolf.mongo.model.MongoEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;

/**
 * Created by Herbert Yu on 2019-08-14 17:33
 */
@Document(collection = "user")
public class User extends MongoEntity implements Landholder {


    /**
     * 租户
     */
    private String tenant;

    /**
     * 用户组
     */
    private LinkedHashSet<String> groups = new LinkedHashSet();

    /**
     * 角色
     */
    private LinkedHashSet<String> roles = new LinkedHashSet();

    /**
     * 账号类型
     */
    private int accountType = AccountType.DEFAULT.getCode();

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 居住地址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信
     */
    private String wechat;

    /**
     * qq
     */
    private String qq;

    /**
     * 历史登录ip
     * 最近登录的 放置在最前面
     */
    private LinkedHashSet<String> ips;

    @Override
    public String getPrincipal() {
        return getUsername();
    }

    @Override
    public String getCredential() {
        return getPassword();
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public LinkedHashSet<String> getRoles() {
        return roles;
    }

    public void setRoles(LinkedHashSet<String> roles) {
        this.roles = roles;
    }

    public LinkedHashSet<String> getGroups() {
        return groups;
    }

    public void setGroups(LinkedHashSet<String> groups) {
        this.groups = groups;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public LinkedHashSet<String> getIps() {
        return ips;
    }

    public void setIps(LinkedHashSet<String> ips) {
        this.ips = ips;
    }
}
