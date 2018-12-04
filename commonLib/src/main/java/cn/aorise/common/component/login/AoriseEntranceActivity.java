package cn.aorise.common.component.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import cn.aorise.common.core.config.Config;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.util.AppUtils;

/**
 * <pre>
 *     author : tangjy
 *     e-mail : jianye.tang@aorise.org
 *     time   : 2017/03/17
 *     desc   : 公共登录入口
 *     version: 1.0
 * </pre>
 */
@Deprecated
public abstract class AoriseEntranceActivity extends BaseActivity {
    private static final String TAG = AoriseEntranceActivity.class.getSimpleName();
    private EntranceReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    class EntranceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent
                    && Config.BroadcastKey.CN_AORISE_PLATFORM_LOGIN_ACCOUNT.equals(intent.getAction())) {
                Bundle bundle = intent.getExtras();
                String account = bundle.getString(Config.AccountKey.USER_ACCOUNT);
                String id = bundle.getString(Config.AccountKey.USER_ID);
                String sex = bundle.getString(Config.AccountKey.USER_SEX);
                Log.d(TAG, "Account:" + account + " ;ID:" + id + " ;Sex:" + sex);

                // TODO: 调试稳定后可以删除
                if (AppUtils.isAppDebug()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Account:").append(account).append("\r\n").append("ID:").append(id).append("\r\n")
                            .append("Sex:").append(sex);
                    showToast(sb.toString());
                }
            }
        }
    }

    private void registerReceiver() {
        mReceiver = new EntranceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Config.BroadcastKey.CN_AORISE_PLATFORM_LOGIN_ACCOUNT);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);
    }
}
