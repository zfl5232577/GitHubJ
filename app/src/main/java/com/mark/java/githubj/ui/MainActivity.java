package com.mark.java.githubj.ui;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mark.java.githubj.R;
import com.mark.java.githubj.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import cn.aorise.common.core.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;
    private int mCurrentID;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initEvent() {
        mBinding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (mCurrentID!= menuItem.getItemId() && !Navigation.findNavController(MainActivity.this, R.id.main_fragment).popBackStack(menuItem.getItemId(),false)){
                    Navigation.findNavController(MainActivity.this, R.id.main_fragment).navigate(menuItem.getItemId());
                }
                return true;
            }
        });
        Navigation.findNavController(this, R.id.main_fragment).addOnNavigatedListener(new NavController.OnNavigatedListener() {
            @Override
            public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination) {
                mCurrentID = destination.getId();
                if (mCurrentID == R.id.home_fragment
                        || mCurrentID == R.id.repositories_fragment
                        || mCurrentID == R.id.stars_fragment
                        || mCurrentID == R.id.me_fragment){
                    mBinding.navigation.setVisibility(View.VISIBLE);
                    mBinding.navigation.setSelectedItemId(mCurrentID);
                }else {
                    mBinding.navigation.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mCurrentID == R.id.home_fragment){
            finish();
        }else {
            Navigation.findNavController(this, R.id.main_fragment).navigateUp();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.e(TAG, "onSupportNavigateUp: ");
        return super.onSupportNavigateUp();
    }
}
