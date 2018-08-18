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
import top.wzmyyj.zymk.presenter.TypePresenter;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class TypeRecyclerPanel extends BaseRecyclerPanel<TypeBean, TypePresenter> {


    public TypeRecyclerPanel(Context context, TypePresenter p) {
        super(context, p);
    }

    @Override
    protected void setData() {

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
                tv_type.setText("- " + typeBean.getTitle() + " -");
                G.img(context, typeBean.getData_src(), img_type);
                final String href = typeBean.getHref();
            }
        });
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        mPresenter.goTy(mData.get(position).getHref());
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

    @Override
    public Object f(int w, Object... objects) {
        if (w == -1) {
            notifyDataSetChanged();
            return null;
        }
        List<TypeBean> typeList = (List<TypeBean>) objects[0];
        if (typeList != null && typeList.size() > 0) {
            mData.clear();
            for (TypeBean bean : typeList) {
                mData.add(bean);
            }
        }
        notifyDataSetChanged();
        return super.f(w, objects);
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
