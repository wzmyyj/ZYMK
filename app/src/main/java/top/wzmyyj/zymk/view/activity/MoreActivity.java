package top.wzmyyj.zymk.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.MoreContract;
import top.wzmyyj.zymk.presenter.MorePresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.panel.MoreRecyclerPanel;

public class MoreActivity extends BaseActivity<MoreContract.IPresenter> implements MoreContract.IView {

    @Override
    protected void initPresenter() {
        mPresenter = new MorePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more;
    }


    private MoreRecyclerPanel moreRecyclerPanel;

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(moreRecyclerPanel = new MoreRecyclerPanel(activity, mPresenter));
    }

    @BindView(R.id.ll_panel)
    LinearLayout layout;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_top)
    LinearLayout ll_top;

    @BindView(R.id.v_top_0)
    View v0;
    @BindView(R.id.v_top_1)
    View v1;

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        StatusBarUtil.initStatusBar(activity, true, true, true);
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v0, v1);

        layout.addView(getPanelView(0));
        getPanel(0).bingViews(ll_top);
    }

    @Override
    protected void initListener() {
        super.initListener();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    public void showData(String content, String figure, List<BookBean> books) {
        moreRecyclerPanel.setMoreData(content, figure, books);
    }

    @Override
    public void setTitle(String s) {
        if (tv_title != null)
            tv_title.setText(s);
    }
}
