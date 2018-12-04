package cn.aorise.common.core.util;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import cn.aorise.common.core.constant.PermissionConstants;
import cn.aorise.common.core.constant.PermissionConstants.Permission;

/**
 * <pre>
 *     author : tangjy
 *     e-mail : jianye.tang@aorise.org
 *     time   : 2017/03/17
 *     desc   : 权限相关工具类
 * </pre>
 */
public final class PermissionUtils {

    /**
     * 获取APP註冊過的權限列表
     *
     * @param permissions 權限列表
     * @return
     */
    public static String[] getPermissions(@Permission final String... permissions) {
        List<String> permissionGroups = new ArrayList<>();

        List<String> appPermissions = getAppPermissions();
        if (null == appPermissions || appPermissions.size() == 0) {
            AoriseLog.e("manifests文件没有配置权限列表");
            return null;
        }

        for (String permission : permissions) {
            String[] group = PermissionConstants.getPermissions(permission);
            for (String single : group) {
                if (appPermissions.contains(single)) {
                    permissionGroups.add(single);
                }
            }
        }

        String[] authPermission = new String[permissionGroups.size()];
        permissionGroups.toArray(authPermission);
        return authPermission;
    }

    /**
     * 判断是否授权通过
     *
     * @param grantResults
     * @return true 授权通过； false 授权不通过
     */
    public static boolean isGranted(@NonNull int[] grantResults) {
        for (int i = 0, size = grantResults.length; i < size; i++) {
            if (PackageManager.PERMISSION_DENIED == grantResults[i])
                return false;
        }
        return true;
    }

    /**
     * 判断权限是否被授予
     *
     * @param permissions 权限
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isGranted(@NonNull String[] permissions) {
        for (String permission : permissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGranted(final String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || PackageManager.PERMISSION_GRANTED
                == ContextCompat.checkSelfPermission(Utils.getApp(), permission);
    }

    /**
     * 获取应用权限
     *
     * @return 清单文件中的权限列表
     */
    private static List<String> getAppPermissions() {
        return getAppPermissions(Utils.getApp().getPackageName());
    }

