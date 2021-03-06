package com.huanghua.mysecret.bean;

import cn.bmob.v3.BmobObject;

public class Secret extends BmobObject {

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String contents;
    private User user;

    public Secret() {
        // setTableName("Secret");
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
