package com.mark.java.githubj.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.java.githubj.base.BaseFragment;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.databinding.FragmentMeBinding;

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
public class MeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMeBinding binding = FragmentMeBinding.inflate(inflater,container,false);
        binding.setData(UserManasger.getInstance().getUserInfo());
        binding.setLifecycleOwner(this);
        StatusBarUtil.setLightMode(getActivity());
        return binding.getRoot();
    }
}
