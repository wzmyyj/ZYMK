package top.wzmyyj.zymk.view.activity;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.DetailsPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.iv.IDetailsView;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView {


    @Override
    protected void initPresenter() {
        mPresenter = new DetailsPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void update(int w, Object... objs) {

    }
}
