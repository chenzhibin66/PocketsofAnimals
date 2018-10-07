package example.chaoyueteam.com.pocketsofanimals.modules.discover;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import example.chaoyueteam.com.pocketsofanimals.Adapter.AnimalsAdapter;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.base.BaseFragment;
import example.chaoyueteam.com.pocketsofanimals.db.AnimalIntroduction;

public class DiscoverFragment extends BaseFragment {
    public AnimalsAdapter adapter;
    @BindView(R.id.animal_title)
    TextView animalTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<AnimalIntroduction> mAnimals = new ArrayList<>();
    private GridLayoutManager manager;


    Unbinder unbinder;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(Bundle state) {
        Bmob.initialize(getContext(), "e3f7e3dcd335515e9aa1040d7067bace");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });
        thread.start();
        initRecyclerView();
    }

    public static BaseFragment getInstance() {
        return new DiscoverFragment();
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

    /**
     * 实例化recyclerview
     */
    protected void initRecyclerView() {
        adapter = new AnimalsAdapter(mAnimals);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        adapter.isFirstOnly(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        manager = new GridLayoutManager(getContext(), 2);
        adapter.setHeaderViewAsFlow(false);
        recyclerView.setLayoutManager(manager);
    }


    private void loadData() {
        BmobQuery<AnimalIntroduction> query = new BmobQuery<AnimalIntroduction>();
        query.findObjects(new FindListener<AnimalIntroduction>() {
            @Override
            public void done(List<AnimalIntroduction> object, BmobException e) {
                if (e == null) {
                    for (AnimalIntroduction animalIntroduction : object) {
                        String animalName = animalIntroduction.getAnimalname();
                        BmobFile animalPhoto = animalIntroduction.getAnimalPhoto();
                        String animalIntroduce =animalIntroduction.getIntroduce();
                        AnimalIntroduction animalIntroduction1 = new AnimalIntroduction(animalPhoto, animalName,animalIntroduce);
                        mAnimals.add(animalIntroduction1);
                    }

                    adapter.notifyDataSetChanged();
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


}
