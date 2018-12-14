package cn.silence.tableaux.utils;


import cn.silence.tableaux.R;
import cn.silence.tableaux.activity.MyApp;
import cn.silence.tableaux.common.Api;
import cn.silence.tableaux.common.ApiService;
import cn.silence.tableaux.common.Contacts;
import cn.silence.tableaux.common.OnRequestDataListener;
import cn.silence.tableaux.common.SPUtil;

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
            jsonObject.put("app_name","分期还");
            jsonObject.put("channel","fenqi");
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
