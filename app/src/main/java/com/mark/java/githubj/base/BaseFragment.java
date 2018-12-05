package com.mark.java.githubj.base;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.java.githubj.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.Navigation;
import cn.aorise.common.core.util.StatusBarUtil;


public abstract class BaseFragment extends RxFragment {

    private Toolbar mToolBar = null;
    private TextView mTxtTitle = null;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initToolbar(view.findViewById(R.id.toolbar));
        View status = view.findViewById(R.id.fake_status_bar);
        if (status != null) {
            ViewGroup.LayoutParams layoutParams = status.getLayoutParams();
            layoutParams.height = StatusBarUtil.getStatusBarHeight(getActivity());
            status.setLayoutParams(layoutParams);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    public Toolbar getToolBar() {
        return this.mToolBar;
    }

    private void initToolbar(Toolbar toolBar) {
        this.mToolBar = toolBar;
        if (this.mToolBar != null) {
            this.mToolBar.setTitle("");
            ((AppCompatActivity)getActivity()).setSupportActionBar(mToolBar);
            this.mTxtTitle = mToolBar.findViewById(cn.aorise.common.R.id.toolbar_title);
            this.mTxtTitle.setText(Navigation.findNavController(mToolBar).getCurrentDestination().getLabel());
            this.mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Navigation.findNavController(view).popBackStack();
                }
            });
        }
        setTitleCenter(Gravity.CENTER);
    }

    public void setTitleCenter(int gravity) {
        if (null != this.mTxtTitle) {
            Toolbar.LayoutParams params = new Toolbar.LayoutParams(-2, -2);
            params.gravity = gravity;
            this.mTxtTitle.setLayoutParams(params);
        }

    }

    public void setToolBarTitle(CharSequence title) {
        if (null != this.mTxtTitle && !TextUtils.isEmpty(title)) {
            this.mTxtTitle.setText(title);
        }
    }

    public void setTitleColor(int color) {
        if (null != this.mTxtTitle) {
            mTxtTitle.setTextColor(color);
        }
    }
}
