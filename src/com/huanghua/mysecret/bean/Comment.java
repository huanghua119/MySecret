package com.huanghua.mysecret.bean;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {

    private static final long serialVersionUID = 1L;
    private User fromUser;
    private User toUser;
    private String contents;
    private Secret secret;

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Secret getSecret() {
        return secret;
    }

    public void setSecret(Secret secret) {
        this.secret = secret;
    }
}
