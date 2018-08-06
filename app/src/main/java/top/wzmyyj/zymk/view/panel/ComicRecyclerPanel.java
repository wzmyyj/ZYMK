package top.wzmyyj.zymk.view.panel;

import android.content.Context;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.presenter.ComicPresenter;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 */

public class ComicRecyclerPanel extends BaseRecyclerPanel<ComicBean, ComicPresenter> {

    public ComicRecyclerPanel(Context context, ComicPresenter comicPresenter) {
        super(context, comicPresenter);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setIVD(List<IVD<ComicBean>> ivd) {

    }

    @Override
    public void update() {

    }
}
