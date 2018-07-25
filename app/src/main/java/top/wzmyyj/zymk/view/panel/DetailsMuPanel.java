package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.HuaBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.presenter.DetailsPresenter;
import top.wzmyyj.zymk.view.panel.base.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */

public class DetailsMuPanel extends BasePanel<DetailsPresenter> {
    public DetailsMuPanel(Context context, DetailsPresenter detailsPresenter) {
        super(context, detailsPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.panel_details_mu;
    }

    @BindView(R.id.tv_mu_last)
    TextView tv_mu_last;
    @BindView(R.id.tv_mu_xu)
    TextView tv_mu_xu;
    @BindView(R.id.img_mu_xu)
    ImageView img_mu_xu;
    @BindView(R.id.rv_mu)
    RecyclerView rv_mu;
    CommonAdapter<HuaBean> mAdapter;
    List<HuaBean> mData = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        rv_mu.setLayoutManager(new GridLayoutManager(context, 4));
        rv_mu.setNestedScrollingEnabled(false);
        rv_mu.setOnScrollListener(new RecyclerView.OnScrollListener() {

            int mDistance = 0;
            int maxDistance = 100;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        mAdapter = new CommonAdapter<HuaBean>(context, R.layout.layout_mu_item, mData) {
            @Override
            protected void convert(ViewHolder holder, HuaBean huaBean, int position) {
                TextView tv_text = holder.getView(R.id.tv_hua_text);
                ImageView img_lock = holder.getView(R.id.img_hua_lock);
                ImageView img_new = holder.getView(R.id.img_hua_new);
                ImageView img_reading = holder.getView(R.id.img_hua_reading);

                tv_text.setText(huaBean.getTitle());
                if (huaBean.isLock()) {
                    img_lock.setVisibility(View.VISIBLE);
                } else {
                    img_lock.setVisibility(View.GONE);
                }

                if (huaBean.isDot()) {
                    img_new.setVisibility(View.VISIBLE);
                } else {
                    img_new.setVisibility(View.GONE);
                }


            }
        };


        rv_mu.setAdapter(mAdapter);


    }

    @Override
    public Object f(int w, Object... objects) {
        MuBean mu = (MuBean) objects[0];
        tv_mu_last.setText(mu.getTime_desc());

        if (mu.getHuaList() != null) {
            mData.clear();
            for (HuaBean hua : mu.getHuaList()) {
                mData.add(hua);
            }
            mAdapter.notifyDataSetChanged();
        } else {
            T.s("加载失败");
        }
        return super.f(w, objects);
    }
}
