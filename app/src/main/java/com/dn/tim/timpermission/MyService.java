package com.dn.tim.timpermission;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.andy.lib_permiss.annotation.Permission;
import com.andy.lib_permiss.annotation.PermissionCanceled;
import com.andy.lib_permiss.annotation.PermissionDenied;

/**
 * @date 创建时间：2018/4/18
 * @description
 */

public class MyService extends Service {

    private static final String TAG = "MyService";
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        requestCamera();

        return super.onStartCommand(intent, flags, startId);
    }

    @Permission(Manifest.permission.CAMERA)
    private void requestCamera() {
        Toast.makeText(getApplicationContext(),"SERVICE中请求权限——通过",Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied()
    private void deny() {
        Log.i(TAG, "SERVICE中请求权限_writeDeny:" );
        Toast.makeText(this, "SERVICE中请求权限_deny", Toast.LENGTH_SHORT).show();

    }

    @PermissionCanceled()
    private void cancel() {
        Log.i(TAG, "SERVICE中请求权限_writeCancel: " );
        Toast.makeText(this, "SERVICE中请求权限_cancel", Toast.LENGTH_SHORT).show();
    }

}
