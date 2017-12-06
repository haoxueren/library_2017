package com.haoxueren.library;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/** Toast帮助类； */
public class ToastHelper
{
    /** Toast的位置； */
    private static int gravity, xOffset, yOffset;

    /** 设置Toast显示的位置； */
    public static void setGravity(int gravity, int xOffset, int yOffset)
    {
        ToastHelper.xOffset = xOffset;
        ToastHelper.yOffset = yOffset;
        ToastHelper.gravity = gravity;
    }

    /** 弹出一个系统默认Toast的快捷方法 */
    public static void showShortToast(String message)
    {
        Context context = ContextHelper.getContext();
        if (!TextUtils.isEmpty(message))
        {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
        } else
        {
            Toast toast = Toast.makeText(context, "NULL", Toast.LENGTH_SHORT);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
        }
    }

    /** 弹出一个系统默认Toast的快捷方法 */
    public static void showLongToast(String message)
    {
        Context context = ContextHelper.getContext();
        if (!TextUtils.isEmpty(message))
        {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
        } else
        {
            Toast toast = Toast.makeText(context, "NULL", Toast.LENGTH_SHORT);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
        }
    }
}
