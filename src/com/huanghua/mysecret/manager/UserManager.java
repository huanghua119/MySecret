package com.huanghua.mysecret.manager;

import android.content.Context;
import android.util.Log;
import cn.bmob.v3.listener.SaveListener;

import com.huanghua.mysecret.CustomApplcation;
import com.huanghua.mysecret.bean.User;

public class UserManager {

    private static UserManager mUserManager;
    private static Context sContext;
    private User mCurrentUser;

    public interface UserManagerListener {
        public void onError(int arg0, String arg1);

        public void onSuccess(User u);
    }

    private UserManager() {
    }

    public static UserManager getInstance(Context context) {
        if (mUserManager == null) {
            synchronized (UserManager.class) {
                if (mUserManager == null) {
                    mUserManager = new UserManager();
                }
            }
        }
        sContext = context;
        return mUserManager;
    }

    public void initCurrentUser() {
        mCurrentUser = User.getCurrentUser(sContext, User.class);
    }

    public void login(String userName, String passWord,
            final UserManagerListener userListener) {
        mCurrentUser = new User();
        mCurrentUser.setUsername(userName);
        mCurrentUser.setPassword(passWord);
        mCurrentUser.login(sContext, new SaveListener() {
            @Override
            public void onSuccess() {
                if (userListener != null) {
                    userListener.onSuccess(mCurrentUser);
                }
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                mCurrentUser = null;
                if (userListener != null) {
                    userListener.onError(arg0, arg1);
                }
            }
        });
    }

    public void logout() {
        User.logOut(sContext);
        mCurrentUser = null;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void signUp(User u, final UserManagerListener userListener) {
        u.signUp(sContext, new SaveListener() {
            @Override
            public void onSuccess() {
                if (userListener != null) {
                    userListener.onSuccess(mCurrentUser);
                }
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                if (userListener != null) {
                    userListener.onError(arg0, arg1);
                }
                mCurrentUser = null;
            }
        });
        mCurrentUser = u;
    }

    public void setCreentUser(User u) {
        this.mCurrentUser = u;
    }

    public void showLog(String msg) {
        if (CustomApplcation.DEBUG) {
            Log.i(CustomApplcation.TAG, msg);
        }
    }

}
