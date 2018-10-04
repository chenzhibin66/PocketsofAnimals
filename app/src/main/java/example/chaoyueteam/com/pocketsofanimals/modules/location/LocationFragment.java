package example.chaoyueteam.com.pocketsofanimals.modules.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.base.BaseFragment;
import example.chaoyueteam.com.pocketsofanimals.map.MapAcitvity;

public class LocationFragment extends BaseFragment {

    public LocationClient mLocationClient;
    @BindView(R.id.bmapView)
    MapView bmapView;
    Unbinder unbinder;
    //创建LocationClient实例，LocationClient的构造函数接收一个Context参数

    private BaiduMap baiduMap;


    //为了防止多次调用animateMapStatus方法，因为将地图移动到我们当前的位置只需要在程序第一次定位的时候调用一次
    private boolean isFirstLocation = true;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_location;
    }

    @Override
    public void initView(Bundle state) {

        mLocationClient = new LocationClient(getContext().getApplicationContext());
        //getApplicationContext()获取全局Context参数并传入
        mLocationClient.registerLocationListener((BDLocationListener) new MyLocationListener());
        //注册监听器，当获取到位置信息时就会回调这个位置监听器
        SDKInitializer.initialize(getContext().getApplicationContext());
        //初始化，在setContentView前实现，否则回出错，使地图显示出来
//        setContentView(R.layout.fragment_location);


        baiduMap = bmapView.getMap();

        baiduMap.setMyLocationEnabled(true);

        //一次性申请三个权限：创建一个空的List集合，然后依次判断这三个权限有没有被授权，如果没有的话就添加到List集合中，
        //最后将List集合转化为数组，再调用ActivityCompat.requestPermissions一次申请三个权限。
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!permissionList.isEmpty()) {
            String[] permission = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(), permission, 1);
        } else {
            requestLocation();
        }
    }

    //navigateTo方法：先将BDLocation对象中的地理位置信息取出并封装到LatLng中，
    //然后调用MapStatusUpdateFactory的newLatLng方法并将LatLng对象传入，
    //将返回的MapStatusUpdate对象作为参数传入BaiduMap的animateMapStatus中
    private void navigateTo(BDLocation location) {
        if (isFirstLocation) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(18f);
            //18f时，显示50m/1cm，17f时，显示100m/1cm
            baiduMap.animateMapStatus(update);
            isFirstLocation = false;
        }

        //将Location中包含的经度和纬度分别封装到MyLocationData.Builder中
        //然后把MyLocationData设置到了BaiduMap的setMyLocationData方法中
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    @Override
    public void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    //
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //每5秒更新一下当前位置
        option.setScanSpan(5000);
        //获取当前位置详细的信息地址
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    //调用LocationClient的start()方法进行定位
    //定位的结果会回调到前面注册的监听器中，也就是MyLocationListener
    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    //销毁活动
    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        bmapView.onDestroy();
    }

    //对权限申请结果的逻辑处理
    //通过一个循环对申请的每个权限都进行判断，如果有任何一个权限被拒绝，则直接调用finish()方法关闭当前程序
    //只有当所有权限都被同意了后才会调用requestLocation()方法开始地理位置定位
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int resault : grantResults) {
                        if (resault != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity(),
                                    "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(getActivity(), "发生未知错误", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                break;
            default:
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //getLocType()获取当前定位方式
            if (location.getLocType() == BDLocation.TypeGpsLocation ||
                    location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
            currentPosition.append("国家：").append(location.getCountry()).append("\n");
            currentPosition.append("省：").append(location.getProvince()).append("\n");
            currentPosition.append("市：").append(location.getCity()).append("\n");
            currentPosition.append("区：").append(location.getDistrict()).append("\n");
            currentPosition.append("街道：").append(location.getStreet()).append("\n");
            currentPosition.append("定位方式");
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            }
            Toast.makeText(getActivity(), currentPosition, Toast.LENGTH_SHORT).show();

        }
    }
}

