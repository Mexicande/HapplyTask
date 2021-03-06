package cn.silence.tableaux.common.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import cn.silence.tableaux.adapter.HomeAdapter;
import cn.silence.tableaux.bean.Banner;
import cn.silence.tableaux.bean.Product;
import cn.silence.tableaux.utils.BrowsingHistory;
import cn.silence.tableaux.utils.LogUtils;
import cn.silence.tableaux.utils.RecycleViewDivider;
import cn.silence.tableaux.utils.ToastUtils;
import cn.silence.tableaux.R;

import cn.silence.tableaux.common.activity.HomeActivity;
import cn.silence.tableaux.common.activity.HtmlActivity;
import cn.silence.tableaux.common.Api;
import cn.silence.tableaux.common.ApiService;
import cn.silence.tableaux.common.Contacts;
import cn.silence.tableaux.common.activity.LoginActivity;
import cn.silence.tableaux.common.OnRequestDataListener;
import cn.silence.tableaux.common.SPUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 主页
 * <p>
 * A simple {@link Fragment} subclass.
 *
 * @author apple
 */
public class HomeFragment extends Fragment {

    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private BGABanner banner;
    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    private HomeAdapter mAdapter;

    private ArrayList<Product> mProductList = new ArrayList<>();
    private Product mRecommendProduct;
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        getBanner();
        getDate();
        setListener();
        return view;
    }

    private void initView() {
        mAdapter = new HomeAdapter(null);
        recylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerview.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.recycler_divider));

        recylerview.setAdapter(mAdapter);
        View footerView = setView();
        mAdapter.addHeaderView(footerView, 0);
        View foot = getActivity().getLayoutInflater().inflate(R.layout.foot_item, null);
        ButterKnife.findById(foot,R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.navigationController.setSelect(1);
            }
        });
        mAdapter.addFooterView(foot);
        banner.setAdapter(new BGABanner.Adapter<ImageView, Banner>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, Banner model, int position) {
                Glide.with(getActivity())
                        .load(model.getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });
    }

    private void getDate() {
        /**product_list**/
        ApiService.GET_SERVICE(Api.URL, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.finishRefresh();
                }
                try {
                    String data = json.getString("data");
                    List<Product> mRecommendList = new Gson().fromJson(data, new TypeToken<List<Product>>() {
                    }.getType());
                    if(mRecommendList.size()>10){
                        mAdapter.setNewData(mRecommendList.subList(0,10));
                    }else {
                        mAdapter.setNewData(mRecommendList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.finishRefresh();
                }
                ToastUtils.showToast(getActivity(),msg);
            }
        });

        JSONObject object =new JSONObject();
        try {
            object.put("type","7");
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    private void getBanner() {
        ApiService.GET_SERVICE(Api.BANNER, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    Gson gson = new Gson();
                    Banner[] banners = gson.fromJson(data, Banner[].class);
                    if(banners.length>0){
                        List<Banner> banners1 = Arrays.asList(banners);
                        banner.setData(banners1, null);
                        LogUtils.d("banners1="+banners1.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });
    }

    /**
     * RecyclerView   header
     *
     * @return
     */


    private View setView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.header_layout, null);

        banner = (BGABanner) view.findViewById(R.id.banner_fresco_demo_content);

        return view;

    }

    private void setListener() {
        banner.setDelegate(new BGABanner.Delegate<ImageView, Banner>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, Banner model, int position) {
                String token = SPUtil.getString( Contacts.TOKEN);
                if(TextUtils.isEmpty(token)){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("title",model.getName());
                    intent.putExtra("link",model.getH5_link());
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getActivity(), HtmlActivity.class);
                    intent.putExtra("title",model.getName());
                    intent.putExtra("link",model.getH5_link());
                    startActivity(intent);
                }
            }
        });


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Product product = mAdapter.getData().get(position);
                String token = SPUtil.getString( Contacts.TOKEN);
                if(TextUtils.isEmpty(token)){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("title",product.getProduct_name());
                    intent.putExtra("link",product.getH5_link());
                    intent.putExtra("id",product.getId());
                    startActivity(intent);
                }else {
                    new BrowsingHistory().execute(product.getProduct_id());
                        Intent intent=new Intent(getActivity(), HtmlActivity.class);
                        intent.putExtra("title",product.getProduct_name());
                        intent.putExtra("link",product.getH5_link());
                        startActivity(intent);
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getBanner();
                getDate();
            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
