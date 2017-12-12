package com.haoxueren.library;

/**
 * 帮助类：监听连续发生的事件组；<br>
 * 功能：实现连按两次返回键退出；<br>
 * 示例：DoubleClickManager.create(this).registerEvent(200);
 */
public class DoubleClickHelper {

    /** 事件首次被触发的时间； */
    private long firstEventTime = 0;

    /** 事件被触发的监听器； */
    private OnDoubleClickListener listener;

    private static DoubleClickHelper doubleClickHelper;

    /** 注入事件监听器； */
    private DoubleClickHelper(OnDoubleClickListener listener) {
        this.listener = listener;
    }

    public static DoubleClickHelper create(OnDoubleClickListener listener) {
        if (doubleClickHelper == null) {
            doubleClickHelper = new DoubleClickHelper(listener);
        }
        return doubleClickHelper;
    }


    /**
     * 判断两次事件的时间间隔(ms)，小于间隔时间则回调接口；
     */
    public void registerEvent(int intervalTime) {
        // 判断两次间隔时间是否满足条件；
        if (System.currentTimeMillis() - firstEventTime > intervalTime) {
            // 记录事件首次被触发的时间；
            firstEventTime = System.currentTimeMillis();
            listener.onFirstClick();
        } else {
            firstEventTime = 0;
            listener.onSecondClick();
        }
    }

    /**
     * 事件被触发的监听器；
     */
    public interface OnDoubleClickListener {

        /** 第一次点击 */
        void onFirstClick();

        /** 第二次点击 */
        void onSecondClick();
    }

}
