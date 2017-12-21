package com.haoxueren.library.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于标识相关联的类
 * Created by Haoxueren on 2017/12/21.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Link {
    Class[] value();
}
