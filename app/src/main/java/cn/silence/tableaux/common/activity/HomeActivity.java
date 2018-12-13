package cn.silence.tableaux.common.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.silence.tableaux.adapter.NoTouchViewPager;
import cn.silence.tableaux.common.SPUtil;
import cn.silence.tableaux.common.fragment.CenterFragment;
import cn.silence.tableaux.common.fragment.HomeFragment;
import cn.silence.tableaux.common.fragment.WelfareFragment;

import cn.silence.tableaux.R;

import cn.silence.tableaux.common.MyViewPagerAdapter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.meituan.android.walle.WalleChannelReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.tab)
    PageBottomTabLayout tab;
    public static  NavigationController navigationController;

    public  static MyViewPagerAdapter pagerAdapter;
    public  static NoTouchViewPager viewPager;
    private int newversioncode;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      //StatusBarUtil.setColor(this, getResources().getColor(R.color.white),114);
        ButterKnife.bind(this);
        initView();
        setUrl();
    }
    private void initView() {
        viewPager = (NoTouchViewPager) findViewById(R.id.viewPager);
        navigationController = tab.custom()
                .addItem(newItem(R.mipmap.iv_home, R.mipmap.iv_home_select,"主页"))
                .addItem(newItem(R.mipmap.iv_welfare, R.mipmap.iv_welfare_select,"产品"))
                .addItem(newItem(R.mipmap.iv_center, R.mipmap.iv_center_select,"我的"))
                .build();
        ArrayList<Fragment> list=new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new WelfareFragment());
        list.add(new CenterFragment());
        pagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(pagerAdapter);
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(list.size());

    }

    private void setUrl() {
        Map<String, String> map = new HashMap<>();
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        map.put("name", "去");
        map.put("market", channel);
        OkGo.<String>post("http://api.anwenqianbao.com/v2/vest/getStatus")
                .params(map)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body());
                            JSONObject data = jsonObject.getJSONObject("data");
                            int status = data.getInt("status");
                            if(status==1){
                                SPUtil.putBoolean(HomeActivity.this, "open", true);
                            }else {
                                SPUtil.putBoolean(HomeActivity.this,"open", false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }
    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text){
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable,checkedDrawable,text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.colorPrimary));
        return normalItemView;
    }

    private long mLastBackTime = 0;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
            finish();
        } else {
            mLastBackTime = System.currentTimeMillis();
            Toast.makeText(this, "请再确认一次", Toast.LENGTH_SHORT).show();
        }

    }


}
