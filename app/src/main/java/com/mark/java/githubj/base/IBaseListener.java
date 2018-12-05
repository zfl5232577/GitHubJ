package com.mark.java.githubj.base;

public interface IBaseListener<T> {

    /**
     * 成功的时候回调
     */
    void onSuccess(T t);

    /**
     * 失败的时候回调
     */
    void onError(Throwable exception);
}