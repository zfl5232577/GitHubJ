package com.mark.java.githubj.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.java.githubj.databinding.FragmentHomeBinding;

import androidx.annotation.Nullable;
import cn.aorise.common.core.ui.base.BaseFragment;
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




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("mark", "onCreate: "+this );
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("mark", "onCreateView: ==================="+this );
        FragmentHomeBinding mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        StatusBarUtil.setLightMode(getActivity());
        mBinding.text.setOnClickListener(view -> mBinding.text.setText("dianji"));
        return mBinding.getRoot();
    }
}
