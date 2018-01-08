package com.haoxueren.library;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间/日期 帮助类
 * Created by Haoxueren on 2017/12/24.
 */
public class DateHelper {

    public static String format(Date date, String pattern) {
        if (date == null) {
            return "date is null";
        }
        if (TextUtils.isEmpty(pattern)) {
            return "empty pattern";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        return dateFormat.format(date);
    }

    /** 获取当前时间字符串； */
    public static String formatToday(String pattern) {
        return DateHelper.format(new Date(), pattern);
    }
}
