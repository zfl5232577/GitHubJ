package cn.aorise.common.core.ui.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.File;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import cn.aorise.common.R;
import cn.aorise.common.core.config.Config;
import cn.aorise.common.core.util.AoriseLog;
import cn.aorise.common.core.util.CrashHandlerUtils;
import cn.aorise.common.core.util.HandlerUtils;
import cn.aorise.common.core.util.StatusBarUtil;


/**
 * <pre>
 *     author : tangjy
 *     e-mail : jianye.tang@aorise.org
 *     time   : 2017/03/17
 *     desc   : Activity基类
 *     version: 1.0
 * </pre>
 */
public abstract class BaseActivity extends RxAppCompatActivity implements CrashHandlerUtils.CrashListener {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private Toolbar mToolBar = null;
    private TextView mTxtTitle = null;
    private ProgressDialog mLoadingDialog = null;
    private Toast mToast = null;

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AoriseLog.i(TAG, getLocalClassName() + " - onCreate");
//        CrashHandlerUtils.getsInstance().init(this, this,
//                getPackageName() + File.separator + AoriseConstant.Folder.LOG_PATH);
//        ActivityManager.getInstance().addActivity(this);
        setStatusBar();
        initData();
        initView();
        initEvent();
    }

    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        destroyToast();
        dismissLoadingDialog();
//        ActivityManager.getInstance().finishActivity(this);
        HandlerUtils.HANDLER.removeCallbacksAndMessages(null);
        AoriseLog.i(TAG, getLocalClassName() + " - onDestroy");
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar(findViewById(R.id.toolbar));
    }

    /**
     * 提示语
     *
     * @param text 提示内容
     */
    @SuppressLint("ShowToast")
    public void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 提示语
     *
     * @param resId 提示内容ID
     */
    public void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }

    /**
     * 长时间提示语
     *
     * @param text 提示内容
     */
    @SuppressLint("ShowToast")
    public void showLongToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    /**
     * 长时间提示语
     *
     * @param resId 提示内容ID
     */
    public void showLongToast(@StringRes int resId) {
        showLongToast(getString(resId));
    }

    private void destroyToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

    /**
     * 显示加载框
     */
    public void showLoadingDialog() {
        AoriseLog.i(TAG, "showLoadingDialog");
        if (null == mLoadingDialog) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setCancelable(true);

            mLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mLoadingDialog.setMessage(getString(R.string.aorise_loading_tips));
            mLoadingDialog.show();
        }
    }

    /**
     * 取消加载框
     */
    public void dismissLoadingDialog() {
        AoriseLog.i(TAG, "dismissLoadingDialog");
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.cancel();
        }
        mLoadingDialog = null;
    }

    /**
     * 获取标题
     *
     * @return toolbar
     */
    public Toolbar getToolBar() {
        return mToolBar;
    }

    /**
     * 获取标题栏标题
     *
     * @return
     */
    public TextView getToolBarTitle() {
        return mTxtTitle;
    }

    /**
     * 标题对齐方向
     *
     * @param gravity
     */
    public void setTitleCenter(int gravity) {
        if (null != mTxtTitle) {
            Toolbar.LayoutParams params = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 此处相当于布局文件中的Android:layout_gravity属性
            params.gravity = gravity;
            mTxtTitle.setLayoutParams(params);
        }
    }

    /**
     * 设置标题名字
     *
     * @param title 标题名字内容
     */
    public void setToolBarTitle(CharSequence title) {
        if (null != mTxtTitle && !TextUtils.isEmpty(title)) {
            mTxtTitle.setText(title);
        }
    }

    public void initToolbar(Toolbar toolbar) {
        mToolBar = toolbar; // (Toolbar) findViewById(R.id.toolbar);
        if (null == mToolBar) {
            return;
        }

        // title、subTitle、logo 三个属性必须在 setSupportActionBar 之前设置
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        // mToolBar.setBackgroundResource(R.color.base_blue);
        // Navigation Icon 属性必须在 setSupoortActionBar 之后才有作用
        // mToolBar.setNavigationIcon(R.drawable.aorise_ic_navigation);

        mTxtTitle = (TextView) findViewById(R.id.toolbar_title);
        mTxtTitle.setText(getTitle());
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (null == getSupportActionBar()) {
            return;
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    /**
     * 捕获APP异常
     *
     * @param file 异常写入的文件
     */
    @Override
    public void uploadExceptionToServer(File file) {

    }


    /**
     * APP异常后的动作或者提示
     */
    @Override
    public void crashAction() {
        showToast(R.string.aorise_label_crash_msg);
    }

    /**
     * 页面跳转
     *
     * @param pClass Activity类名
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 页面跳转
     *
     * @param pClass  Activity类名
     * @param pBundle 数据
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 打开WebView
     *
     * @param uri URL地址
     */
    public void openWebActivity(String uri) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.TransportKey.BUNDLE_URL, uri);
        openActivity(BaseWebActivity.class, bundle);
    }
}
