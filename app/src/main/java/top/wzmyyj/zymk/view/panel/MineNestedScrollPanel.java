package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;

import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.base.panel.BaseNestedScrollPanel;
import top.wzmyyj.zymk.contract.MineContract;

/**
 * Created by yyj on 2018/10/29. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class MineNestedScrollPanel extends BaseNestedScrollPanel<MineContract.IPresenter> {

    public MineNestedScrollPanel(Context context, MineContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_mine_content;
    }

    @OnClick(R.id.img_me)
    public void about() {
        mPresenter.goAboutMe();
    }

    @OnClick(R.id.ll_info)
    public void goGitHub() {
        mPresenter.goGitHubWeb();
    }

    @OnClick(R.id.ll_1)
    public void goWeb1() {
        mPresenter.goHomeWeb();
    }

    @OnClick(R.id.ll_2)
    public void goWeb2() {
        mPresenter.goHotWeb();
    }

    @OnClick(R.id.ll_3)
    public void goWeb3() {
        mPresenter.goTMallWeb();
    }

    @OnClick(R.id.ll_4)
    public void goSetting() {
        mPresenter.goSetting();
    }
}
