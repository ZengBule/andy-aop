package com.dn.tim.timpermission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andy.lib_permiss.annotation.Permission;
import com.andy.lib_permiss.annotation.PermissionCanceled;
import com.andy.lib_permiss.annotation.PermissionDenied;


/**
 * @date 创建时间：2018/4/18
 * @description
 */

public class MyFragment extends Fragment {

    private static final String TAG = "MyFragment";
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permission, container, false);
        button = view.findViewById(R.id.btn_fragment);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLocation();
            }
        });
    }

    @Permission(Manifest.permission.CAMERA)
    private void requestLocation() {
        Toast.makeText(getContext(), "Fragment中请求权限——通过", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied()
    private void deny() {
        Log.i(TAG, "Fragment中请求权限_writeDeny");
        Toast.makeText(getContext(), "Fragment中请求权限_deny", Toast.LENGTH_SHORT).show();
    }

    @PermissionCanceled()
    private void cancel() {
        Log.i(TAG, "Fragment中请求权限_writeCancel: " );
        Toast.makeText(getContext(), "Fragment中请求权限_cancel", Toast.LENGTH_SHORT).show();
    }
}
