package com.mark.java.githubj.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.java.githubj.databinding.FragmentLoginBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.aorise.common.core.manager.ActivityManager;
import cn.aorise.common.core.ui.base.BaseFragment;
import cn.aorise.common.core.util.SPUtils;
import cn.aorise.common.core.util.StatusBarUtil;

/**
 * <pre>
 *     author : admin
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class LoginFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater, container, false);
        StatusBarUtil.setLightMode(getActivity());
        return binding.getRoot();
    }

}
