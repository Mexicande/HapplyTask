package cn.silence.tableaux.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.silence.tableaux.bean.LoginEvent;
import cn.silence.tableaux.common.ActivityUtils;
import cn.silence.tableaux.common.Contacts;
import cn.silence.tableaux.utils.ToastUtils;
import cn.silence.tableaux.R;

import cn.silence.tableaux.common.SPUtil;

import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author apple
 */
public class SettingActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_back)
    ImageView toolbarBack;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.apply)
    Button apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 90);
        initView();

    }
    private void initView() {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarTitle.setText("设置");
        String token = SPUtil.getString(Contacts.TOKEN);
        if(!TextUtils.isEmpty(token)){
            apply.setVisibility(View.VISIBLE);
        }
    }
    @OnClick({R.id.toolbar_back,  R.id.super_version,R.id.apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.super_version:
                ToastUtils.showToast(this,"已经是最近版本啦！");
                break;
            case R.id.apply:
                SPUtil.remove(Contacts.PHONE);
                SPUtil.remove(Contacts.TOKEN);
                ActivityUtils.startActivity(LoginActivity.class);
                EventBus.getDefault().post(new LoginEvent(null));
                finish();
                break;
             default:
                 break;
        }
    }
}
