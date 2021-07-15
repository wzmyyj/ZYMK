package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import top.wzmyyj.zymk.base.panel.BasePanel;

/**
 * Created by yyj on 2018/08/16. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class HomeItemPanel extends BasePanel<HomeContract.IPresenter> {

    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.rv_item)
    RecyclerView rvItem;

    private BookAdapter bookAdapter;
    private final List<BookBean> data = new ArrayList<>();
    private ItemBean itemBean;

    public HomeItemPanel(Context context, HomeContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_item;
    }

    @OnClick(R.id.bt_more)
    public void more() {
        if (itemBean == null) return;
        mPresenter.goMore(itemBean.getHref());
    }

    @Override
    protected void initView() {
        super.initView();
        rvItem.setNestedScrollingEnabled(false);
        rvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        bookAdapter = new BookAdapter(context, data);
        rvItem.setAdapter(bookAdapter);
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
        imgIcon.setImageResource(itemBean.getIcon());
        tvTitle.setText(item.getTitle());
        tvSummary.setText(item.getSummary());
        if (itemBean.getBooks() != null) {
            data.clear();
            data.addAll(itemBean.getBooks());
            bookAdapter.notifyDataSetChanged();
        }
    }
}
