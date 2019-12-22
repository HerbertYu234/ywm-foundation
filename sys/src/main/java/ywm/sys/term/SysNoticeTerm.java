package ywm.sys.term;

import ywm.library.shared.term.SearchTerm;

/**
 * Created by Herbert Yu on 2019-12-17 20:45
 */
public class SysNoticeTerm implements SearchTerm {

    /**
     * 创建人
     */
    private String userId;

    /**
     * 状态
     * 1 启用
     * -1 禁用
     */
    private int status = 1;

    /**
     * 轮播展示
     */
    private Boolean playable;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getPlayable() {
        return playable;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }
}
