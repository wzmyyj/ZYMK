package top.wzmyyj.zymk.app.event;

import top.wzmyyj.zymk.app.bean.HuaBean;

/**
 * Created by yyj on 2018/08/07. email: 2209011667@qq.com
 */

public class SelectHuaEvent {
    private HuaBean hua;

    public SelectHuaEvent(HuaBean hua) {
        this.hua = hua;
    }

    public HuaBean getHua() {
        return hua;
    }

    public void setHua(HuaBean hua) {
        this.hua = hua;
    }
}
