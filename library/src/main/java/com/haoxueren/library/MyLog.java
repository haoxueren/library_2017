package com.haoxueren.library;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 一款小巧强大的日志输出工具；
 * @author Haoxueren
 */
public class MyLog {
    /** 默认打开日志； */
    public static boolean debug = true;
    public static String tag = "Haoxueren";

    /** true表示打开日志，false表示关闭日志 */
    public static void setDebug(boolean debug) {
        MyLog.debug = debug;
    }

    /** 设置日志的TAG； */
    public static void setTag(String tag) {
        MyLog.tag = tag;
    }

    /** 输出普通日志信息 */
    public static void info(Object... messages) {
        MyLog.info(debug, tag, messages);
    }

    /** 输出普通日志信息 */
    public static void info(boolean debug, String tag, Object... message) {
        if (debug) {
            ThreadInfo thread = new ThreadInfo();
            String header = thread.getClassName() + "." + thread.getMethodName() + "()";
            Log.d(tag, header);
            Log.i(tag, "#" + thread.getLineNumber() + " --> " + Arrays.toString(message));
        }
    }

    /** 输出捕获异常信息 */
    public static void error(Object... message) {
        if (debug) {
            ThreadInfo thread = new ThreadInfo();
            String header = thread.getClassName() + "." + thread.getMethodName() + "()";
            Log.d(tag, header);
            Log.e(tag, "#" + thread.getLineNumber() + " --> " + Arrays.toString(message));
        }
    }

    /** 输出捕获异常信息 */
    public static void error(Throwable throwable) {
        if (debug) {
            StackTraceElement[] elements = throwable.getStackTrace();
            Log.e(tag, throwable.getClass().getSimpleName() + "：" + throwable.getMessage());
            for (StackTraceElement element : elements) {
                Log.w(tag, element.getClassName() + "." + element.getMethodName() + "(Line: " + element.getLineNumber
                        () + ")");
            }
        }
    }

    /** 打印参数列表 */
    /** 输出普通日志信息 */
    public static void params(Object... message) {
        if (debug) {
            ThreadInfo thread = new ThreadInfo();
            Log.i("method.params", thread.getClassName());
            Log.d("method.params", "#" + thread.getLineNumber() + " " + thread.getMethodName() + "()");
            Log.d("method.params", Arrays.toString(message));
        }
    }

}
