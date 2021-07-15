package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.contract.TypeContract;
import top.wzmyyj.zymk.base.panel.BaseRecyclerPanel;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class TypeRecyclerPanel extends BaseRecyclerPanel<TypeBean, TypeContract.IPresenter> {

    public TypeRecyclerPanel(Context context, TypeContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected void setIVD(List<IVD<TypeBean>> ivd) {
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
                GlideLoaderHelper.img(img_type, typeBean.getDataSrc());
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
        mData.clear();
        if (typeList != null && typeList.size() > 0) {
            mData.addAll(typeList);
        }
        notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void setEmpty() {
        mEmpty = mInflater.inflate(R.layout.layout_empty, null);
        TextView tv_empty = mEmpty.findViewById(R.id.tv_empty_text);
        mEmpty.setVisibility(View.GONE);
        tv_empty.setText("下滑刷新，重新加载。");
    }
}
