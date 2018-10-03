package cn.bmob.sdkdemo.crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import cn.bmob.sdkdemo.BaseActivity;
import cn.bmob.sdkdemo.R;
import cn.bmob.sdkdemo.bean.BankCard;
import cn.bmob.sdkdemo.bean.Person;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 增删改查
 */
public class CRUDActivity extends BaseActivity {

private String objId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListview = (ListView) findViewById(R.id.listview);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.tv_item, getResources().getStringArray(
						R.array.bmob_crud_list));
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
			testinsertObject();
			break;
		case 2:
			testUpdateObject();
			break;
		case 3:
			testDeleteObject();
			break;
		case 4:
			startActivity(new Intent(this, QueryActivity.class));
			break;
		}
	}


	/**
	 * 插入对象
	 */
	private void testinsertObject() {

		Person coder = new Person();
		coder.setName("野原新之助");
		coder.save(new SaveListener<String>() {
			@Override
			public void done(String s, BmobException e) {
				if (e == null) {
					objId = s;
					log("currentThread is " + Thread.currentThread().getName());
					toast("ok ");
				} else {
					Log.e(TAG, e.toString());
				}
			}
		});

	}


	/**
	 * 更新对象
	 */
	private void testUpdateObject() {
		Person coder = new Person();
		coder.setAge(24);
		coder.update(objId, new UpdateListener() {
			@Override
			public void done(BmobException e) {
				if (e == null) {
				    toast("update ok ");
				} else {
				    Log.e(TAG, e.toString());
				}
			}
		});
	}

	/**
	 * 删除对象
	 */
	private void testDeleteObject() {
		Person person = new Person();
		person.removeAll("cards", Arrays.asList(new BankCard("建行", "111")));
		person.setObjectId("56b6b2298d");
		addSubscription(person.update(new UpdateListener() {
			@Override
			public void done(BmobException e) {
				if(e==null){
					log("删除成功");
				}else{
					loge(e);
				}
			}
		}));
	}
}
