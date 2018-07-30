package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;

/**
 * Created by wzm on 2018/07/03. email: 2209011667@qq.com
 */

public abstract class BoPanel extends InitPanel {

    protected List images = new ArrayList<>();
    protected List<String> titles = new ArrayList<>();

    protected Banner mBanner;

    public BoPanel(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_bo, null);
        mBanner = view.findViewById(R.id.banner);

        setData();
        //设置图片加载器
        mBanner.setImageLoader(getImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(4000);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);

        // 自定义样式
        setBanner(mBanner);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    protected void setBanner(Banner banner) {

    }

    protected abstract void setData();

    protected abstract ImageLoader getImageLoader();


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

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
