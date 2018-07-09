package top.wzmyyj.zymk.view.fragment.base;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.fragment.PanelFragment;


/**
 * Created by yyj on 2018/07/07. email: 2209011667@qq.com
 */

public abstract class BasePanelFragment extends PanelFragment {
    protected abstract int getLayoutId();

    @Override
    protected void initView() {
        mVRoot = mInflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, mVRoot);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
