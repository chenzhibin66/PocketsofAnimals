package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.base.BaseFragment;
import example.chaoyueteam.com.pocketsofanimals.utils.PermissionUtils;


public class TakePhotoFragment extends BaseFragment implements View.OnClickListener {
    static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private ImageView takePhoto;
    private ImageView recordPhoto;

    @Override
    public int getLayoutResId(){
        return R.layout.fragment_take_photo;
    }

    @Override
    public void initView(Bundle state) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        takePhoto = (ImageView) view.findViewById(R.id.take_photo);
        recordPhoto = (ImageView) view.findViewById(R.id.record_photo);
        takePhoto.setOnClickListener(this);
        recordPhoto.setOnClickListener(this);
        askPermission();
        view.getBackground().setAlpha(120);
        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.take_photo:
                Intent intent=new Intent(getActivity(),TakePhotoActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.record_photo:
                break;
        }
    }
    /**
     * 请求权限
     *
     */
    private void askPermission() {
        PermissionUtils.requestCameraPermission(getActivity());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(getActivity(),"赋予权限成功",Toast.LENGTH_SHORT).show();
    }

}
