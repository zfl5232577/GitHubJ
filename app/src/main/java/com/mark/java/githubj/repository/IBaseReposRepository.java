package com.mark.java.githubj.repository;

import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.Repos;

import java.util.List;

/**
 * <pre>
 *     author : admin
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/07
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public interface IBaseReposRepository {

    void queryRepos(int pageIndex,IBaseListener<List<Repos>> listener);
}
