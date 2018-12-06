package com.mark.java.githubj.module.network;

import com.mark.java.githubj.data.LoginUser;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.Repos;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public interface GitHubService {
    @GET("user")
    Observable<LoginUser> login(@Header("Authorization") String authorization);

    @GET("users/{username}/received_events?")
    Observable<List<ReceivedEvent>> queryReceivedEvents(@Path("username") String username,
                                                        @Query("page") int pageIndex,
                                                        @Query("per_page") int perPage);

    @GET("users/{username}/repos?")
    Observable<List<Repos>> queryRepos(@Path("username") String username,
                                       @Query("page") int pageIndex,
                                       @Query("per_page") int perPage,
                                       @Query("sort") String sort);
}