    /**
     * 获取应用权限
     *
     * @param packageName 包名
     * @return 清单文件中的权限列表
     */
    private static List<String> getAppPermissions(final String packageName) {
        PackageManager pm = Utils.getApp().getPackageManager();
        try {
            return Arrays.asList(
                    pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
                            .requestedPermissions
            );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 打开应用具体设置
     */
    public static void openAppSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

//    private static final List<String> PERMISSIONS = getPermissions();
//
//    private static PermissionUtils sInstance;
//
//    private OnRationaleListener mOnRationaleListener;
//    private SimpleCallback mSimpleCallback;
//    private FullCallback mFullCallback;
//    private ThemeCallback mThemeCallback;
//    private Set<String> mPermissions;
//    private List<String> mPermissionsRequest;
//    private List<String> mPermissionsGranted;
//    private List<String> mPermissionsDenied;
//    private List<String> mPermissionsDeniedForever;
//
//    /**
//     * 获取应用权限
//     *
//     * @return 清单文件中的权限列表
//     */
//    public static List<String> getPermissions() {
//        return getPermissions(Utils.getApp().getPackageName());
//    }
//
//    /**
//     * 获取应用权限
//     *
//     * @param packageName 包名
//     * @return 清单文件中的权限列表
//     */
//    public static List<String> getPermissions(final String packageName) {
//        PackageManager pm = Utils.getApp().getPackageManager();
//        try {
//            return Arrays.asList(
//                    pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
//                            .requestedPermissions
//            );
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }
//
//    /**
//     * 判断权限是否被授予
//     *
//     * @param permissions 权限
//     * @return {@code true}: 是<br>{@code false}: 否
//     */
//    public static boolean isGranted(final String... permissions) {
//        for (String permission : permissions) {
//            if (!isGranted(permission)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean isGranted(final String permission) {
//        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
//                || PackageManager.PERMISSION_GRANTED
//                == ContextCompat.checkSelfPermission(Utils.getApp(), permission);
//    }
//
//    /**
//     * 打开应用具体设置
//     */
//    public static void openAppSettings() {
//        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
//        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
//        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//    }
//
//    /**
//     * 设置请求权限
//     *
//     * @param permissions 要请求的权限
//     * @return {@link PermissionUtils}
//     */
//    public static PermissionUtils permission(@PermissionConstants.Permission final String... permissions) {
//        return new PermissionUtils(permissions);
//    }
//
//    private PermissionUtils(final String... permissions) {
//        mPermissions = new LinkedHashSet<>();
//        for (String permission : permissions) {
//            for (String aPermission : PermissionConstants.getPermissions(permission)) {
//                if (PERMISSIONS.contains(aPermission)) {
//                    mPermissions.add(aPermission);
//                }
//            }
//        }
//        sInstance = this;
//    }
//
//    /**
//     * 设置拒绝权限后再次请求的回调接口
//     *
//     * @param listener 拒绝权限后再次请求的回调接口
//     * @return {@link PermissionUtils}
//     */
//    public PermissionUtils rationale(final OnRationaleListener listener) {
//        mOnRationaleListener = listener;
//        return this;
//    }
//
//    /**
//     * 设置回调
//     *
//     * @param callback 简单回调接口
//     * @return {@link PermissionUtils}
//     */
//    public PermissionUtils callback(final SimpleCallback callback) {
//        mSimpleCallback = callback;
//        return this;
//    }
//
//    /**
//     * 设置回调
//     *
//     * @param callback 完整回调接口
//     * @return {@link PermissionUtils}
//     */
//    public PermissionUtils callback(final FullCallback callback) {
//        mFullCallback = callback;
//        return this;
//    }
//
//    /**
//     * 设置主题
//     *
//     * @param callback 主题回调接口
//     * @return {@link PermissionUtils}
//     */
//    public PermissionUtils theme(final ThemeCallback callback) {
//        mThemeCallback = callback;
//        return this;
//    }
//
//    /**
//     * 开始请求
//     */
//    public void request() {
//        mPermissionsGranted = new ArrayList<>();
//        mPermissionsRequest = new ArrayList<>();
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            mPermissionsGranted.addAll(mPermissions);
//            requestCallback();
//        } else {
//            for (String permission : mPermissions) {
//                if (isGranted(permission)) {
//                    mPermissionsGranted.add(permission);
//                } else {
//                    mPermissionsRequest.add(permission);
//                }
//            }
//            if (mPermissionsRequest.isEmpty()) {
//                requestCallback();
//            } else {
//                startPermissionActivity();
//            }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void startPermissionActivity() {
//        mPermissionsDenied = new ArrayList<>();
//        mPermissionsDeniedForever = new ArrayList<>();
//        PermissionActivity.start(Utils.getApp());
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private boolean rationale(final Activity activity) {
//        boolean isRationale = false;
//        if (mOnRationaleListener != null) {
//            for (String permission : mPermissionsRequest) {
//                if (activity.shouldShowRequestPermissionRationale(permission)) {
//                    getPermissionsStatus(activity);
//                    mOnRationaleListener.rationale(new OnRationaleListener.ShouldRequest() {
//                        @Override
//                        public void again(boolean again) {
//                            if (again) {
//                                startPermissionActivity();
//                            } else {
//                                requestCallback();
//                            }
//                        }
//                    });
//                    isRationale = true;
//                    break;
//                }
//            }
//            mOnRationaleListener = null;
//        }
//        return isRationale;
//    }
//
//    private void getPermissionsStatus(final Activity activity) {
//        for (String permission : mPermissionsRequest) {
//            if (isGranted(permission)) {
//                mPermissionsGranted.add(permission);
//            } else {
//                mPermissionsDenied.add(permission);
//                if (!activity.shouldShowRequestPermissionRationale(permission)) {
//                    mPermissionsDeniedForever.add(permission);
//                }
//            }
//        }
//    }
//
//    private void requestCallback() {
//        if (mSimpleCallback != null) {
//            if (mPermissionsRequest.size() == 0
//                    || mPermissions.size() == mPermissionsGranted.size()) {
//                mSimpleCallback.onGranted();
//            } else {
//                if (!mPermissionsDenied.isEmpty()) {
//                    mSimpleCallback.onDenied();
//                }
//            }
//            mSimpleCallback = null;
//        }
//        if (mFullCallback != null) {
//            if (mPermissionsRequest.size() == 0
//                    || mPermissions.size() == mPermissionsGranted.size()) {
//                mFullCallback.onGranted(mPermissionsGranted);
//            } else {
//                if (!mPermissionsDenied.isEmpty()) {
//                    mFullCallback.onDenied(mPermissionsDeniedForever, mPermissionsDenied);
//                }
//            }
//            mFullCallback = null;
//        }
//        mOnRationaleListener = null;
//        mThemeCallback = null;
//    }
//
//    private void onRequestPermissionsResult(final Activity activity) {
//        getPermissionsStatus(activity);
//        requestCallback();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public static class PermissionActivity extends Activity {
//
//        public static void start(final Context context) {
//            Intent starter = new Intent(context, PermissionActivity.class);
//            starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(starter);
//        }
//
//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            if (sInstance.mThemeCallback != null) {
//                sInstance.mThemeCallback.onActivityCreate(this);
//            } else {
//                Window window = getWindow();
//                window.setBackgroundDrawable(null);
//                int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
//                window.getDecorView().setSystemUiVisibility(option);
//                window.setStatusBarColor(Color.TRANSPARENT);
//            }
//
//            super.onCreate(savedInstanceState);
//
//            if (sInstance.rationale(this)) {
//                finish();
//                return;
//            }
//            if (sInstance.mPermissionsRequest != null) {
//                int size = sInstance.mPermissionsRequest.size();
//                requestPermissions(sInstance.mPermissionsRequest.toArray(new String[size]), 1);
//            }
//        }
//
//        @Override
//        public void onRequestPermissionsResult(int requestCode,
//                                               @NonNull String[] permissions,
//                                               @NonNull int[] grantResults) {
//            sInstance.onRequestPermissionsResult(this);
//            finish();
//        }
//    }
//
//    public interface OnRationaleListener {
//
//        void rationale(ShouldRequest shouldRequest);
//
//        interface ShouldRequest {
//            void again(boolean again);
//        }
//    }
//
//    public interface SimpleCallback {
//        void onGranted();
//
//        void onDenied();
//    }
//
//    public interface FullCallback {
//        void onGranted(List<String> permissionsGranted);
//
//        void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied);
//    }
//
//    public interface ThemeCallback {
//        void onActivityCreate(Activity activity);
//    }
}