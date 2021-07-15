package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.base.panel.BaseBannerPanel;
import top.wzmyyj.zymk.contract.HomeContract;

/**
 * Created by yyj on 2018/09/19. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class HomeBannerPanel extends BaseBannerPanel<HomeContract.IPresenter> {

    public HomeBannerPanel(Context context, HomeContract.IPresenter homePresenter) {
        super(context, homePresenter);
    }

    @NonNull
    @Override
    protected ImageLoader getImageLoader() {
        return new BannerImageLoader();
    }

    static class BannerImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            if (path instanceof String && context != null) {
                String url = (String) path;
                GlideLoaderHelper.img(imageView, url, R.mipmap.ico_bg);
            }
        }
    }

    public void setBannerData(final List<BoBean> bos) {
        if (bos == null || bos.size() == 0) return;
        List<String> imgList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < bos.size(); i++) {
            BoBean bo = bos.get(i);
            imgList.add(bo.getDataSrc());
            strList.add(bo.getTitle());
        }
        mBanner.update(imgList, strList);
        mBanner.setOnBannerListener(position -> mPresenter.goDetails(bos.get(position).getHref()));
    }
}
