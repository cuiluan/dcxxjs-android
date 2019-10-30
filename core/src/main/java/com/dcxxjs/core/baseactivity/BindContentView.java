package com.dcxxjs.core.baseactivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/30
 * 功能描述：扩展
 * 联系方式：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindContentView {
    int value();
}

