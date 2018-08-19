package com.andy.lib_permiss.annotation;

import com.andy.lib_permiss.PermissionUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by apple on 18/8/12.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionDenied {
    int requestCode() default PermissionUtils.DEFAULT_REQUEST_CODE;
}
