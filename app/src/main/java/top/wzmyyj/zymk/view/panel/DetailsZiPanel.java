package top.wzmyyj.zymk.view.panel;

import android.content.Context;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.DetailsPresenter;
import top.wzmyyj.zymk.view.panel.base.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */

public class DetailsZiPanel extends BasePanel<DetailsPresenter> {
    public DetailsZiPanel(Context context, DetailsPresenter detailsPresenter) {
        super(context, detailsPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.panel_details_zi;
    }
}
