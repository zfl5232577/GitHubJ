package com.mark.java.githubj.data;

import android.content.Context;

import com.mark.java.githubj.base.Constant;

import java.util.List;

import cn.aorise.common.core.module.network.Response;
import cn.aorise.common.core.util.CacheUtils;
import cn.aorise.common.core.util.GsonUtils;
import cn.aorise.common.core.util.SPUtils;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/08/20
 *     desc   : 用户管理类
 *     version: 1.0
 * </pre>
 */
public class UserManasger {

    private static volatile UserManasger mInstance;
    private LoginUser mLoginUser;
    private boolean isLogin;
    private String authorization;

    private UserManasger() {
        isLogin = SPUtils.getInstance(Constant.CacheKey.USER_CACHE_NAME).getBoolean(Constant.CacheKey.ISLOGIN);
        authorization = SPUtils.getInstance(Constant.CacheKey.USER_CACHE_NAME).getString(Constant.CacheKey.AUTHORIZATION);
        mLoginUser = GsonUtils.fromJson(SPUtils.getInstance(Constant.CacheKey.USER_CACHE_NAME).getString(Constant.CacheKey.USER_INFO), LoginUser.class);
    }

    public void login(LoginUser loginUser,String authorization) {
        mLoginUser = loginUser;
        isLogin = true;
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    public static UserManasger getInstance() {
        if (mInstance == null) {
            synchronized (UserManasger.class) {
                if (mInstance == null) {
                    mInstance = new UserManasger();
                }
            }
        }
        return mInstance;
    }


    public boolean isLogin() {
        return isLogin && mLoginUser != null;
    }

    public LoginUser getUserInfo() {
        return mLoginUser;
    }

    public String getUserName() {
        if (mLoginUser == null) {
            throw new NullPointerException("mUserInfo is null,you need login() Or CacheUtils.getInstance().setSerializable(Constant.CacheKey.USER_INFO)");
        }
        return mLoginUser.getLogin();
    }
}
