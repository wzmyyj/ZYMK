package top.wzmyyj.zymk.view.activity;

import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.TyPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.iv.ITyView;
import top.wzmyyj.zymk.view.panel.TyRecyclerPanel;

public class TyActivity extends BaseActivity<TyPresenter> implements ITyView {

    @Override
    protected void initPresenter() {
        mPresenter = new TyPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ty;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new TyRecyclerPanel(activity, mPresenter));
    }

    @BindView(R.id.fl_panel)
    FrameLayout layout;

    @OnClick(R.id.img_back)
    public void back() {
        mPresenter.finish();
    }

    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected void initView() {
        super.initView();
        layout.addView(getPanelView(0));
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    public void update(int w, Object... objs) {
        getPanel(0).f(w, objs);
    }

    @Override
    public void setTitle(String s) {
        if (tv_title != null)
            tv_title.setText(s);
    }
}
