package com.mark.java.githubj.module.network;

import com.mark.java.githubj.R;

import java.lang.ref.WeakReference;

import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.util.ToastUtils;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/06/25
 *     desc   : 可视化网络请求订阅者
 *     version: 1.0
 * </pre>
 */
public class GitHubAPIObserver<T> implements Observer<T> {
    private static final String TAG = GitHubAPIObserver.class.getSimpleName();
    private WeakReference<BaseActivity> mWeakReference;
    private GitHubAPICallback<T> mCallback;

    public GitHubAPIObserver(BaseActivity baseActivity, @NonNull GitHubAPICallback<T> callback) {
        mWeakReference = new WeakReference(baseActivity);
        mCallback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mCallback.onSubscribe(d);
    }

    @Override
    public void onNext(T result) {
        if (result == null) {
            return;
        }
        this.mCallback.onNext(result);
    }

    @Override
    public void onError(Throwable e) {
        if (this.mWeakReference.get() != null) {
            this.mWeakReference.get().dismissLoadingDialog();
        }
        if (e instanceof HttpException) {
            if (((HttpException) e).code() != 401) {
                ToastUtils.showShort(R.string.text_network_request_fail);
            }
        }
        this.mCallback.onError(e);
        this.mCallback.onFinish();
    }

    @Override
    public void onComplete() {
        if (this.mWeakReference.get() != null) {
            this.mWeakReference.get().dismissLoadingDialog();
        }
        this.mCallback.onFinish();
    }
}
