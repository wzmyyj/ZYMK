package top.wzmyyj.zymk.view.activity.base;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.activity.PanelActivity;


/**
 * Created by yyj on 2018/07/07. email: 2209011667@qq.com
 */

public abstract class BasePanelActivity extends PanelActivity {
    protected abstract int getLayoutId();

    @Override
    protected void initView() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
