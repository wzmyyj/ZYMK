package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.tag.TagLayout;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.contract.RankContract;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */

public class RankRecyclerPanel extends BaseRecyclerPanel<BookBean, RankContract.IPresenter> {
    public RankRecyclerPanel(Context context, RankContract.IPresenter p) {
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
                return R.layout.layout_book_rank;
            }

            @Override
            public void convert(ViewHolder holder, BookBean bookBean, int position) {

                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_num = holder.getView(R.id.tv_num);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_what = holder.getView(R.id.tv_what);
                TextView tv_ift = holder.getView(R.id.tv_ift);

                tv_title.setText(bookBean.getTitle());
                tv_what.setText(title.replace("榜", "") + "：");
                tv_ift.setText(bookBean.getIft());

                String num = bookBean.getNum() == null ? "0" : bookBean.getNum();
                int n = Integer.parseInt(num);
                switch (n) {
                    case 1:
                        tv_num.setText("");
                        tv_num.setBackgroundResource(R.mipmap.ic_rank_1);
                        break;
                    case 2:
                        tv_num.setText("");
                        tv_num.setBackgroundResource(R.mipmap.ic_rank_2);
                        break;
                    case 3:
                        tv_num.setText("");
                        tv_num.setBackgroundResource(R.mipmap.ic_rank_3);
                        break;
                    default:
                        tv_num.setText("" + n);
                        tv_num.setBackgroundResource(R.color.colorClarity);
                        break;
                }

                TagLayout tl_tag = holder.getView(R.id.tl_tag);
                tl_tag.cleanTags();

                if (bookBean.getTags() != null) {
                    for (String tag : bookBean.getTags()) {
                        tl_tag.addTag(tag);
                    }
                }

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


    public void setRankData(List<BookBean> books) {
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
