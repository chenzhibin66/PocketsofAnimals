package example.chaoyueteam.com.pocketsofanimals.modules.me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.base.BaseFragment;
import example.chaoyueteam.com.pocketsofanimals.db.MyUser;
import example.chaoyueteam.com.pocketsofanimals.modules.LoginActivity;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MeFragment extends BaseFragment {
    MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
    private String TAG = "MainActivity";
    private String[] sexArry = new String[]{"男", "女"};
    Unbinder unbinder;
    @BindView(R.id.h_head)
    ImageView hHead;
    @BindView(R.id.my_nick)
    TextView myNick;
    @BindView(R.id.show_nick)
    TextView showNick;
    @BindView(R.id.im_sex)
    ImageView imSex;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.show_sex)
    TextView showSex;
    @BindView(R.id.im_signature)
    ImageView imSignature;
    @BindView(R.id.my_signature)
    TextView mySignature;
    @BindView(R.id.show_signature)
    TextView showSignature;
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
    @BindView(R.id.h_back)
    ImageView hBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mTimeHandler.sendEmptyMessageDelayed(0, 1000);
        setData();
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(Bundle state) {
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        showNick.setText(myUser.getNick());
        showSignature.setText(myUser.getSignature());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setData() {
        Glide.with(this).load(R.drawable.ic_head)
                .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                .into(hBack);
        Glide.with(this).load(R.drawable.ic_head)
                .bitmapTransform(new CropCircleTransformation(getContext())).into(hHead);
    }

    @OnClick({R.id.my_nick, R.id.user_sex, R.id.my_signature, R.id.alter_pw, R.id.back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_nick:
                Intent intent = new Intent(getActivity(), NickActtivity.class);
                startActivity(intent);
                break;
            case R.id.user_sex:
                showSexChooseDialog();
                break;
            case R.id.my_signature:
                Intent intent1 = new Intent(getActivity(), SignatureActivity.class);
                startActivity(intent1);
                break;
            case R.id.alter_pw:
                Intent intent2 = new Intent(getActivity(), AlterPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.back_login:
                BmobUser.logOut();//清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser();//现在的currentuser是null
                Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent3);
                getActivity().finish();
                break;
        }
    }

    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(getContext());// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                myUser.setSex(sexArry[which]);
                myUser.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {

                        } else {
                            Toast.makeText(getContext(), "更新失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Log.d(TAG, "onClick: " + myUser.getSex());
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }
    Handler mTimeHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                showSex.setText(myUser.getSex());
                sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
}
