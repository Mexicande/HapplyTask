package com.cn.heres.cashmoney.utils;


import com.cn.heres.cashmoney.R;
import com.cn.heres.cashmoney.activity.MyApp;
import com.cn.heres.cashmoney.common.Api;
import com.cn.heres.cashmoney.common.ApiService;
import com.cn.heres.cashmoney.common.Contacts;
import com.cn.heres.cashmoney.common.OnRequestDataListener;
import com.cn.heres.cashmoney.common.SPUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.meituan.android.walle.WalleChannelReader;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by tantan on 2017/7/14.
 */

public class BrowsingHistory {
    public  void execute(final String prdId) {

        String token = SPUtil.getString(Contacts.TOKEN);
        String channel = WalleChannelReader.getChannel(MyApp.getApp());
        String netError = MyApp.getApp().getString(R.string.app_name);

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("token",token);
            jsonObject.put("product_id",prdId);
            jsonObject.put("app_name","去哪借");
            jsonObject.put("channel","qunajie");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.GET_SERVICE(Api.APPLY, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {

            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });


    }
}
