package com.mark.java.githubj.repository;

import android.util.Base64;

import com.mark.java.githubj.BuildConfig;
import com.mark.java.githubj.base.Constant;
import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.LoginUser;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.module.network.API;
import com.mark.java.githubj.module.network.GitHubAPICallback;
import com.mark.java.githubj.module.network.GitHubAPIObserver;
import com.mark.java.githubj.module.network.GitHubService;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

import cn.aorise.common.core.manager.ActivityManager;
import cn.aorise.common.core.module.network.RetrofitFactory;
import cn.aorise.common.core.module.network.RxSchedulers;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.util.GsonUtils;
import cn.aorise.common.core.util.SPUtils;
import cn.aorise.common.core.util.ToastUtils;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
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
public class HomeRepository {

    public void queryReceivedEvents(int pageIndex,IBaseListener<List<ReceivedEvent>> listener){
        RetrofitFactory.getInstance().create(GitHubService.class, API.BASE_URL,BuildConfig.DEBUG)
                .queryReceivedEvents(UserManasger.getInstance().getUserName(),pageIndex,12)
                .subscribeOn(Schedulers.io())
                .subscribe(new GitHubAPIObserver<>(null, new GitHubAPICallback<List<ReceivedEvent>>() {
                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(List<ReceivedEvent> receivedEvents) {
                        listener.onSuccess(receivedEvents);
                    }
                }));

    }

}
