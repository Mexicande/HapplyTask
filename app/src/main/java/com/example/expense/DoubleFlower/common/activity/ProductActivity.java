package com.example.expense.DoubleFlower.common.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.expense.DoubleFlower.R;
import com.example.expense.DoubleFlower.adapter.ProductAdapter;
import com.example.expense.DoubleFlower.bean.Product;
import com.example.expense.DoubleFlower.common.Api;
import com.example.expense.DoubleFlower.common.ApiService;
import com.example.expense.DoubleFlower.common.Contacts;
import com.example.expense.DoubleFlower.common.OnRequestDataListener;
import com.example.expense.DoubleFlower.common.SPUtil;
import com.example.expense.DoubleFlower.utils.BrowsingHistory;
import com.example.expense.DoubleFlower.utils.RecyclerViewDecoration;
import com.example.expense.DoubleFlower.utils.ToastUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author apple
 */
public class ProductActivity extends AppCompatActivity {

    @Bind(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    private ProductAdapter mProductAdapter;

    public static void launch(Context context, String identity) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("identity", identity);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodcut);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark), 90);
        initView();
        setListener();
        getData();

    }

    private void setListener() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);

        mProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Product product = mProductAdapter.getData().get(position);
                String token = SPUtil.getString(Contacts.TOKEN);
                if (TextUtils.isEmpty(token)) {
                    Intent intent = new Intent(ProductActivity.this, LoginActivity.class);
                    intent.putExtra("title",product.getName());
                    intent.putExtra("link",product.getLink());
                    intent.putExtra("id",product.getId());
                    startActivity(intent);
                } else {
                    new BrowsingHistory().execute(product.getId());

                    Uri uri = Uri.parse(product.getLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                /*    Intent intent = new Intent(ProductActivity.this, HtmlActivity.class);
                    intent.putExtra("title",product.getName());
                    intent.putExtra("link",product.getLink());
                    startActivity(intent);*/
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

    }

    private void initView() {
        toolbarTitle.setText("智能贷");
        mProductAdapter = new ProductAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewDecoration decoration = new RecyclerViewDecoration(10);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(mProductAdapter);
    }

    private void getData() {
        String identity = getIntent().getStringExtra("identity");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("type",identity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiService.GET_SERVICE(Api.URL, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                try {
                    String data = json.getString("data");
                    Gson gson = new Gson();
                    Product[] welfare = gson.fromJson(data, Product[].class);
                    if (welfare.length > 0) {
                        List<Product> welfare1 = Arrays.asList(welfare);
                        mProductAdapter.setNewData(welfare1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                ToastUtils.showToast(ProductActivity.this, msg);
            }
        });
    }


    @OnClick(R.id.toolbar_back)
    public void onViewClicked() {
        finish();
    }
}
