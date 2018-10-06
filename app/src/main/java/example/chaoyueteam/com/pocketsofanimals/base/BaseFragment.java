package example.chaoyueteam.com.pocketsofanimals.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    private View view;
    private FragmentActivity mFragmentActivity;

    public abstract int getLayoutResId();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResId(), container, false);
        mFragmentActivity=getSupportActivity();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedInstanceState);
    }

    /**
     * 初始化UI
     *
     * @param state
     */
    public abstract void initView(Bundle state);

    @Override
    public void onResume() {
        super.onResume();
    }

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    /**
     * Fragment的隐藏
     */
    protected void onInvisible() {

    }

    /**
     * 初始化recyclerView
     */
    protected void initRecyclerView() {

    }

}
