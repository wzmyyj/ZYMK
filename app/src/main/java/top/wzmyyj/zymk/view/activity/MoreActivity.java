package top.wzmyyj.zymk.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.MoreContract;
import top.wzmyyj.zymk.presenter.MorePresenter;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.view.panel.MoreRecyclerPanel;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class MoreActivity extends BaseActivity<MoreContract.IPresenter> implements MoreContract.IView {

    private MoreRecyclerPanel moreRecyclerPanel;

    @BindView(R.id.ll_panel)
    LinearLayout llPanel;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.v_top_0)
    View v0;
    @BindView(R.id.v_top_1)
    View v1;

    @Override
    protected void initPresenter() {
        mPresenter = new MorePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(moreRecyclerPanel = new MoreRecyclerPanel(activity, mPresenter));
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        StatusBarUtil.initStatusBar(activity, true, true, true);
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v0, v1);

        llPanel.addView(getPanelView(0));
        getPanel(0).bingViews(llTop);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        imgBack.setOnClickListener(v -> mPresenter.finish());
    }

    @Override
    public void showData(String content, String figure, List<BookBean> books) {
        moreRecyclerPanel.setMoreData(content, figure, books);
    }

    @Override
    public void setTitle(String s) {
        if (tvTitle != null) tvTitle.setText(s);
    }
}
