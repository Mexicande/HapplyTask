package cn.silence.tableaux.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.silence.tableaux.bean.WelfareBean;
import cn.silence.tableaux.R;

import java.util.List;


/**
 * Created by apple on 2017/7/18.
 */

public class WelfareAdapter extends BaseQuickAdapter<WelfareBean,BaseViewHolder> {

    public WelfareAdapter(List<WelfareBean> data) {
        super(R.layout.welfare_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WelfareBean item) {

        Glide.with(mContext).load(item.getImage())
                .into((ImageView) helper.getView(R.id.welfare));


    }
}
