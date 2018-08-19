package com.andy.lib_permiss.core;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.andy.lib_permiss.AndyPermissionActivity;
import com.andy.lib_permiss.PermissionUtils;
import com.andy.lib_permiss.annotation.Permission;
import com.andy.lib_permiss.annotation.PermissionCanceled;
import com.andy.lib_permiss.annotation.PermissionDenied;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * aop 工具
 */
@Aspect
public class AndyPermissAspect {
    private static final String TAG = "AndyPermissAspect";

    @Pointcut("execution(@com.andy.lib_permiss.annotation.Permission * *(..)) && @annotation(Permission)")
    public void requestPermission(Permission permissions){

    }
    @Around("requestPermission(permissions)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint,Permission permissions){
        Context context=null;
        final Object object=joinPoint.getThis();
        if (object instanceof Context){
            context=(Context) object;
        }else if (object instanceof Fragment){
            context=((Fragment) object).getActivity();
        }

        if (context == null || permissions == null) {
            Log.d(TAG, "aroundJonitPoint error ");
            return;
        }
        AndyPermissionActivity.requestPermission(context, permissions.value(),permissions.requestCode(), new IPermission() {
            @Override
            public void ganted() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void cancled() {
                PermissionUtils.invokAnnotation(object, PermissionCanceled.class);
            }

            @Override
            public void denied() {
                PermissionUtils.invokAnnotation(object, PermissionDenied.class);
            }
        });
    }

}
