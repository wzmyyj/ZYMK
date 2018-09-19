package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.common.utils.DensityUtil;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.HomeContract;
import top.wzmyyj.zymk.view.panel.base.BaseNestedScrollPanel;


/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 * 主页。
 */

public class HomeNestedScrollPanel extends BaseNestedScrollPanel<HomeContract.IPresenter> {

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
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int mDistance = 0;
            //            //当距离在[0,maxDistance]变化时，透明度在[0,255之间变化]
            int maxDistance = DensityUtil.dp2px(context, 155) - StatusBarUtil.StatusBarHeight;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (viewList.size() == 0) return;
                View top = viewList.get(0);

                mDistance = scrollY;
                float percent = mDistance * 1f / maxDistance;//百分比
                top.setAlpha(percent);
            }
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

    @BindView(R.id.fl_panel_0)
    FrameLayout fl_panel_0;
    @BindView(R.id.fl_panel_1)
    FrameLayout fl_panel_1;
    @BindView(R.id.fl_panel_2)
    FrameLayout fl_panel_2;
    @BindView(R.id.fl_panel_3)
    FrameLayout fl_panel_3;
    @BindView(R.id.fl_panel_4)
    FrameLayout fl_panel_4;
    @BindView(R.id.fl_panel_5)
    FrameLayout fl_panel_5;
    @BindView(R.id.fl_panel_6)
    FrameLayout fl_panel_6;
    @BindView(R.id.fl_panel_7)
    FrameLayout fl_panel_7;
    @BindView(R.id.fl_panel_8)
    FrameLayout fl_panel_8;


    protected void setContent() {
        fl_panel_0.addView(getPanelView(0));
        fl_panel_1.addView(getPanelView(1));
        fl_panel_2.addView(getPanelView(2));
        fl_panel_3.addView(getPanelView(3));
        fl_panel_4.addView(getPanelView(4));
        fl_panel_5.addView(getPanelView(5));
        fl_panel_6.addView(getPanelView(6));
        fl_panel_7.addView(getPanelView(7));
        fl_panel_8.addView(getPanelView(8));
    }


    @BindView(R.id.ll_h_1)
    LinearLayout ll_h_1;
    @BindView(R.id.ll_h_2)
    LinearLayout ll_h_2;

    @BindView(R.id.fl_panel_head)
    FrameLayout fl_panel_head;

    protected void setHeader() {
        ll_h_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goNew();
            }
        });
        ll_h_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goRank();
            }
        });

        fl_panel_head.addView(getPanelView(9));
    }

    @BindView(R.id.tv_end)
    TextView tv_end;

    protected void setFooter() {
        tv_end.setText("-- 到底部了哦 --");
    }
}
