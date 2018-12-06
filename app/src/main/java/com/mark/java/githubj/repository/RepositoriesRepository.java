package com.mark.java.githubj.repository;

import com.mark.java.githubj.BuildConfig;
import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.Repos;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.module.network.API;
import com.mark.java.githubj.module.network.GitHubAPICallback;
import com.mark.java.githubj.module.network.GitHubAPIObserver;
import com.mark.java.githubj.module.network.GitHubService;

import java.util.List;

import cn.aorise.common.core.module.network.RetrofitFactory;
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
public class RepositoriesRepository implements IBaseReposRepository {

    @Override
    public void queryRepos(int pageIndex,IBaseListener<List<Repos>> listener){
        RetrofitFactory.getInstance().create(GitHubService.class, API.BASE_URL,BuildConfig.DEBUG)
                .queryRepos(UserManasger.getInstance().getUserName(),pageIndex,12,"")
                .subscribeOn(Schedulers.io())
                .subscribe(new GitHubAPIObserver<>(null, new GitHubAPICallback<List<Repos>>() {
                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(List<Repos> repos) {
                        listener.onSuccess(repos);
                    }
                }));

    }

}
