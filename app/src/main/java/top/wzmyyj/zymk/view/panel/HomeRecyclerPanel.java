package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;
import top.wzmyyj.wzm_sdk.panel.RecyclerPanel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;


/**
 * Created by wzm on 2018/07/04. email: 2209011667@qq.com
 */

public class HomeRecyclerPanel extends RecyclerPanel<BookBean> {
    public HomeRecyclerPanel(Context context) {
        super(context);
    }

    @Override
    protected void setData() {
        mData.add(new BookBean());
        mData.add(new BookBean());
        mData.add(new BookBean());
        mData.add(new BookBean());
        mData.add(new BookBean());
        mData.add(new BookBean());
        mData.add(new BookBean());
        mData.add(new BookBean());
        mData.add(new BookBean());
    }

    @Override
    protected void setIVD(List<IVD<BookBean>> ivd) {
        ivd.add(new SingleIVD<BookBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book;
            }

            @Override
            public void convert(ViewHolder holder, BookBean bookBean, int position) {

            }
        });
    }

    @Override
    protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new TopBoPanel(activity));
    }

    @Override
    protected View getHeader() {
        mHeader = mInflater.inflate(R.layout.fragment_1_header, null);
        LinearLayout layout = mHeader.findViewById(R.id.ll_panel);
        layout.addView(getPanelView(0));
        return mHeader;
    }

    @Override
    protected View getFooter() {
        return null;
    }
}
