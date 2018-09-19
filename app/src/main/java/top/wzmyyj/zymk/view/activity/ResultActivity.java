package top.wzmyyj.zymk.view.activity;

import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.contract.ResultContract;
import top.wzmyyj.zymk.presenter.ResultPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.panel.ResultRecyclerPanel;

public class ResultActivity extends BaseActivity<ResultContract.IPresenter> implements ResultContract.IView {

    @Override
    protected void initPresenter() {
        mPresenter = new ResultPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ty;
    }

    private ResultRecyclerPanel tyRecyclerPanel;

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                tyRecyclerPanel = new ResultRecyclerPanel(activity, mPresenter)
        );
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
    public void setTitle(String s) {
        if (tv_title != null)
            tv_title.setText(s);
    }

    @Override
    public void showData(boolean isFirst, List<BookBean> books, String base, String next) {
        tyRecyclerPanel.setResultData(isFirst, books, base, next);
    }

    @Override
    public void showLoadFail(String msg) {
        tyRecyclerPanel.loadFail(msg);
    }
}
