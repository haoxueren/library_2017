package com.haoxueren.library;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;

import java.util.Collections;
import java.util.List;

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
    public static boolean launchAppMarket(String packageName) {
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


    /** 打开应用设置界面； */
    public static void launchSettings(String packageName) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", packageName, null));
        Context context = ContextHelper.getContext();
        context.startActivity(intent);
    }

    // 获取已安装应用的信息；
    public static void getInstalledAppList() {
        PackageManager packageManager = ContextHelper.getContext().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
        // 按应用名称排序；
        Collections.sort(resolveInfoList, new ResolveInfo.DisplayNameComparator(packageManager));
        for (ResolveInfo resolveInfo : resolveInfoList) {
            // 应用名称；
            String appName = resolveInfo.loadLabel(packageManager).toString();
            // 应用图标；
            Drawable icon = resolveInfoList.get(0).loadIcon(packageManager);
            // 应用包名；
            String packageName = resolveInfo.activityInfo.packageName;
            MyLog.info(appName, packageName, icon.toString());
        }
    }


    /** 获取APP图标； */
    public static Drawable getAppIcon(String packageName) {
        // 通过API从应用内获取；
        Context context = ContextHelper.getContext();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        if (intent != null) {
            List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            return resolveInfo.loadIcon(packageManager);
        }
        return new BitmapDrawable();
    }


    /**
     * 判断一个程序是否显示在前端(根据测试此方法执行效率在11毫秒,无需担心此方法的执行效率)；
     */
    public static boolean isAppForeground(String packageName) {
        Context context = ContextHelper.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessList = activityManager.getRunningAppProcesses();
        if (runningAppProcessList == null) return false;
        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcessList) {
            if (runningAppProcess.processName.equals(packageName)) {
                int status = runningAppProcess.importance;
                boolean isVisible = (status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE);
                boolean isForeground = (status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND);
                return isVisible || isForeground;
            }
        }
        return false;
    }

    /** 判断APP是否在后台； */
    public static boolean isAppBackground(String packageName) {
        Context context = ContextHelper.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : processList) {
            if (process.processName.equals(packageName)) {
                return (process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND);
            }
        }
        return false;
    }


    /** 重启APP； */
    public static void restartApp(String packageName) {
        Context context = ContextHelper.getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }


    /** 卸载APP的方法； */
    public void uninstallApp(String packageName) {
        Intent intent = new Intent();
        //获取删除包名的URI
        Uri uri = Uri.parse("package:" + packageName);
        //设置我们要执行的卸载动作；
        intent.setAction(Intent.ACTION_DELETE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置获取到的URI
        intent.setData(uri);
        // 启动卸载界面；
        Context context = ContextHelper.getContext();
        context.startActivity(intent);
    }


    /** 使用隐式Intent调用浏览器打开网址； */
    public static void launcherBrowser(String url) {
        Context context = ContextHelper.getContext();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
