package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.presenter.FindPresenter;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class FavorRecyclerPanel extends BaseRecyclerPanel<BookBean, FindPresenter> {
    public FavorRecyclerPanel(Context context, FindPresenter p) {
        super(context, p);
    }

    @Override
    protected void setData() {
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
                return R.layout.layout_book2;
            }

            @Override
            public void convert(ViewHolder holder, BookBean bookBean, int position) {
                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_star = holder.getView(R.id.tv_star);
                TextView tv_chapter = holder.getView(R.id.tv_chapter);
                TextView tv_title = holder.getView(R.id.tv_title);

                tv_star.setText(bookBean.getStar() + "分");
                tv_title.setText(bookBean.getTitle());
                tv_chapter.setText(bookBean.getChapter());
                G.img(context, bookBean.getData_src(), img_book);
            }
        });

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        mPresenter.goDetails(mData.get(position).getHref());
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void update() {

    }
}
