package top.wzmyyj.zymk.view.activity;

import android.annotation.SuppressLint;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.contract.SettingContract;
import top.wzmyyj.zymk.presenter.SettingPresenter;
import top.wzmyyj.zymk.base.activity.BaseActivity;

/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class SettingActivity extends BaseActivity<SettingContract.IPresenter> implements SettingContract.IView {

    @BindView(R.id.tv_m_1)
    TextView tvM1;
    @BindView(R.id.tv_m_2)
    TextView tvM2;
    @BindView(R.id.tv_m_3)
    TextView tvM3;
    @BindView(R.id.tv_i_say)
    TextView tvISay;

    @Override
    protected void initPresenter() {
        mPresenter = new SettingPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.img_back)
    public void back() {
        mPresenter.finish();
    }

    @OnClick(R.id.ll_m_1)
    public void m1() {
        mPresenter.clearCache();
    }

    @OnClick(R.id.ll_m_2)
    public void m2() {
        mPresenter.changeCue();
    }

    @OnClick(R.id.ll_m_3)
    public void m3() {
        mPresenter.loadNewApp();
    }

    @OnClick(R.id.ll_p_1)
    public void p1() {
        mPresenter.goGitHubWeb();
    }

    @OnClick(R.id.ll_p_2)
    public void p2() {
        mPresenter.goAboutMe();
    }

    @OnClick(R.id.ll_p_3)
    public void p3() {
        mPresenter.goFeedback();
    }

    @OnClick(R.id.tv_title)
    public void setting() {
        mPresenter.setting();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getCacheSize();
        mPresenter.getCue();
        mPresenter.getVersion();
        tvISay.setText(R.string.i_say);
    }

    @Override
    public void setCache(String s) {
        tvM1.setText(s);
    }

    @Override
    public void setCue(boolean is) {
        if (is) {
            tvM2.setText("是");
        } else {
            tvM2.setText("否");
        }
    }

    @Override
    public void setVersion(String s) {
        tvM3.setText(s);
    }
}
