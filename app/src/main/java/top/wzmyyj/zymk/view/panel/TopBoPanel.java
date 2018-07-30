package top.wzmyyj.zymk.view.panel;

import android.content.Context;

import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.utils.GlideImageLoader;
import top.wzmyyj.zymk.presenter.HomePresenter;
import top.wzmyyj.zymk.view.panel.base.BaseBoPanel;


/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 */

public class TopBoPanel extends BaseBoPanel<HomePresenter> {
    public TopBoPanel(Context context, HomePresenter p) {
        super(context, p);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected ImageLoader getImageLoader() {
        return new GlideImageLoader();
    }


    @Override
    public Object f(int w, Object... objects) {
        if (w == -1) return null;

        List<BoBean> bos = (List<BoBean>) objects[0];
        if (bos == null || bos.size() < 6) return null;

        List<String> imgs = new ArrayList<>();
        List<String> strs = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            BoBean bo = bos.get(i);
            imgs.add(bo.getData_src());
            strs.add(bo.getTitle());
        }
        mBanner.update(imgs, strs);
        return super.f(w, objects);
    }


}
