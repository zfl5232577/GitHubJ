package com.mark.java.githubj.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.java.githubj.R;
import com.mark.java.githubj.base.BaseFragment;
import com.mark.java.githubj.databinding.FragmentReposBinding;
import com.mark.java.githubj.repository.RepositoriesRepository;
import com.mark.java.githubj.repository.StaredRepository;
import com.mark.java.githubj.ui.adapter.RepositoriesAdapter;
import com.mark.java.githubj.view_models.ReposViewModel;
import com.mark.java.githubj.view_models.ReposViewModelFactory;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import cn.aorise.common.core.util.StatusBarUtil;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/04
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class StarsFragment extends BaseFragment {

    private ReposViewModel mViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentReposBinding mBinding = FragmentReposBinding.inflate(inflater, container, false);
        mBinding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        ReposViewModelFactory factory = new ReposViewModelFactory(new StaredRepository());
        mViewModel = ViewModelProviders.of(this, factory).get(ReposViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        RepositoriesAdapter adapter = new RepositoriesAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerView.setAdapter(adapter);
        mViewModel.getEvents().observe(getViewLifecycleOwner(), adapter::submitList);
        StatusBarUtil.setLightMode(getActivity());
        return mBinding.getRoot();
    }
}
