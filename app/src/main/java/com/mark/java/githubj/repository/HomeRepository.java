package com.mark.java.githubj.repository;

import android.util.Base64;

import com.mark.java.githubj.BuildConfig;
import com.mark.java.githubj.base.Constant;
import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.module.network.API;
import com.mark.java.githubj.module.network.GitHubAPICallback;
import com.mark.java.githubj.module.network.GitHubAPIObserver;
import com.mark.java.githubj.module.network.GitHubService;

import java.util.Iterator;
import java.util.List;

import cn.aorise.common.core.module.network.RetrofitFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
                .queryReceivedEvents(UserManasger.getInstance().getAuthorization(),UserManasger.getInstance().getUserName(),pageIndex, Constant.PAGE_SIZE)
                .flatMap(receivedEvents -> {
                    Iterator<ReceivedEvent> iterator = receivedEvents.iterator();
                    while (iterator.hasNext()){
                        ReceivedEvent event = iterator.next();
                        ReceivedEvent.Type type = event.getType();
                        if (type!= ReceivedEvent.Type.WatchEvent
                                &&type!= ReceivedEvent.Type.CreateEvent
                                &&type!= ReceivedEvent.Type.ForkEvent
                                &&type!= ReceivedEvent.Type.PushEvent){
                            iterator.remove();
                        }
                    }
                    return Observable.just(receivedEvents);
                })
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
