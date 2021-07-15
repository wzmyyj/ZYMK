package top.wzmyyj.zymk.view.activity;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.contract.ResultContract;
import top.wzmyyj.zymk.presenter.ResultPresenter;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.view.panel.ResultRecyclerPanel;

/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class ResultActivity extends BaseActivity<ResultContract.IPresenter> implements ResultContract.IView {

    @BindView(R.id.fl_panel)
    FrameLayout flPanel;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void initPresenter() {
        mPresenter = new ResultPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_result;
    }

    private ResultRecyclerPanel tyRecyclerPanel;

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(tyRecyclerPanel = new ResultRecyclerPanel(activity, mPresenter));
    }

    @OnClick(R.id.img_back)
    public void back() {
        mPresenter.finish();
    }

    @Override
    protected void initView() {
        super.initView();
        flPanel.addView(getPanelView(0));
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    public void setTitle(String s) {
        if (tvTitle != null)
            tvTitle.setText(s);
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
