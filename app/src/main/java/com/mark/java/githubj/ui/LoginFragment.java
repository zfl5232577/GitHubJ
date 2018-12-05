package com.mark.java.githubj.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.java.githubj.R;
import com.mark.java.githubj.databinding.FragmentLoginBinding;
import com.mark.java.githubj.repository.LoginRepository;
import com.mark.java.githubj.view_models.LoginViewModel;
import com.mark.java.githubj.view_models.LoginViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import com.mark.java.githubj.base.BaseFragment;
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

    private FragmentLoginBinding mBinding;
    private LoginViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(getActivity());
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);
        LoginViewModelFactory factory = new LoginViewModelFactory(new LoginRepository());
        mViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        mViewModel.loginSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Navigation.findNavController(mBinding.getRoot()).navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment());
                }
            }
        });
        return mBinding.getRoot();
    }

}
