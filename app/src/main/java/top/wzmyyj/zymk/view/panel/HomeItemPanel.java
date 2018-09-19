package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.contract.HomeContract;
import top.wzmyyj.zymk.view.adapter.BookAdapter;
import top.wzmyyj.zymk.view.panel.base.BasePanel;


/**
 * Created by yyj on 2018/08/16. email: 2209011667@qq.com
 */

public class HomeItemPanel extends BasePanel<HomeContract.IPresenter> {
    public HomeItemPanel(Context context, HomeContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_item;
    }

    @BindView(R.id.img_icon)
    ImageView img_icon;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_summary)
    TextView tv_summary;

    @OnClick(R.id.bt_more)
    public void more() {
        if (itemBean == null) return;
        mPresenter.goMore(itemBean.getHref());
    }

    @BindView(R.id.rv_item)
    RecyclerView rv_item;

    BookAdapter bookAdapter;
    List<BookBean> data = new ArrayList<>();

    ItemBean itemBean;

    @Override
    protected void initView() {
        super.initView();
        rv_item.setNestedScrollingEnabled(false);
        rv_item.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));
        bookAdapter = new BookAdapter(context, R.layout.layout_book, data);
        rv_item.setAdapter(bookAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        bookAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPresenter.goDetails(data.get(position).getHref());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    public void setItemData(ItemBean item) {
        if (item == null) return;
        itemBean = item;
        img_icon.setImageResource(itemBean.getIcon());
        tv_title.setText(item.getTitle());
        tv_summary.setText(item.getSummary());
        if (itemBean.getBooks() != null) {
            data.clear();
            data.addAll(itemBean.getBooks());
            bookAdapter.notifyDataSetChanged();
        }

    }

}
