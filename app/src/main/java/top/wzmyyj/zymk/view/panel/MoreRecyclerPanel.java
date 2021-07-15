package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dl7.tag.TagLayout;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.wzm_sdk.utils.DensityUtil;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.base.panel.BaseRecyclerPanel;
import top.wzmyyj.zymk.contract.MoreContract;

/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class MoreRecyclerPanel extends BaseRecyclerPanel<BookBean, MoreContract.IPresenter> {

    private ImageView imgHeader;
    private TextView tvHeader;
    private TextView tvEnd;

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
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //当距离在[0,maxDistance]变化时，透明度在[0,255之间变化]
            final int maxDistance = DensityUtil.pt2px(context, 135) - StatusBarUtil.StatusBarHeight;
            int mDistance = 0;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
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
                tv_star.setText((bookBean.getStar() + "分"));
                tv_title.setText(bookBean.getTitle());
                tv_desc.setText(bookBean.getDesc());
                tl_tag.cleanTags();
                if (bookBean.getTags() != null) {
                    tl_tag.addTags(bookBean.getTags());
                }
                GlideLoaderHelper.img(img_book, bookBean.getDataSrc());
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
        tvHeader.setText(content);
        GlideLoaderHelper.img(imgHeader, figure);
        mData.clear();
        if (books != null) mData.addAll(books);
        tvEnd.setText(("-- 共推荐" + mData.size() + "个 --"));
        notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void setHeader() {
        super.setHeader();
        mHeader = mInflater.inflate(R.layout.layout_more_header, null);
        imgHeader = mHeader.findViewById(R.id.img_1);
        tvHeader = mHeader.findViewById(R.id.tv_1);
    }

    @SuppressLint("InflateParams")
    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer, null);
        tvEnd = mFooter.findViewById(R.id.tv_end);
        tvEnd.setText(("-- 共推荐" + mData.size() + "个 --"));
    }
}
