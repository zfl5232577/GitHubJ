package com.mark.java.githubj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.java.githubj.R;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mark.java.githubj.base.BaseFragment;
import com.mark.java.githubj.repository.HomeRepository;
import com.mark.java.githubj.repository.LoginRepository;
import com.mark.java.githubj.ui.adapter.HomeAdapter;
import com.mark.java.githubj.view_models.HomeViewModel;
import com.mark.java.githubj.view_models.HomeViewModelFactory;
import com.mark.java.githubj.view_models.LoginViewModel;
import com.mark.java.githubj.view_models.LoginViewModelFactory;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
public class HomeFragment extends BaseFragment {


    private HomeViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mBinding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        HomeViewModelFactory factory = new HomeViewModelFactory(new HomeRepository());
        mViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        HomeAdapter adapter = new HomeAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerView.setAdapter(adapter);
        mViewModel.getEvents().observe(getViewLifecycleOwner(), adapter::submitList);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
