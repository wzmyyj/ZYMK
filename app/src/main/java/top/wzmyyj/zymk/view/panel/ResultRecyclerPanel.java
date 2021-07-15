package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.contract.ResultContract;
import top.wzmyyj.zymk.base.panel.BaseRecyclerPanel;

/**
 * Created by yyj on 2018/07/29. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class ResultRecyclerPanel extends BaseRecyclerPanel<BookBean, ResultContract.IPresenter> {

    private TextView tvEnd;
    private String nextHref;
    private boolean isRunning = false;

    public ResultRecyclerPanel(Context context, ResultContract.IPresenter p) {
        super(context, p);
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
                tv_star.setText((bookBean.getStar() + "分"));
                tv_title.setText(bookBean.getTitle());
                tv_chapter.setText(bookBean.getChapter());
                GlideLoaderHelper.img(img_book, bookBean.getDataSrc());
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

    @Override
    protected void initView() {
        super.initView();
        mRefreshLayout.setEnableLoadMore(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    protected void loadMore() {
        super.loadMore();
        if (!isRunning && !TextUtils.isEmpty(nextHref)) {
            mPresenter.loadData(nextHref);
            isRunning = true;
        }
    }

    public void loadFail(String msg) {
        tvEnd.setText("-- 加载失败,稍后再试 --");
        mPresenter.log(msg);
        notifyDataSetChanged();
    }

    public void setResultData(boolean isFirst, List<BookBean> books, String base, String next) {
        mPresenter.log("base:" + base + "; next:" + next);
        isRunning = false;
        if (books == null || books.size() == 0) {
            loadFail("No Books");
            return;
        }
        if (isFirst) mData.clear();
        mData.addAll(books);
        nextHref = next;
        notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer2, null);
        tvEnd = mFooter.findViewById(R.id.tv_end);
        tvEnd.setOnClickListener(v -> loadMore());
    }

    @SuppressLint("InflateParams")
    @Override
    protected void setEmpty() {
        mEmpty = mInflater.inflate(R.layout.layout_empty, null);
        TextView tv_empty = mEmpty.findViewById(R.id.tv_empty_text);
        mEmpty.setVisibility(View.GONE);
        tv_empty.setText("什么都没有哦。。。");
    }

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        if (TextUtils.isEmpty(nextHref)) {
            tvEnd.setText("-- 没有了哦 --");
        } else {
            tvEnd.setText("-- 加载更多 --");
        }
    }
}
