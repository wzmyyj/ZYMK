package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.view.View;

import java.util.List;

import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.view.panel.base.BaseBoPanel;


/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 */

public class TopBoPanel extends BaseBoPanel {
    public TopBoPanel(Context context) {
        super(context);
    }

    @Override
    protected int getSize() {
        return 6;
    }

    @Override
    protected int getPointSize() {
        return 25;
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
            final String url = bo.getHref();
            mImageList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.s(url);
                }
            });
        }
        mTV.setText(mDosc[mViewPager.getCurrentItem() % size]);
        return super.f(w, objects);
    }
}
