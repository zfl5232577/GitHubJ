package com.mark.java.githubj.view_models;

import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.LoginUser;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.repository.LoginRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class LoginViewModel extends ViewModel {

    private LoginRepository mLoginRepository;
    public MutableLiveData<String> mUserName = new MutableLiveData<>();
    public MutableLiveData<String> mPassWord = new MutableLiveData<>();
    public MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    public LoginViewModel(LoginRepository loginRepository) {
        mLoginRepository = loginRepository;
        if (UserManasger.getInstance().isLogin()){
            loginSuccess.setValue(true);
        }
    }





    public void login() {
        mLoginRepository.login(mUserName.getValue(), mPassWord.getValue(), new IBaseListener<LoginUser>() {
            @Override
            public void onSuccess(LoginUser loginUser) {
                loginSuccess.setValue(true);
            }

            @Override
            public void onError(Throwable exception) {

            }
        });
    }

}
