package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.common.java.Vanessa;
import top.wzmyyj.zymk.contract.FindContract;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class HistoryRecyclerPanel extends FindRecyclerPanel<HistoryBean> {
    public HistoryRecyclerPanel(Context context, FindContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected void setIVD(List<IVD<HistoryBean>> ivd) {
        ivd.add(new IVD<HistoryBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book_history;
            }

            @Override
            public boolean isForViewType(HistoryBean item, int position) {
                return !isGongge;// 列表形式。
            }

            @Override
            public void convert(ViewHolder holder, HistoryBean historyBean, int position) {
                BookBean bookBean = historyBean.getBook();
                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_title = holder.getView(R.id.tv_title);
                tv_title.setText(bookBean.getTitle());
                G.img(context, bookBean.getData_src(), img_book);


                // 已读章节。
                ChapterBean chapterBean = historyBean.getChapter();
                TextView tv_chapter = holder.getView(R.id.tv_chapter);
                tv_chapter.setText(chapterBean.getChapter_name());

                // 上次阅读时间。
                TextView tv_read_time = holder.getView(R.id.tv_read_time);
                tv_read_time.setText(Vanessa.getEasyText(historyBean.getRead_time()));


                LinearLayout ll_continue_read = holder.getView(R.id.ll_continue_read);
                ImageView img_continue_read = holder.getView(R.id.img_continue_read);
                ImageView img_select = holder.getView(R.id.img_select);
                RelativeLayout rl_select = holder.getView(R.id.rl_select);
                if (isCanSelect) {
                    rl_select.setVisibility(View.VISIBLE);
                    ll_continue_read.setVisibility(View.GONE);
                } else {
                    rl_select.setVisibility(View.GONE);
                    ll_continue_read.setVisibility(View.VISIBLE);
                }
                if (isSelect(historyBean)) {
                    img_select.setImageResource(R.mipmap.icon_mine_has_selected);
                } else {
                    img_select.setImageResource(R.mipmap.icon_mine_not_select);
                }

                final long chapter_id = chapterBean.getChapter_id();
                final int comic_id = bookBean.getId();
                img_continue_read.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.goComic(comic_id, chapter_id);
                    }
                });

            }
        });

        ivd.add(new IVD<HistoryBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book_find;
            }

            @Override
            public boolean isForViewType(HistoryBean item, int position) {
                return isGongge;// 表格形式。
            }

            @Override
            public void convert(ViewHolder holder, HistoryBean historyBean, int position) {
                BookBean bookBean = historyBean.getBook();
                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_some = holder.getView(R.id.tv_some);

                tv_title.setText(bookBean.getTitle());


                tv_some.setText(Vanessa.getEasyText(historyBean.getRead_time()));

                G.img(context, bookBean.getData_src(), img_book);

                ImageView img_select = holder.getView(R.id.img_select);
                RelativeLayout rl_select = holder.getView(R.id.rl_select);
                if (isCanSelect) {
                    rl_select.setVisibility(View.VISIBLE);
                } else {
                    rl_select.setVisibility(View.GONE);
                }
                if (isSelect(historyBean)) {
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
            if (isSelect(mData.get(position))) {
                unSelect(mData.get(position));
            } else {
                select(mData.get(position));
            }
        } else {
            mPresenter.goDetails(mData.get(position).getBook().getHref());
        }
    }

    @Override
    public void update() {
        mPresenter.loadHistory();
    }

    @Override
    protected void sort() {
        super.sort();
        Collections.sort(mData, new Comparator<HistoryBean>() {

            @Override
            public int compare(HistoryBean o1, HistoryBean o2) {
                if (o1.getRead_time() < o2.getRead_time()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    @Override
    protected Long getLongId(HistoryBean historyBean) {
        return (long) historyBean.getBook().getId();
    }

    @Override
    protected void delete(Long[] ids) {
        mPresenter.deleteSomeHistory(ids);
    }

    @Override
    protected void initView() {
        super.initView();
        tv_empty.setText("奴家在等大人的宠幸~");
    }
}
