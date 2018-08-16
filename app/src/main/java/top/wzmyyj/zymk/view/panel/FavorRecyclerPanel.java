package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.common.java.Vanessa;
import top.wzmyyj.zymk.presenter.FindPresenter;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class FavorRecyclerPanel extends BaseRecyclerPanel<FavorBean, FindPresenter> {
    public FavorRecyclerPanel(Context context, FindPresenter p) {
        super(context, p);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setIVD(List<IVD<FavorBean>> ivd) {
        ivd.add(new SingleIVD<FavorBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book_favor;
            }

            @Override
            public void convert(ViewHolder holder, FavorBean favorBean, int position) {
                BookBean bookBean = favorBean.getBook();
                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_new = holder.getView(R.id.tv_new);
                TextView tv_chapter = holder.getView(R.id.tv_chapter);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_update_time = holder.getView(R.id.tv_update_time);

                tv_title.setText(bookBean.getTitle());
                tv_chapter.setText(bookBean.getChapter());

                long update_time = bookBean.getUpdate_time();
                // 最新更新是否一周内。
                if (Vanessa.isInDay(update_time, 7)) {
                    tv_new.setVisibility(View.VISIBLE);
                } else {
                    tv_new.setVisibility(View.GONE);
                }
                tv_update_time.setText(Vanessa.getEasyText(update_time));

                G.img(context, bookBean.getData_src(), img_book);

                ImageView img_select = holder.getView(R.id.img_select);
                if (isCanSelect) {
                    img_select.setVisibility(View.VISIBLE);
                } else {
                    img_select.setVisibility(View.GONE);
                }

            }
        });

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        mPresenter.goDetails(mData.get(position).getBook().getHref());
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void update() {

    }

    private boolean isCanSelect;

    @Override
    protected void setHeader() {
        super.setHeader();
    }
}
