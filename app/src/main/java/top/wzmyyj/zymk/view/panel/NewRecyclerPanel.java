package top.wzmyyj.zymk.view.panel;

import android.content.Context;
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
import top.wzmyyj.zymk.contract.NewContract;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */

public class NewRecyclerPanel extends BaseRecyclerPanel<BookBean, NewContract.IPresenter> {
    public NewRecyclerPanel(Context context, NewContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected void setFirstData() {
        super.setFirstData();
        mPresenter.addEmptyData(mData);
    }


    @Override
    protected void setIVD(List<IVD<BookBean>> ivd) {
        ivd.add(new SingleIVD<BookBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book_new;
            }

            @Override
            public void convert(ViewHolder holder, BookBean bookBean, int position) {

                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_star = holder.getView(R.id.tv_star);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_desc = holder.getView(R.id.tv_desc);
                TextView tv_chapter = holder.getView(R.id.tv_chapter);

                tv_star.setText(bookBean.getStar() + "分");
                tv_title.setText(bookBean.getTitle());
                tv_chapter.setText(bookBean.getChapter());
                tv_desc.setText(bookBean.getDesc());

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
    public void update() {
        mPresenter.loadData();
    }

    public void setNewData(List<BookBean> books) {
        if (books == null) return;
        mData.clear();
        for (BookBean item : books) {
            mData.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer, null);
        TextView tv = mFooter.findViewById(R.id.tv_end);
        tv.setText("-- 没有了哦 --");
    }
}
