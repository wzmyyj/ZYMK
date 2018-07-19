package top.wzmyyj.zymk.view.panel.base;

import android.content.Context;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.panel.InitPanel;


/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */

public abstract class BaseInitPanel extends InitPanel {
    public BaseInitPanel(Context context) {
        super(context);
    }

    protected abstract int getLayoutId();

    @Override
    protected void initView() {
        view = mInflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
