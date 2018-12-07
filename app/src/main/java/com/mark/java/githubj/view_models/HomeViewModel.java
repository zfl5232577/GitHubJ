package com.mark.java.githubj.view_models;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mark.java.githubj.base.Constant;
import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.LoginUser;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.repository.HomeRepository;
import com.mark.java.githubj.repository.LoginRepository;
import com.mark.java.githubj.utils.CommonLoadMoreDataSource;

import java.util.List;
import java.util.concurrent.Executor;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class HomeViewModel extends ViewModel implements CommonLoadMoreDataSource.LoadDataCallback<ReceivedEvent> {

    private HomeRepository mHomeRepository;
    private LiveData<PagedList<ReceivedEvent>> events;
    private MutableLiveData<Boolean> refreshing = new MutableLiveData<>();
    private CommonLoadMoreDataSource.Factory<ReceivedEvent> mFactory;

    public HomeViewModel(HomeRepository homeRepository) {
        mHomeRepository = homeRepository;
        initReceivedEvents();
    }

    public LiveData<PagedList<ReceivedEvent>> getEvents() {
        return events;
    }

    public MutableLiveData<Boolean> getRefreshing() {
        return refreshing;
    }

    public void initReceivedEvents() {
        refreshing.setValue(true);
        if (mFactory == null) {
            PagedList.Config config = new PagedList.Config.Builder()
                    .setPageSize(Constant.PAGE_SIZE)
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(Constant.PAGE_SIZE * 2)
                    .build();
            mFactory = new CommonLoadMoreDataSource.Factory<>(this);
            events = new LivePagedListBuilder<>(mFactory, config).build();
        } else {
            mFactory.getDataSource().invalidate();
        }
    }

    @Override
    public void loadData(int pageIndex, PageKeyedDataSource.LoadInitialCallback<Integer, ReceivedEvent> loadInitialCallback, PageKeyedDataSource.LoadCallback<Integer, ReceivedEvent> loadCallback) {
        mHomeRepository.queryReceivedEvents(pageIndex, new IBaseListener<List<ReceivedEvent>>() {
            @Override
            public void onSuccess(List<ReceivedEvent> receivedEvents) {
                if (pageIndex == 1) {
                    refreshing.postValue(false);
                    loadInitialCallback.onResult(receivedEvents, pageIndex, pageIndex + 1);
                } else {
                    loadCallback.onResult(receivedEvents, pageIndex + 1);
                }
            }

            @Override
            public void onError(Throwable exception) {
                refreshing.postValue(false);
            }
        });
    }
}
