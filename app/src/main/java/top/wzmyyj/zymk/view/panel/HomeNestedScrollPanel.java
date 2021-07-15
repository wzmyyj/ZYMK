package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.wzm_sdk.utils.DensityUtil;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.HomeContract;
import top.wzmyyj.zymk.base.panel.BaseNestedScrollPanel;

/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 * 主页。
 */
@SuppressLint("NonConstantResourceId")
public class HomeNestedScrollPanel extends BaseNestedScrollPanel<HomeContract.IPresenter> {

    @BindView(R.id.fl_panel_0)
    FrameLayout flPanel0;
    @BindView(R.id.fl_panel_1)
    FrameLayout flPanel1;
    @BindView(R.id.fl_panel_2)
    FrameLayout flPanel2;
    @BindView(R.id.fl_panel_3)
    FrameLayout flPanel3;
    @BindView(R.id.fl_panel_4)
    FrameLayout flPanel4;
    @BindView(R.id.fl_panel_5)
    FrameLayout flPanel5;
    @BindView(R.id.fl_panel_6)
    FrameLayout flPanel6;
    @BindView(R.id.fl_panel_7)
    FrameLayout flPanel7;
    @BindView(R.id.fl_panel_8)
    FrameLayout flPanel8;
    @BindView(R.id.ll_h_1)
    LinearLayout llH1;
    @BindView(R.id.ll_h_2)
    LinearLayout llH2;
    @BindView(R.id.fl_panel_head)
    FrameLayout flPanelHead;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    public HomeNestedScrollPanel(Context context, HomeContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeItemPanel(context, mPresenter),
                new HomeBannerPanel(context, mPresenter)
        );
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_home_content;
    }

    @Override
    protected void initView() {
        super.initView();
        setHeader();
        setContent();
        setFooter();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mNestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (viewList.size() == 0) return;
                    View top = viewList.get(0);
                    //当距离在[0,maxDistance]变化时，透明度在[0,255之间变化]
                    int maxDistance = DensityUtil.pt2px(context, 155) - StatusBarUtil.StatusBarHeight;
                    float percent = scrollY * 1f / maxDistance;//百分比
                    top.setAlpha(percent);
                });
    }

    @Override
    public void update() {
        mPresenter.loadData();
    }

    public void setHomeData(List<BoBean> boBeans, List<ItemBean> itemBeans) {
        ((HomeBannerPanel) getPanel(9)).setBannerData(boBeans);
        if (itemBeans != null && itemBeans.size() == 9) {
            for (int i = 0; i < 9; i++) {
                ((HomeItemPanel) getPanel(i)).setItemData(itemBeans.get(i));
            }
        }
    }

    protected void setContent() {
        flPanel0.addView(getPanelView(0));
        flPanel1.addView(getPanelView(1));
        flPanel2.addView(getPanelView(2));
        flPanel3.addView(getPanelView(3));
        flPanel4.addView(getPanelView(4));
        flPanel5.addView(getPanelView(5));
        flPanel6.addView(getPanelView(6));
        flPanel7.addView(getPanelView(7));
        flPanel8.addView(getPanelView(8));
    }

    protected void setHeader() {
        llH1.setOnClickListener(v -> mPresenter.goNew());
        llH2.setOnClickListener(v -> mPresenter.goRank());
        flPanelHead.addView(getPanelView(9));
    }

    protected void setFooter() {
        tvEnd.setText("-- 到底部了哦 --");
    }
}
