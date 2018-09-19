package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import top.wzmyyj.zymk.contract.ResultContract;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/07/29. email: 2209011667@qq.com
 */

public class ResultRecyclerPanel extends BaseRecyclerPanel<BookBean, ResultContract.IPresenter> {
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
    public void update() {
        mPresenter.loadData();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
    }

//    private List<String> mBaseList = new ArrayList<>();

    private String next_href;

    @Override
    protected void loadMore() {
        super.loadMore();
        if (!isRuning && !TextUtils.isEmpty(next_href)) {
            mPresenter.loadData(next_href);
            isRuning = true;
        }
    }

    private boolean isRuning = false;


    public void loadFail(String msg) {
        tv_end.setText("-- 加载失败,稍后再试 --");
        notifyDataSetChanged();
    }

    public void setResultData(boolean isFirst, List<BookBean> books, String base, String next) {
        isRuning = false;
        if (books == null && books.size() == 0) {
            loadFail("No Books");
            return;
        }

        if (isFirst) {
            mData.clear();
        }

        mData.addAll(books);
        next_href = next;
        notifyDataSetChanged();
    }

    TextView tv_end;

    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer2, null);
        tv_end = mFooter.findViewById(R.id.tv_end);
        tv_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMore();
            }
        });
    }

    protected TextView tv_empty;

    @Override
    protected void setEmpty() {
        mEmpty = mInflater.inflate(R.layout.layout_empty, null);
        tv_empty = mEmpty.findViewById(R.id.tv_empty_text);
        mEmpty.setVisibility(View.GONE);
        tv_empty.setText("什么都没有哦。。。");
    }

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        if (TextUtils.isEmpty(next_href)) {
            tv_end.setText("-- 没有了哦 --");
        } else {
            tv_end.setText("-- 加载更多 --");
        }
    }
}
