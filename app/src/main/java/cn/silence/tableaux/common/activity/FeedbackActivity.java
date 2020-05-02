package cn.silence.tableaux.common.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import cn.silence.tableaux.common.Api;
import cn.silence.tableaux.common.ApiService;
import cn.silence.tableaux.common.OnRequestDataListener;
import cn.silence.tableaux.utils.NetworkUtils;
import cn.silence.tableaux.utils.StatusBarUtil;
import cn.silence.tableaux.utils.ToastUtils;
import cn.silence.tableaux.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @Bind(R.id.et_message)
    EditText etMessage;
    @Bind(R.id.apply)
    Button apply;
    @Bind(R.id.toolbar_back)
    ImageView toolbarBack;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimary),30);
        initView();
    }

    private void initView() {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarTitle.setText("意见反馈");
    }

    @OnClick({R.id.toolbar_back, R.id.apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.apply:


                boolean available = NetworkUtils.isAvailable(this);
                if(available){
                    if(!TextUtils.isEmpty(etMessage.getText().toString())){
                        submit(etMessage.getText().toString());
                    }
                }else {
                    ToastUtils.showToast(this,"网络无法连接");
                }
                break;
            default:
                break;
        }
    }

    private void submit(String toString) {
        JSONObject object=new JSONObject();
        try {
            object.put("content",toString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiService.POST_SERVICE(Api.FEEDBACK, object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                ToastUtils.showToast(FeedbackActivity.this,"感觉您的宝贵意见，我们将稍后作答");
                finish();
            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });

    }
}
