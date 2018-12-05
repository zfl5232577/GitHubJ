package com.mark.java.githubj.repository;

import android.util.Base64;

import com.mark.java.githubj.BuildConfig;
import com.mark.java.githubj.base.Constant;
import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.LoginUser;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.module.network.API;
import com.mark.java.githubj.module.network.GitHubAPICallback;
import com.mark.java.githubj.module.network.GitHubAPIObserver;
import com.mark.java.githubj.module.network.GitHubService;
import com.trello.rxlifecycle2.android.ActivityEvent;

import androidx.lifecycle.OnLifecycleEvent;
import cn.aorise.common.core.manager.ActivityManager;
import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIObserver;
import cn.aorise.common.core.module.network.RetrofitFactory;
import cn.aorise.common.core.module.network.RxSchedulers;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.util.GsonUtils;
import cn.aorise.common.core.util.SPUtils;
import cn.aorise.common.core.util.ToastUtils;
import retrofit2.HttpException;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class LoginRepository {

    public void login(String userName,String password,IBaseListener<LoginUser> listener){
        BaseActivity baseActivity = (BaseActivity) ActivityManager.getInstance().currentActivity();
                String authorization = "basic " + Base64.encodeToString((userName+":"+password).getBytes(),Base64.NO_WRAP);
        RetrofitFactory.getInstance().create(GitHubService.class, API.BASE_URL,BuildConfig.DEBUG)
                .login(authorization)
                .compose(RxSchedulers.compose(baseActivity, baseActivity.bindUntilEvent(ActivityEvent.STOP)))
                .subscribe(new GitHubAPIObserver<>(baseActivity, new GitHubAPICallback<LoginUser>() {
                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                        if (e instanceof HttpException) {
                            if (((HttpException) e).code() == 401) {
                                ToastUtils.showShort("username or password error");
                            }
                        }
                    }

                    @Override
                    public void onNext(LoginUser loginUser) {
                        UserManasger.getInstance().login(loginUser);
                        saveUserInfo(loginUser);
                        listener.onSuccess(loginUser);
                    }
                }));

    }

    private void saveUserInfo(LoginUser loginUser){
        SPUtils.getInstance(Constant.CacheKey.USER_CACHE_NAME).put(Constant.CacheKey.ISLOGIN,true);
        SPUtils.getInstance(Constant.CacheKey.USER_CACHE_NAME).put(Constant.CacheKey.USER_INFO, GsonUtils.toJson(loginUser),true);
    }
}
