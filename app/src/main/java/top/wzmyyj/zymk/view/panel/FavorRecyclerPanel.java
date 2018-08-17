package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Collections;
import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.app.utils.FavorComparator;
import top.wzmyyj.zymk.common.java.Vanessa;
import top.wzmyyj.zymk.presenter.FindPresenter;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class FavorRecyclerPanel extends FindRecyclerPanel<FavorBean> {
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
                if (isSelect(favorBean)) {
                    img_select.setImageResource(R.mipmap.icon_mine_has_selected);
                } else {
                    img_select.setImageResource(R.mipmap.icon_mine_not_select);
                }

            }
        });

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        if (isCanSelect) {
            if (isSelect(mData.get(position - 1))) {
                unSelect(mData.get(position - 1));
            } else {
                select(mData.get(position - 1));
            }
        } else {
            mPresenter.goDetails(mData.get(position - 1).getBook().getHref());
        }
    }

    @Override
    public void update() {
        mPresenter.loadFavor();
    }

    @Override
    protected void sort() {
        super.sort();
        Collections.sort(mData, new FavorComparator());
    }

    @Override
    protected Long getLongId(FavorBean favorBean) {
        return (long) favorBean.getBook().getId();
    }

    @Override
    protected void delete(Long[] ids) {
        mPresenter.deleteSomeFavor(ids);
    }

    @Override
    protected void initView() {
        super.initView();
        tv_empty.setText("大人，您的后宫空空如也...");
    }
}
