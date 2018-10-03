package cn.bmob.sdkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


import cn.bmob.v3.exception.BmobException;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends Activity {

	public static String TAG = "bmob";
	protected ListView mListview;
	protected BaseAdapter mAdapter;
	//Observable(被观察者)在subscribe()之后会持有 Subscriber(Observer的) 的引用，这个引用如果不能及时被释放，将有内存泄露的风险
	private CompositeSubscription mCompositeSubscription;

	/**
	 * 解决Subscription内存泄露问题
	 * @param s
     */
	protected void addSubscription(Subscription s) {
		if (this.mCompositeSubscription == null) {
			this.mCompositeSubscription = new CompositeSubscription();
		}
		this.mCompositeSubscription.add(s);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	Toast mToast;

	public void showToast(String text) {
		if (!TextUtils.isEmpty(text)) {
			if (mToast == null) {
				mToast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(text);
			}
			mToast.show();
		}
	}


	public void showToast(int resId) {
		if (mToast == null) {
			mToast = Toast.makeText(getApplicationContext(), resId,
					Toast.LENGTH_SHORT);
		} else {
			mToast.setText(resId);
		}
		mToast.show();
	}
	
	public static void log(String msg) {
		Log.i(TAG,"===============================================================================");
		Log.i(TAG, msg);
	}

	public static void loge(Throwable e) {
		Log.i(TAG,"===============================================================================");
		if(e instanceof BmobException){
			Log.e(TAG, "错误码："+((BmobException)e).getErrorCode()+",错误描述："+((BmobException)e).getMessage());
		}else{
			Log.e(TAG, "错误描述："+e.getMessage());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//取消订阅，防止rx内存泄露
		if (this.mCompositeSubscription != null) {
			this.mCompositeSubscription.unsubscribe();
		}
	}

}
