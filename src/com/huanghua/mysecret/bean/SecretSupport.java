package com.huanghua.mysecret.bean;

import cn.bmob.v3.BmobObject;

public class SecretSupport extends BmobObject {

    private static final long serialVersionUID = 1L;
    private User fromUser;
    private User toUser;
    private Secret secret;
    private boolean support;

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

    public Secret getSecret() {
        return secret;
    }

    public void setSecret(Secret secret) {
        this.secret = secret;
    }

    public boolean isSupport() {
        return support;
    }

    public void setSupport(boolean support) {
        this.support = support;
    }
}
