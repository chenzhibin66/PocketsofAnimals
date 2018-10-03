package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.take_photo:
                Intent intent=new Intent(getActivity(),TakePhotoActivity.class);
                getActivity().startActivity(intent);

            case R.id.record_photo:

        }
    }


    /**
     * 请求权限
     * 你都没调用大哥
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
