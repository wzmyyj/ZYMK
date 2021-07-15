package top.wzmyyj.zymk.app.bean;

import java.util.List;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */
public class MuBean {

    private int book_id;
    private final String timeDesc;
    private final List<HuaBean> huaList;

    public MuBean(String time_desc, List<HuaBean> huaList) {
        this.timeDesc = time_desc;
        this.huaList = huaList;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public List<HuaBean> getHuaList() {
        return huaList;
    }
}
