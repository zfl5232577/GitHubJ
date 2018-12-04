package com.mark.java.githubj.view;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mark.java.githubj.R;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import cn.aorise.common.core.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private NavController mController;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mController = Navigation.findNavController(this, R.id.main_fragment);
    }

    @Override
    protected void initEvent() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            NavOptions.Builder builder = new NavOptions.Builder();
            builder.setLaunchSingleTop(true);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    builder.setPopUpTo(R.id.home_fragment,true);
                    mController.navigate(R.id.home_fragment,null,builder.build());
                    return true;
                case R.id.navigation_repositories:
                    mController.navigate(R.id.repositories_fragment,null,builder.build());
                    return true;
                case R.id.navigation_me:
                    mController.navigate(R.id.me_fragment,null,builder.build());
                    return true;
            }
            return false;
        }
    };
}
