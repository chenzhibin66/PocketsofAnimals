package example.chaoyueteam.com.pocketsofanimals.modules;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.base.BaseActivity;
import example.chaoyueteam.com.pocketsofanimals.modules.discover.DiscoverFragment;
import example.chaoyueteam.com.pocketsofanimals.modules.location.LocationFragment;
import example.chaoyueteam.com.pocketsofanimals.modules.me.MeFragment;
import example.chaoyueteam.com.pocketsofanimals.modules.takephoto.TakePhotoFragment;

public class MainActivity extends BaseActivity {
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private TakePhotoFragment takephotoFragment;
    private LocationFragment locationFragment;
    private DiscoverFragment discoverFragment;
    private MeFragment meFragment;
    private Fragment[] fragments;
    private int lastfragment;

    private FragmentTransaction transaction;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(getLayoutId());
//    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        takephotoFragment = new TakePhotoFragment();
        locationFragment = new LocationFragment();
        discoverFragment = new DiscoverFragment();
        meFragment = new MeFragment();
        fragments = new Fragment[]{takephotoFragment, locationFragment, discoverFragment, meFragment};
        lastfragment = 0;
        switchFragment(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_camera:
                    if (lastfragment != 0) {
                        switchFragment(0);
                        lastfragment = 0;
                    }
                    return true;
                case R.id.navigation_location:
                    if (lastfragment != 1) {
                        switchFragment(1);
                        lastfragment = 1;
                    }
                    return true;
                case R.id.navigation_discover:
                    if (lastfragment != 2) {
                        switchFragment(2);
                        lastfragment = 2;
                    }
                    return true;
                case R.id.navigation_me:
                    if (lastfragment != 3) {
                        switchFragment(3);
                        lastfragment = 3;
                    }
                    return true;
            }
            return false;
        }
    };

    /**
     * 切换Fragment
     */
    private void switchFragment(int index) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainview, fragments[index])
                .commit();
        lastfragment = index;
    }




}
