package com.mark.java.githubj.view_models;

import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.Repos;
import com.mark.java.githubj.repository.HomeRepository;
import com.mark.java.githubj.repository.IBaseReposRepository;
import com.mark.java.githubj.repository.RepositoriesRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class ReposViewModel extends ViewModel {

    private IBaseReposRepository mRepositoriesRepository;
    private MutableLiveData<List<Repos>> mRepos = new MutableLiveData<>();
    private MutableLiveData<Boolean> refreshing = new MutableLiveData<>();

    public ReposViewModel(IBaseReposRepository repositoriesRepository) {
        mRepositoriesRepository = repositoriesRepository;
        initRepos();
    }

    public MutableLiveData<List<Repos>> getEvents() {
        return mRepos;
    }

    public MutableLiveData<Boolean> getRefreshing() {
        return refreshing;
    }

    public void initRepos() {
        refreshing.setValue(true);
        queryRepos(1);
    }

    public void queryRepos(int pageIndex){
        mRepositoriesRepository.queryRepos(pageIndex,new IBaseListener<List<Repos>>() {
            @Override
            public void onSuccess(List<Repos> repos) {
                refreshing.postValue(false);
                mRepos.postValue(repos);
            }

            @Override
            public void onError(Throwable exception) {
                refreshing.postValue(false);
            }
        });
    }
}
