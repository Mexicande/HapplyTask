package cn.silence.tableaux.common.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cn.silence.tableaux.common.Api;
import cn.silence.tableaux.common.ApiService;
import cn.silence.tableaux.common.AppUtils;
import cn.silence.tableaux.common.OnRequestDataListener;
import cn.silence.tableaux.utils.StatusBarUtil;
import cn.silence.tableaux.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_back)
    ImageView toolbarBack;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 30);
        initView();
        getData();
    }

    private void getData() {

        ApiService.GET_SERVICE(Api.ABOUT, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {

                try {
                    JSONObject data = json.getJSONObject("data");
                    String share_our = data.getString("share_our");



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });


    }

    private void initView() {

        toolbarBack.setVisibility(View.VISIBLE);
        toolbarTitle.setText("关于我们");
        String appVersionName = AppUtils.getAppVersionName();
        version.setText(getString(R.string.app_name) + "V" + appVersionName);
    }

    @OnClick(R.id.toolbar_back)
    public void onViewClicked() {
        finish();
    }
}
