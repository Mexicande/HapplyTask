package cn.silence.tableaux.common.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.allen.library.SuperButton;
import cn.silence.tableaux.R;
import cn.silence.tableaux.activity.MyApp;
import cn.silence.tableaux.bean.LoginEvent;
import cn.silence.tableaux.common.Api;
import cn.silence.tableaux.common.ApiService;
import cn.silence.tableaux.common.CodeUtils;
import cn.silence.tableaux.common.Contacts;
import cn.silence.tableaux.common.OnRequestDataListener;
import cn.silence.tableaux.common.SPUtil;
import cn.silence.tableaux.utils.BrowsingHistory;
import cn.silence.tableaux.utils.CaptchaTimeCount;
import cn.silence.tableaux.utils.Constants;
import cn.silence.tableaux.utils.ToastUtils;
import cn.silence.tableaux.utils.editext.PowerfulEditText;
import com.jaeger.library.StatusBarUtil;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author apple
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.ed_phone)
    PowerfulEditText edPhone;
    @Bind(R.id.ed_code)
    PowerfulEditText edCode;
    @Bind(R.id.bt_code)
    Button btCode;
    @Bind(R.id.layout_code)
    RelativeLayout layoutCode;
    @Bind(R.id.bt_login)
    SuperButton btLogin;
    @Bind(R.id.view)
    View view;
    private CaptchaTimeCount captchaTimeCount;
    private int oldNew = 0;
    private KProgressHUD hud;
    private String phone;
    private int isolduser;
    private String sign;
    private CodeUtils codeUtils;
    private String yanZhengResult;
    private String etYanZhengCode;
    private String yanZhengCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 40);
        ButterKnife.bind(this);
        initView();
        setListener();

    }

    private void initView() {
        captchaTimeCount = new CaptchaTimeCount(Constants.MILLIS_IN_TOTAL, Constants.COUNT_DOWN_INTERVAL, btCode, this);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);

    }

    private void setListener() {
        edPhone.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (!edCode.getText().toString().isEmpty() && s.toString().length() == 11) {
                    btLogin.setEnabled(true);
                    btLogin.setUseShape();
                } else {
                    btLogin.setEnabled(false);
                    btLogin.setUseShape();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edCode.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (edPhone.getText().toString().length() == 11 && s.toString().length() == 4) {
                    btLogin.setEnabled(true);
                    btLogin.setUseShape();
                } else {
                    btLogin.setEnabled(false);
                    btLogin.setUseShape();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                v.setFocusable(false);
                v.setFocusableInTouchMode(true);
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 验证码效验
     */
    private void verCode(String code) {
        hud.show();
        phone = edPhone.getText().toString();

        JSONObject object = new JSONObject();
        try {
            object.put("mobile", phone);
            object.put("sign", sign);
            object.put("version", "2");
            object.put("code", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiService.POST_SERVICE(Api.LOGIN.CHECKCODE, object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                hud.dismiss();

                try {
                    JSONObject date = data.getJSONObject("data");
                    String token = date.getString("accessToken");
                    SPUtil.putString(Contacts.TOKEN, token);
                    SPUtil.putString(Contacts.PHONE, phone);
                    EventBus.getDefault().post(new LoginEvent(phone));
                    String title = getIntent().getStringExtra("title");
                    String link = getIntent().getStringExtra("link");
                    if (!TextUtils.isEmpty(title)) {
                        String id = getIntent().getStringExtra("id");
                        new BrowsingHistory().execute(id);
                            Intent intent=new Intent(LoginActivity.this, HtmlActivity.class);
                            intent.putExtra("title",title);
                            intent.putExtra("link",link);
                            startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("phone", phone);
                        setResult(200, intent);
                    }
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                hud.dismiss();
                ToastUtils.showToast(MyApp.getApp(), msg);
            }
        });

    }

    /**
     * isOldUser
     * 新老用户
     */
    private void isOldUser() {
        hud.show();
        phone = edPhone.getText().toString();

        JSONObject object = new JSONObject();
        try {
            object.put("mobile", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.POST_SERVICE(Api.LOGIN.isOldUser, object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                hud.dismiss();
                try {
                    JSONObject date = data.getJSONObject("data");
                    sign = date.getString("sign");
                        captchaTimeCount.start();
                        view.setVisibility(View.VISIBLE);
                        layoutCode.setVisibility(View.VISIBLE);
                        btLogin.setEnabled(false);
                        btLogin.setUseShape();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                hud.dismiss();
                ToastUtils.showToast(MyApp.getApp(), msg);
            }
        });
    }

    @OnClick({R.id.back, R.id.bt_code, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.bt_code:
                isOldUser();
                break;
            case R.id.bt_login:
                String code = edCode.getText().toString();
                verCode(code);
                break;
        }
    }
}
