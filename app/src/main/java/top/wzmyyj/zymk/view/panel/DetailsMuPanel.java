package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.HuaBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.app.utils.HuaComparator;
import top.wzmyyj.zymk.presenter.DetailsPresenter;
import top.wzmyyj.zymk.view.adapter.HuaAdapter;
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
    @BindView(R.id.tv_left)
    TextView tv_left;
    @BindView(R.id.tv_right)
    TextView tv_right;

    HuaAdapter mAdapter;
    List<HuaBean> mData = new ArrayList<>();


    @BindView(R.id.tl_mu_pager)
    TabLayout tl_mu_pager;

    @Override
    protected void initView() {
        super.initView();
        rv_mu.setLayoutManager(new GridLayoutManager(context, 4));
        rv_mu.setNestedScrollingEnabled(false);
        mAdapter = new HuaAdapter(context, R.layout.layout_mu_item, mData);
        rv_mu.setAdapter(mAdapter);


    }

    @Override
    protected void initListener() {
        super.initListener();
        tl_mu_pager.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int p = tab.getPosition() + 1;
                setData(p);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private List<HuaBean> mALLHuaList = new ArrayList<>();

    @Override
    public Object f(int w, Object... objects) {
        MuBean mu = (MuBean) objects[0];
        tv_mu_last.setText(mu.getTime_desc());

        read_href = mu.getReading_href();

        if (mu.getHuaList() != null) {
            mALLHuaList.clear();
            mALLHuaList.addAll(mu.getHuaList());

            pager_size = mALLHuaList.size() / 32 + 1;

            tl_mu_pager.removeAllTabs();
            for (int i = 0; i < pager_size; i++) {
                tl_mu_pager.addTab(tl_mu_pager.newTab().setText(i + 1 + ""));
            }

            sort(mALLHuaList, 1);//倒序排列
            setData(read_href);
        } else {
            T.s("加载失败");
        }
        return super.f(w, objects);
    }

    private void sort(List list, int k) {
        Collections.sort(list, new HuaComparator(k));
    }

    private String read_href;
    private int pager_size;
    private int sort_xu = 1;

    private void setData(String reading_href) {

        if (reading_href == null) {
            setData(1);
            return;
        }
        int p = 1;
        int i = mALLHuaList.size() - 1;
        for (HuaBean hua : mALLHuaList) {
            if (hua.getHref().equals(reading_href)) {
                mAdapter.setRead(reading_href);
                p = i / 32 + 1;
                break;
            }
            i--;
        }

        setData(p);
    }

    @OnClick(R.id.tv_left)
    public void left() {
        setData(tl_mu_pager.getSelectedTabPosition());
    }

    @OnClick(R.id.tv_right)
    public void right() {
        setData(tl_mu_pager.getSelectedTabPosition() + 2);
    }


    @OnClick(R.id.tv_mu_xu)
    public void xu() {
        if (sort_xu == 1) {
            sort_xu = -1;
            tv_mu_xu.setText("降序");
            img_mu_xu.setImageResource(R.mipmap.svg_booklist_down);
        } else {
            sort_xu = 1;
            tv_mu_xu.setText("升序");
            img_mu_xu.setImageResource(R.mipmap.svg_booklist_up);
        }

        sort(mALLHuaList, sort_xu);
        setData(read_href);

    }

    private void setData(int p) {
        if (p < 1 || p > pager_size) return;

        tl_mu_pager.getTabAt(p - 1).select();

        if (p == 1) {
            tv_left.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        } else {
            tv_left.setTextColor(context.getResources().getColor(R.color.colorGray_7));
        }

        if (p == pager_size) {
            tv_right.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        } else {
            tv_right.setTextColor(context.getResources().getColor(R.color.colorGray_7));
        }

        mData.clear();
        if (p == pager_size) {
            mData.addAll(mALLHuaList.subList(32 * (p - 1), mALLHuaList.size()));
        } else {
            mData.addAll(mALLHuaList.subList(32 * (p - 1), 32 * p));
        }
        mAdapter.notifyDataSetChanged();

    }

}
