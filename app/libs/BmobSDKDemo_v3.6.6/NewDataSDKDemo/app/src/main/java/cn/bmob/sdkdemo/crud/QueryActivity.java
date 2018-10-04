package cn.bmob.sdkdemo.crud;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.bmob.sdkdemo.BaseActivity;
import cn.bmob.sdkdemo.R;
import cn.bmob.sdkdemo.bean.Person;
import cn.bmob.sdkdemo.file.Movie;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;
import rx.Subscription;

/**
 * 查询数据
 */
@SuppressLint("SimpleDateFormat")
public class QueryActivity extends BaseActivity {

    protected ListView mListview;
    protected BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mListview = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_item, getResources().getStringArray(
                R.array.bmob_findtest_list));
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                testFind(position + 1);
            }
        });
    }


    private void testFind(int pos) {
        switch (pos) {
            case 1:
                query();
                break;
            case 2:
                queryObjects();
                break;
            case 3:
                countObjects();
                break;
            case 4:
                compositeAndQuery();
                break;
            case 5:
                compositeOrQuery();
                break;
            case 6:
                queryArrays();
                break;
        }
    }


    public void query() {
        BmobQuery<Person> query = new BmobQuery<>();
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if (e == null) {
                    for (Person person : object) {
                        log(person.toString());
                    }
                } else {
                    Log.e(TAG, e.toString());
                }
            }
        });

    }

    /**
     * 根据表名查询多条数据
     */
    public void queryObjectsByTable() {
        BmobQuery query = new BmobQuery("Person");
        query.addWhereEqualTo("age", 25);
        query.setLimit(2);
        query.order("createdAt");
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                if (e == null) {
                    log(ary.toString());
                } else {
                    loge(e);
                }
            }
        });
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray array, BmobException e) {
                if (e == null) {
                    toast("ok ");
                } else {
                    Log.e(TAG, e.toString());
                }
            }
        });


    }

    public void queryObjects() {
        BmobQuery query = new BmobQuery("Qunimade");
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray array, BmobException e) {
                if (e == null) {
                    log(array.toString());
                } else {
                    Log.e(TAG, e.toString());
                }
            }
        });

    }

    /**
     * 根据表名查询单条数据
     */
    public void queryOneByTable() {
        BmobQuery query = new BmobQuery("Movie");
        addSubscription(query.getObjectByTable("5f1042e183", new QueryListener<JSONObject>() {
            @Override
            public void done(JSONObject jsonObject, BmobException e) {
                if (e == null) {
                    log(jsonObject.toString());
                } else {
                    loge(e);
                }
            }
        }));
    }

    public void queryOne() {
        BmobQuery<Movie> query = new BmobQuery();
        addSubscription(query.getObject("5f1042e183", new QueryListener<Movie>() {

            @Override
            public void done(Movie movie, BmobException e) {
                if (e == null) {
                    log(movie.getFile().getFileUrl());
                } else {
                    loge(e);
                }
            }
        }));
    }

    /**
     * 查询2015-05-01当天的Person数据
     *
     * @return void
     * @throws
     * @method queryObject
     */
    public void queryDate() {
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        //查询2015-05-01当天的Person数据
        List<BmobQuery<Person>> and = new ArrayList<BmobQuery<Person>>();
        BmobQuery<Person> q1 = new BmobQuery<Person>();
        String start = "2016-03-23 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        q1.addWhereGreaterThanOrEqualTo("createdAt", new BmobDate(date));
        and.add(q1);

        BmobQuery<Person> q2 = new BmobQuery<Person>();
        String end = "2016-03-23 15:55:00";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = sdf1.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        q2.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date1));
        and.add(q2);
        bmobQuery.and(and);
        bmobQuery.setLimit(10);
        bmobQuery.order("-createdAt");
        boolean cache = bmobQuery.hasCachedResult(Person.class);
        toast("是否有缓存：" + cache);
        bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);    // 先从缓存取数据，如果没有的话，再从网络取。
        addSubscription(bmobQuery.findObjects(new FindListener<Person>() {

            @Override
            public void done(List<Person> objects, BmobException e) {
                if (e == null) {
                    if (objects != null && objects.size() > 0) {
                        toast("查询成功：" + objects.size() + "个");
                    }
                } else {
                    loge(e);
                }
            }

        }));
    }

    Subscription subscription;

    public void countObjects() {
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();

        subscription = bmobQuery.count(Person.class, new CountListener() {

            @Override
            public void done(Integer count, BmobException e) {
                if (e == null) {
                    toast("count对象个数为：" + count);
                } else {
                    loge(e);
                }
            }
        });
        addSubscription(subscription);
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 复合与查询：查询年龄6-29岁之间，且姓名以"y"或者"e"结尾的人
     */
    private void compositeAndQuery() {
        //查询年龄6-29岁之间的人，每一个查询条件都需要new一个BmobQuery对象
        //--and条件1
        BmobQuery<Person> eq1 = new BmobQuery<Person>();
        eq1.addWhereLessThanOrEqualTo("age", 29);//年龄<=29
        //--and条件2
        BmobQuery<Person> eq2 = new BmobQuery<Person>();
        eq2.addWhereGreaterThanOrEqualTo("age", 6);//年龄>=6

        //查询姓名以"y"或者"e"结尾的人--这个需要使用到or查询
        //--and条件3
        BmobQuery<Person> eq3 = new BmobQuery<Person>();
        eq3.addWhereEndsWith("name", "y");
        BmobQuery<Person> eq4 = new BmobQuery<Person>();
        eq4.addWhereEndsWith("name", "e");
        List<BmobQuery<Person>> queries = new ArrayList<BmobQuery<Person>>();
        queries.add(eq3);
        queries.add(eq4);
        BmobQuery<Person> mainQuery = new BmobQuery<Person>();
        BmobQuery<Person> or = mainQuery.or(queries);

        //最后组装完整的and条件
        List<BmobQuery<Person>> andQuerys = new ArrayList<BmobQuery<Person>>();
        andQuerys.add(eq1);
        andQuerys.add(eq2);
        andQuerys.add(or);
        //查询符合整个and条件的人
        BmobQuery<Person> query = new BmobQuery<Person>();
        query.and(andQuerys);
        addSubscription(query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if (e == null) {
                    toast("查询年龄6-29岁之间，姓名以'y'或者'e'结尾的人个数：" + object.size());
                } else {
                    loge(e);
                }
            }


        }));


    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 复合或查询：查询name字段有值且不为"",查询age 等于 29 或者 age 等于 6 的人
     */
    private void compositeOrQuery() {
//		BmobQuery<Person> and1 = new BmobQuery<Person>();
//		and1.addWhereExists("name");
//		BmobQuery<Person> and2 = new BmobQuery<Person>();
//		and2.addWhereNotEqualTo("name", "");
//		List<BmobQuery<Person>> ands = new ArrayList<BmobQuery<Person>>();
//		ands.add(and1);
//		ands.add(and2);
        BmobQuery<Person> or1 = new BmobQuery<Person>();
        or1.addWhereMatches("name", "luck");
        BmobQuery<Person> or2 = new BmobQuery<Person>();
        or2.addWhereMatches("address", "北京");
        List<BmobQuery<Person>> ors = new ArrayList<BmobQuery<Person>>();
        ors.add(or1);
        ors.add(or2);
        BmobQuery<Person> mainQuery = new BmobQuery<Person>();
//		mainQuery.order("-createdAt");
//		mainQuery.and(ands);
        mainQuery.or(ors);
        addSubscription(mainQuery.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if (e == null) {
                    toast("年龄为29或者6岁人的个数：" + object.size());
                } else {
                    loge(e);
                }
            }

        }));
    }

    /**
     * 数组查询
     */
    public void queryArrays() {
        BmobQuery<Person> query = new BmobQuery<Person>();
        //查询喜欢看书的人
        String[] hobby = {"看书"};
        query.addWhereContainsAll("hobby", Arrays.asList(hobby));
//        query.addWhereDoesNotExists("hobby");
        addSubscription(query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if (e == null) {
                    toast("查询成功：共" + object.size() + "条数据。");
                } else {
                    loge(e);
                }
            }

        }));

        // 数组查询
        query.addWhereContainsAll("", Arrays.asList(""));
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {

            }
        });
    }

    private void qSql() {
        // bql 不能除了查询之外的操作
        String sql = "select * from Person limit 600";
        new BmobQuery<Person>().doSQLQuery(sql, new SQLQueryListener<Person>() {
            @Override
            public void done(BmobQueryResult<Person> t, BmobException e) {
                if (e == null) {
                    log(t.getResults().size() + " size ");
                } else {
                    Log.e(TAG, e.toString());
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
