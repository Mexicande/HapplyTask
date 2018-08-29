package com.example.apple.easyspend.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.LogUtil;
import com.example.apple.easyspend.R;
import com.example.apple.easyspend.common.Contacts;
import com.example.apple.easyspend.dao.AllInfo;
import com.example.apple.easyspend.dao.DaoMaster;
import com.example.apple.easyspend.dao.DaoSession;
import com.example.apple.easyspend.dao.ImagerBean;
import com.example.apple.easyspend.dao.User;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.utils.HeaderParser;
import com.meituan.android.walle.WalleChannelReader;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/6/28.
 */
public class MyApp extends Application {
    public static RequestQueue requestQueue;
    public static RequestQueue getVolleyRequestQueue(){
        return requestQueue;
    }
    //dao
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    public static User user;
    public static AllInfo today;
    public static String today_date = "";

    private static MyApp instance;

    public static SharedPreferences sp;
    private static ImagerBean image;

    public static ImagerBean getImage() {
        return image;
    }

    public static void setImage(ImagerBean image) {
        MyApp.image = image;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initOkGo();
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        sp = super.getSharedPreferences("eSetting", Context.MODE_PRIVATE);//只能被本应用访问
    }

    public static MyApp getApp(){
        return instance;
    }

    private void initOkGo() {
        AVOSCloud.initialize(this, Contacts.LEAN_ID,Contacts.LEAN_KEY);
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this,Contacts.UMENG_KEY
                ,channel));
        HttpHeaders parser=new HttpHeaders();
        parser.put("channel",channel);
        OkGo.getInstance().init(this)
                .addCommonHeaders(parser);
    }
    public DaoMaster getDaoMaster(Context context){
        if (daoMaster == null){
            DaoMaster.OpenHelper helper =
                    new DaoMaster.DevOpenHelper(context,"easywork.db",null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }
    public DaoSession getDaoSession(Context context){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    List<Activity> myActivity = new ArrayList<>();
    public void addToList(Activity activity){
        myActivity.add(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (Activity activity : myActivity){
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
