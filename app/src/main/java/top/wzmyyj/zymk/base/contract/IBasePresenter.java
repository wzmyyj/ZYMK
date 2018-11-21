package top.wzmyyj.zymk.base.contract;

import top.wzmyyj.wzm_sdk.activity.inter.IContext;
import top.wzmyyj.wzm_sdk.activity.inter.ILifeCycle;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public interface IBasePresenter extends IContext, ILifeCycle {
    void finish();

    void log(String s);
}
