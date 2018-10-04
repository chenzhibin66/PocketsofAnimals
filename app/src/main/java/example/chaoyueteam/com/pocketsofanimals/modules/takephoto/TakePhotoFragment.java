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
    private ImageView importAlbum;

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
        importAlbum=(ImageView)view.findViewById(R.id.import_album) ;
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
                getActivity().finish();
                break;

            case R.id.record_photo:
                Intent intent1=new Intent(getActivity(),ShowAnimalsActivity.class);
                getActivity().startActivity(intent1);
                getActivity().finish();
                break;

            case R.id.import_album:

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


    public static void saveImageToAlbum(Context context, Bitmap bitmap,String picName){
        String fileName=null;
        //系统相册目录
        String albumPath=Environment.getExternalStorageDirectory() + File.separator +Environment.DIRECTORY_DCIM
                +File.separator +"Camera" +File.separator;
        //声明文件对象
        File file=null;
        //声明输出流
        FileOutputStream outputStream=null;
        try{
            //如果有目标文件，直接获得文件对象，否贼创建一个以filename为名称的文件
            file=new File(albumPath,picName+".jpg");

            //获得文件相对路径
            fileName=file.toString();

            //获得输出流，如果文件有内容，追加内容
            outputStream=new FileOutputStream(fileName);
            if (outputStream!=null){
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            try{
                if (outputStream!=null){
                    outputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //通知相册更新
        MediaStore.Images.Media.insertImage(context.getContentResolver(),bitmap,fileName,null);
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri=Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

}
