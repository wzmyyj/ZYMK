package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.view.View;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.tools.G;
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
    protected int getSize() {
        return 6;
    }

    @Override
    protected void initBoData() {
    }


    @Override
    public Object f(int w, Object... objects) {
        List<BoBean> bos = (List<BoBean>) objects[0];
        if (bos == null || bos.size() < size)
            return null;

        for (int i = 0; i < size; i++) {
            BoBean bo = bos.get(i);
            G.img(context, bo.getData_src(), mImageList.get(i));
            mDosc[i] = bo.getTitle();
            final String href = bo.getHref();
            mImageList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.goDetails(href);
                }
            });
        }
        mTV.setText(mDosc[mViewPager.getCurrentItem() % size]);
        return super.f(w, objects);
    }
}
