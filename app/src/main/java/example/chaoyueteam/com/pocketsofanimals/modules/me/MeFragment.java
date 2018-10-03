package example.chaoyueteam.com.pocketsofanimals.modules.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.base.BaseFragment;


public class MeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.h_back)
    ImageView hBack;
    @BindView(R.id.h_head)
    ImageView hHead;
    @BindView(R.id.user_line)
    ImageView userLine;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_tel)
    TextView userTel;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.my_signature)
    TextView mySignature;
    @BindView(R.id.alter_pw)
    TextView alterPw;
    @BindView(R.id.my_version)
    TextView myVersion;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        onClick(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(Bundle state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_name:
                Toast.makeText(getActivity(), "我点击了用户名", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_sex:
                Toast.makeText(getActivity(), "我点击了性别", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_signature:
                Toast.makeText(getActivity(), "我点击了个性签名", Toast.LENGTH_SHORT).show();
                break;
            case R.id.alter_pw:
                Toast.makeText(getActivity(), "我点击了修改密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_version:
                Toast.makeText(getActivity(), "我点击了版本", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.user_name, R.id.user_tel, R.id.my_name, R.id.user_sex, R.id.my_signature, R.id.alter_pw, R.id.my_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                break;
            case R.id.user_tel:
                break;
            case R.id.my_name:
                break;
            case R.id.user_sex:
                break;
            case R.id.my_signature:
                break;
            case R.id.alter_pw:
                break;
            case R.id.my_version:
                break;
        }
    }
}
