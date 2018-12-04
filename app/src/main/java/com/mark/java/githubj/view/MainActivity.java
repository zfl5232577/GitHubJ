package com.mark.java.githubj.view;

import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mark.java.githubj.R;
import com.mark.java.githubj.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.util.SPUtils;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavigationUI.setupWithNavController(mBinding.navigation, Navigation.findNavController(this, R.id.main_fragment));
        if (!SPUtils.getInstance().getBoolean("isLogin", false)) {
            Navigation.findNavController(this, R.id.main_fragment).navigate(R.id.login_fragment);
        }
    }

    @Override
    protected void initEvent() {
        Navigation.findNavController(this, R.id.main_fragment).addOnNavigatedListener(new NavController.OnNavigatedListener() {
            @Override
            public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination) {
                int id = destination.getId();
                if (id == R.id.home_fragment
                        || id == R.id.repositories_fragment
                        || id == R.id.stars_fragment
                        || id == R.id.me_fragment){
                    mBinding.navigation.setVisibility(View.VISIBLE);
                }else {
                    mBinding.navigation.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (Navigation.findNavController(this, R.id.main_fragment).getCurrentDestination().getId()==R.id.login_fragment){
            finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.e(TAG, "onSupportNavigateUp: ");
        return super.onSupportNavigateUp();
    }
}
