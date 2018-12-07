package com.mark.java.githubj.view_models;

import com.mark.java.githubj.base.Constant;
import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.Repos;
import com.mark.java.githubj.repository.HomeRepository;
import com.mark.java.githubj.repository.IBaseReposRepository;
import com.mark.java.githubj.repository.RepositoriesRepository;
import com.mark.java.githubj.utils.CommonLoadMoreDataSource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
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
public class ReposViewModel extends ViewModel implements CommonLoadMoreDataSource.LoadDataCallback<Repos> {

    private IBaseReposRepository mRepositoriesRepository;
    private MutableLiveData<Boolean> refreshing = new MutableLiveData<>();
    private CommonLoadMoreDataSource.Factory<Repos> mFactory;
    private LiveData<PagedList<Repos>> mRepos;

    public ReposViewModel(IBaseReposRepository repositoriesRepository) {
        mRepositoriesRepository = repositoriesRepository;
        initRepos();
    }

    public LiveData<PagedList<Repos>> getEvents() {
        return mRepos;
    }

    public MutableLiveData<Boolean> getRefreshing() {
        return refreshing;
    }

    public void initRepos() {
        refreshing.setValue(true);
        if (mFactory == null) {
            PagedList.Config config = new PagedList.Config.Builder()
                    .setPageSize(Constant.PAGE_SIZE)
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(Constant.PAGE_SIZE*2)
                    .build();
            mFactory = new CommonLoadMoreDataSource.Factory<>(this);
            mRepos = new LivePagedListBuilder<>(mFactory, config).build();
        } else {
            mFactory.getDataSource().invalidate();
        }
    }


    @Override
    public void loadData(int pageIndex, PageKeyedDataSource.LoadInitialCallback<Integer, Repos> loadInitialCallback, PageKeyedDataSource.LoadCallback<Integer, Repos> loadCallback) {
        mRepositoriesRepository.queryRepos(pageIndex,new IBaseListener<List<Repos>>() {
            @Override
            public void onSuccess(List<Repos> repos) {
                if (pageIndex == 1) {
                    refreshing.postValue(false);
                    loadInitialCallback.onResult(repos, pageIndex, pageIndex + 1);
                } else {
                    loadCallback.onResult(repos, pageIndex + 1);
                }
            }

            @Override
            public void onError(Throwable exception) {
                refreshing.postValue(false);
            }
        });
    }
}
