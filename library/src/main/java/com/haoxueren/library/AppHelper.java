package com.haoxueren.library;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Helper for Android App
 * Created by Haoxueren on 2017/12/9.
 */
public class AppHelper {

    /**
     * 启动APP，返回是否打开成功；
     * @return 是否启动成功
     */
    public static boolean launchApp(String pkgname) {
        try {
            Context context = ContextHelper.getContext();
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(pkgname);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据应用包名，跳转到应用市场
     * @param packageName 所需下载（评论）的应用包名
     * @return 跳转成功返回true，出现异常返回false
     */
    public static boolean linkAppMarket(String packageName) {
        try {
            Context context = ContextHelper.getContext();
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
