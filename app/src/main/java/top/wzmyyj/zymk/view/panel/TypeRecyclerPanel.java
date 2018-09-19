package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.contract.TypeContract;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class TypeRecyclerPanel extends BaseRecyclerPanel<TypeBean, TypeContract.IPresenter> {


    public TypeRecyclerPanel(Context context, TypeContract.IPresenter p) {
        super(context, p);
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
                String ss = "- " + typeBean.getTitle() + " -";
                tv_type.setText(ss);
                G.img(context, typeBean.getData_src(), img_type);
                final String href = typeBean.getHref();
            }
        });
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        mPresenter.goResult(mData.get(position).getHref());
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
    }


    @Override
    public void update() {
        mPresenter.loadData();
    }


    public void setTypeData(List<TypeBean> typeList) {
        if (typeList == null && typeList.size() == 0) {
            notifyDataSetChanged();
            return;
        }
        mData.clear();
        mData.addAll(typeList);
        notifyDataSetChanged();

    }


    protected TextView tv_empty;

    @Override
    protected void setEmpty() {
        mEmpty = mInflater.inflate(R.layout.layout_empty, null);
        tv_empty = mEmpty.findViewById(R.id.tv_empty_text);
        mEmpty.setVisibility(View.GONE);
        tv_empty.setText("下滑刷新，重新加载。");
    }

}
