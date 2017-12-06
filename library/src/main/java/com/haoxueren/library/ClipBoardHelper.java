package com.haoxueren.library;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Android系统剪贴板帮助类；
 * 参考资料：http://www.cnblogs.com/exmyth/p/4603496.html
 */
public class ClipBoardHelper {
    /**
     * 复制文本内容到剪贴板；
     */
    public static void clipText(CharSequence text) {
        Context context = ContextHelper.getContext();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(clipData);
    }

    /**
     * 获取粘贴板文字；
     */
    public static CharSequence getClipText(int index) {
        Context context = ContextHelper.getContext();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        if (primaryClip == null) return null;
        return primaryClip.getItemAt(index).getText();
    }

}
