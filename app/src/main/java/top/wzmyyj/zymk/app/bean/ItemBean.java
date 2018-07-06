package top.wzmyyj.zymk.app.bean;

import android.support.v7.widget.RecyclerView;

/**
 * Created by wzm on 2018/06/30. email: 2209011667@qq.com
 */

public class ItemBean {
    private RecyclerView.Adapter adapter;

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }
}

