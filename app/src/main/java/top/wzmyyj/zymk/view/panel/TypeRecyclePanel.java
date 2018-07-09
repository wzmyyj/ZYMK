package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;

/**
 * Created by wzm on 2018/07/06. email: 2209011667@qq.com
 */

public abstract class TypeRecyclePanel extends BaseRecyclerPanel<TypeBean> {
    public TypeRecyclePanel(Context context) {
        super(context);
    }

    @Override
    protected void setIVD(List ivd) {
        ivd.add(new SingleIVD<TypeBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_comic_type;
            }

            @Override
            public void convert(ViewHolder holder, TypeBean typeBean, int position) {
                ImageView img_type = holder.getView(R.id.img_type);
                TextView tv_type = holder.getView(R.id.tv_type);
                tv_type.setText("- " + typeBean.getName() + " -");
                Glide.with(context)
                        .load(typeBean.getUrl())
                        .error(R.mipmap.ic_error)
                        .into(img_type);
            }
        });
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {
        super.setView(rv, srl, layout);
        rv.setLayoutManager(new GridLayoutManager(context, 3));
    }
}
