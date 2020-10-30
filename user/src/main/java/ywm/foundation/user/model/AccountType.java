package ywm.foundation.user.model;

/**
 * Created by Herbert Yu on 2019-08-15 17:09
 */
public enum AccountType {

    DEFAULT(0), //默认账号
    TYPE1(1);

    private int code;

    AccountType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
