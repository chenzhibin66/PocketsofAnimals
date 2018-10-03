package cn.bmob.sdkdemo.sms;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.sdkdemo.BaseActivity;
import cn.bmob.sdkdemo.R;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.datatype.BmobSmsState;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 短信验证码
 *
 * @author smile
 * @class SMSCodeActivity
 * @date 2015-6-4 上午9:48:50
 */
@SuppressLint("SimpleDateFormat")
public class SMSCodeActivity extends BaseActivity {

    EditText et_number, et_code;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        et_number = (EditText) findViewById(R.id.et_number);
        et_code = (EditText) findViewById(R.id.et_code);
        number = et_number.getText().toString().trim();

        mListview = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_item, getResources().getStringArray(R.array.bmob_code_list));
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                testBmob(position + 1);
            }
        });

    }

    private void testBmob(int pos) {
        switch (pos) {
            case 1:
                requestSmsCode();
                break;
            case 2:
                verifySmsCode();
                break;
            case 3:
                querySmsState();
                break;
            case 4:
                requestSms();
                break;

            default:
                break;
        }

    }


    /**
     * 自定义发送短信内容
     *
     * @return void
     * @throws
     * @method requestSmsCode
     */
    private void requestSms() {
        if (!TextUtils.isEmpty(number)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sendTime = format.format(new Date());
            sendTime = "2016-11-09 17:00:13";
            addSubscription(BmobSMS.requestSMS(number, "您的验证码为123456，请及时验证！", sendTime, new QueryListener<Integer>() {

                @Override
                public void done(Integer smsId, BmobException ex) {
                    if (ex == null) {//验证码发送成功
                        toast("验证码发送成功，短信id：" + smsId);//用于查询本次短信发送详情
                    } else {
                        toast("errorCode = " + ex.getErrorCode() + ",errorMsg = " + ex.getLocalizedMessage());
                    }
                }
            }));
        } else {
            toast("请输入手机号码");
        }
    }

    /**
     * 请求短信验证码
     *
     * @return void
     * @throws
     * @method requestSmsCode
     */
    private void requestSmsCode() {
        String number = et_number.getText().toString();
        if (!TextUtils.isEmpty(number)) {
            addSubscription(BmobSMS.requestSMSCode(number, "注册模板", new QueryListener<Integer>() {

                @Override
                public void done(Integer smsId, BmobException ex) {
                    if (ex == null) {//验证码发送成功
//						toast("验证码发送成功，短信id："+smsId);//用于查询本次短信发送详情
                        Log.e(TAG, smsId + "");
                    } else {
                        toast("errorCode = " + ex.getErrorCode() + ",errorMsg = " + ex.getLocalizedMessage());
                    }
                }
            }));
        } else {
            toast("请输入手机号码");
        }
    }

    /**
     * 验证短信验证码
     *
     * @return void
     * @throws
     * @method requestSmsCode
     */
    private void verifySmsCode() {
        String code = et_code.getText().toString();
        String num = et_number.getText().toString();
        if (!TextUtils.isEmpty(num) && !TextUtils.isEmpty(code)) {
            addSubscription(BmobSMS.verifySmsCode(num, code, new UpdateListener() {

                @Override
                public void done(BmobException ex) {
                    if (ex == null) {//短信验证码已验证成功
                        toast("验证通过");
                    } else {
                        toast("验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                    }
                }
            }));
        } else {
            toast("请输入手机号和验证码");
        }
		/*BmobUser.signOrLoginByMobilePhone(number, code, new LogInListener<BmobUser>() {
			@Override
			public void done(BmobUser user, BmobException e) {
				if (e == null) {
				    Log.e(TAG,"" +user.getObjectId());
				} else {
				    Log.e(TAG, e.toString());
				}
			}
		});*/
    }


    /**
     * 查询短信状态
     *
     * @return void
     * @throws
     * @method querySmsState
     */
    private void querySmsState() {
        addSubscription(BmobSMS.querySmsState(39086233, new QueryListener<BmobSmsState>() {

            @Override
            public void done(BmobSmsState state, BmobException ex) {
                if (ex == null) {
                    toast("短信状态：" + state.getSmsState() + ",验证状态：" + state.getVerifyState());
                } else {
                    toast("errorCode = " + ex.getErrorCode() + ",errorMsg = " + ex.getLocalizedMessage());
                }
            }
        }));
    }
}
