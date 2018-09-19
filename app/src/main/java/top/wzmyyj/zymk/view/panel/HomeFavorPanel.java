package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.common.java.Vanessa;
import top.wzmyyj.zymk.contract.HomeContract;
import top.wzmyyj.zymk.view.panel.base.BasePanel;


/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */

public class HomeFavorPanel extends BasePanel<HomeContract.IPresenter> {
    public HomeFavorPanel(Context context, HomeContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_favor;
    }

    @OnClick({R.id.fl_home_favor, R.id.tv_home_know})
    public void close() {
        view.setVisibility(View.GONE);
    }

    @BindView(R.id.rv_home_favor)
    RecyclerView mRecyclerView;

    CommonAdapter<FavorBean> mAdapter;

    List<FavorBean> mData = new ArrayList<>();


    @Override
    protected void initView() {
        super.initView();
        view.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));
        mAdapter = new CommonAdapter<FavorBean>(context, R.layout.layout_book_home_favor, mData) {

            @Override
            protected void convert(ViewHolder holder, FavorBean favorBean, int position) {
                BookBean bookBean = favorBean.getBook();
                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_new = holder.getView(R.id.tv_new);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_some = holder.getView(R.id.tv_some);

                tv_title.setText(bookBean.getTitle());


                long update_time = bookBean.getUpdate_time();
                // 最新更新是否三天内。
                if (Vanessa.isInDay(update_time, 3)) {
                    tv_new.setVisibility(View.VISIBLE);
                } else {
                    tv_new.setVisibility(View.GONE);
                }

                tv_some.setText(bookBean.getChapter());

                G.img(context, bookBean.getData_src(), img_book);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPresenter.goDetails(mData.get(position).getBook().getHref());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    public void setFavorData(List<FavorBean> list) {
        if (list != null && list.size() > 0) {
            view.setVisibility(View.VISIBLE);
            mData.clear();
            mData.addAll(list);
            mAdapter.notifyDataSetChanged();
        } else {
            view.setVisibility(View.GONE);
            mData.clear();
            mAdapter.notifyDataSetChanged();
        }
    }
}
