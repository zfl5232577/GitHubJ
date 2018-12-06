package com.mark.java.githubj.view_models;

import com.mark.java.githubj.repository.HomeRepository;
import com.mark.java.githubj.repository.RepositoriesRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class ReposViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private RepositoriesRepository mRepositoriesRepository;

    public ReposViewModelFactory(RepositoriesRepository repositoriesRepository) {
        mRepositoriesRepository = repositoriesRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ReposViewModel(mRepositoriesRepository);
    }
}
