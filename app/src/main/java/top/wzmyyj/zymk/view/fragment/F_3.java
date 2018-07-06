package top.wzmyyj.zymk.view.fragment;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.FindPresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_3View;

/**
 * Created by wzm on 2018/07/06. email: 2209011667@qq.com
 */

public class F_3 extends BaseFragment<FindPresenter> implements IF_3View {
    @Override
    protected void initPresenter() {
        mPresenter = new FindPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }

    @Override
    public void showToast(String t) {

    }
}
