package com.haoxueren.library;

import java.util.List;

/**
 * Helper for List
 * Created by Haoxueren on 2017/12/26.
 */
public class ListHelper {

    /** 判断集合是否为空 */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }
}
