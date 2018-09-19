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
import top.wzmyyj.zymk.common.utils.DensityUtil;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.MoreContract;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 */

public class MoreRecyclerPanel extends BaseRecyclerPanel<BookBean, MoreContract.IPresenter> {


    public MoreRecyclerPanel(Context context, MoreContract.IPresenter p) {
        super(context, p);
    }


    @Override
    protected void setFirstData() {
        super.setFirstData();
        mPresenter.addEmptyData(mData);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            int mDistance = 0;
            //当距离在[0,maxDistance]变化时，透明度在[0,255之间变化]
            int maxDistance = DensityUtil.dp2px(context, 135) - StatusBarUtil.StatusBarHeight;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistance += dy;
                float percent = mDistance * 1f / maxDistance;//百分比

                if (viewList.size() == 0) return;
                View top = viewList.get(0);
                top.setAlpha(percent);
            }
        });
    }

    @Override
    protected void setIVD(List<IVD<BookBean>> ivd) {
        ivd.add(new SingleIVD<BookBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book_more;
            }

            @Override
            public void convert(ViewHolder holder, BookBean bookBean, int position) {

                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_star = holder.getView(R.id.tv_star);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_desc = holder.getView(R.id.tv_desc);


                TagLayout tl_tag = holder.getView(R.id.tl_tag);


                tv_star.setText(bookBean.getStar() + "分");
                tv_title.setText(bookBean.getTitle());
                tv_desc.setText(bookBean.getDesc());

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
        mPresenter.goDetails(mData.get(position - 1).getHref());
    }

    @Override
    public void update() {
        mPresenter.loadData();
    }


    public void setMoreData(String content, String figure, List<BookBean> books) {
        tv_1.setText(content);
        G.img(context, figure, img_1);

        mData.clear();
        if (books != null) {
            mData.addAll(books);
        }

        tv_end.setText("-- 共推荐" + mData.size() + "个 --");
        notifyDataSetChanged();
    }


    private ImageView img_1;
    private TextView tv_1;


    @Override
    protected void setHeader() {
        super.setHeader();
        mHeader = mInflater.inflate(R.layout.activity_more_header, null);
        img_1 = mHeader.findViewById(R.id.img_1);
        tv_1 = mHeader.findViewById(R.id.tv_1);


//        G.img(context, "https://image.zymkcdn.com/file/book/000/000/008.jpg-noresize.webp", img_1);
//        tv_1.setText("每一部漫画的结束都代表着新的启程。时光荏苒，让我们再重温那些有笑有泪的岁月。");
        //魔法、仙力、电波系，这个世界多么神秘啊，不寻常的日常，不无聊的成长，给你一个身临其境的机会，感受下各种各样的平行世界吧！
    }


    private TextView tv_end;


    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer, null);
        tv_end = mFooter.findViewById(R.id.tv_end);
        tv_end.setText("-- 共推荐" + mData.size() + "个 --");
    }

}
