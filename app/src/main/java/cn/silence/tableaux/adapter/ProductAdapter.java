package cn.silence.tableaux.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.silence.tableaux.bean.Product;
import cn.silence.tableaux.glide.GlideRoundTransform;
import cn.silence.tableaux.R;

import java.util.List;

/**
 *
 * @author apple
 * @date 2017/4/11
 */

public class ProductAdapter extends BaseQuickAdapter<Product,BaseViewHolder> {

    public ProductAdapter(List<Product> data) {
        super(R.layout.new_item, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Product item) {
            helper.setText(R.id.tv_ProductName,item.getName());
        helper .setText(R.id.tv_Summry,item.getProduct_introduction())
                .setText(R.id.number,item.getSort()+1000+"")
                .setText(R.id.rate,"日利率: "+item.getMin_algorithm());
        String maximumAmount = item.getMaximum_amount();
        if(maximumAmount.length()>4){
            String substring = maximumAmount.substring(0, maximumAmount.length() - 4);
            helper.setText(R.id.min_max_Special,item.getMinimum_amount()+"~"+substring+"万");
        }else {
            helper.setText(R.id.min_max_Special,item.getMinimum_amount()+"~"+maximumAmount);
        }

        Glide.with(mContext).load(item.getProduct_logo())
                .bitmapTransform(new GlideRoundTransform(mContext,5))
                .into((ImageView) helper.getView(R.id.head))
        ;
    }
}
