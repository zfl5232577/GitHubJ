package com.mark.java.githubj.utils;

import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/07
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class CommonLoadMoreDataSource<T> extends PageKeyedDataSource<Integer,T> {


    private static final String TAG = CommonLoadMoreDataSource.class.getSimpleName();
    private int pageIndex = 1;
    private LoadDataCallback<T> mLoadDataCallback;

    public CommonLoadMoreDataSource(LoadDataCallback<T> loadDataCallback) {
        mLoadDataCallback = loadDataCallback;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, T> callback) {
        Log.e(TAG, "loadInitial: ========================>" );
        mLoadDataCallback.loadData(pageIndex,callback,null);
        pageIndex++;
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, T> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, T> callback) {
        mLoadDataCallback.loadData(pageIndex,null,callback);
        pageIndex++;
    }

    public static class Factory<T> extends DataSource.Factory<Integer,T>{
        private LoadDataCallback<T> mLoadDataCallback;
        private CommonLoadMoreDataSource<T> mDataSource;

        public Factory(LoadDataCallback<T> loadDataCallback) {
            mLoadDataCallback = loadDataCallback;
        }

        public CommonLoadMoreDataSource<T> getDataSource() {
            return mDataSource;
        }

        @Override
        public DataSource<Integer,T> create() {
            mDataSource = new CommonLoadMoreDataSource<>(mLoadDataCallback);
            return mDataSource;
        }
    }

    public interface LoadDataCallback<T>{
        void loadData(int pageIndex, LoadInitialCallback<Integer, T> loadInitialCallback,LoadCallback<Integer, T> loadCallback);
    }
}
