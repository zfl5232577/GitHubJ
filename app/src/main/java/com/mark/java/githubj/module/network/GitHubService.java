package com.mark.java.githubj.module.network;

import com.mark.java.githubj.data.LoginUser;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

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
}
