package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.app.utils.GlideImageLoader;
import top.wzmyyj.zymk.common.utils.DensityUtil;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.presenter.HomePresenter;
import top.wzmyyj.zymk.view.panel.base.BaseNestedScrollPanel;


/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 * 主页。
 */

public class HomeNestedScrollPanel extends BaseNestedScrollPanel<HomePresenter> {

    public HomeNestedScrollPanel(Context context, HomePresenter p) {
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
                new HomeItemPanel(context, mPresenter)
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

    @Override
    public Object f(int w, Object... objects) {
        if (w == -1) return null;
        List<BoBean> bos = (List<BoBean>) objects[0];
        setBanner(bos);
        List<ItemBean> itemBeans = (List<ItemBean>) objects[1];
        if (itemBeans != null && itemBeans.size() == 9) {
            for (int i = 0; i < 9; i++) {
                getPanel(i).f(w, itemBeans.get(i));
            }
        }
        return super.f(w, objects);
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
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

    @BindView(R.id.banner)
    Banner mBanner;

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

        List images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(5000);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();


    }

    public void setBanner(final List<BoBean> bos) {
        if (bos == null || bos.size() < 6) return;
        List<String> imgs = new ArrayList<>();
        List<String> strs = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            BoBean bo = bos.get(i);
            imgs.add(bo.getData_src());
            strs.add(bo.getTitle());
        }
        mBanner.update(imgs, strs);

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                mPresenter.goDetails(bos.get(position).getHref());
            }
        });
    }

    @BindView(R.id.tv_end)
    TextView tv_end;

    protected void setFooter() {
        tv_end.setText("-- 到底部了哦 --");
    }
}
