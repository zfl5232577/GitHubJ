package com.mark.java.githubj.module.network;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/06/25
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public abstract class GitHubAPICallback<T> {
    void onSubscribe(Disposable disposable){}
    public abstract void onError(Throwable e);

    public abstract void onNext(T t);

    void onFinish(){}
}
