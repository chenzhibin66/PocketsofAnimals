package example.chaoyueteam.com.pocketsofanimals.modules.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.base.BaseFragment;
import example.chaoyueteam.com.pocketsofanimals.modules.LoginActivity;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MeFragment extends BaseFragment {


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
    @BindView(R.id.im_user)
    ImageView imUser;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.im_sex)
    ImageView imSex;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.im_signature)
    ImageView imSignature;
    @BindView(R.id.my_signature)
    TextView mySignature;
    @BindView(R.id.im_alter_pw)
    ImageView imAlterPw;
    @BindView(R.id.alter_pw)
    TextView alterPw;
    @BindView(R.id.im_version)
    ImageView imVersion;
    @BindView(R.id.my_version)
    TextView myVersion;
    @BindView(R.id.back_login)
    Button backLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setData();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.h_back, R.id.h_head, R.id.user_name, R.id.user_tel, R.id.my_name, R.id.user_sex, R.id.my_signature, R.id.alter_pw, R.id.back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.h_back:
                break;
            case R.id.h_head:
                break;
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
            case R.id.back_login:
                BmobUser.logOut();//清除缓存用户对象
                BmobUser currentUser=BmobUser.getCurrentUser();//现在的currentuser是null
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void setData(){
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new BlurTransformation(getContext(),25),new CenterCrop(getContext()))
                .into(hBack);

        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(getContext())).into(hHead);
    }
}
