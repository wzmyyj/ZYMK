package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.presenter.HomePresenter;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/07/04. email: 2209011667@qq.com
 */

public class HomeRecyclerPanel extends BaseRecyclerPanel<ItemBean, HomePresenter> {

    public HomeRecyclerPanel(Context context, HomePresenter p) {
        super(context, p);
    }

    @Override
    protected void setData() {
        mPresenter.addEmptyData(mData);
        updateWithView();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            int mDistance = 0;
            int maxDistance = 300;//当距离在[0,maxDistance]变化时，透明度在[0,255之间变化]

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
    protected void setIVD(List<IVD<ItemBean>> ivd) {
        ivd.add(new SingleIVD<ItemBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.fragment_1_item;
            }

            @Override
            public void convert(ViewHolder holder, ItemBean itemBean, int position) {
                ImageView img_icon = holder.getView(R.id.img_icon);
                TextView tv_title = holder.getView(R.id.tv_title);
                final TextView tv_summary = holder.getView(R.id.tv_summary);
                img_icon.setImageResource(itemBean.getIcon());
                tv_title.setText(itemBean.getTitle());
                tv_summary.setText(itemBean.getSummary());

                final String href = itemBean.getHref();
                Button bt_more = holder.getView(R.id.bt_more);
                bt_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.goMore(href);
                    }
                });

                List<BookBean> data = itemBean.getBooks() != null
                        ? itemBean.getBooks() : new ArrayList<BookBean>();

                RecyclerView rv_item = holder.getView(R.id.rv_item);
                rv_item.setRecycledViewPool(viewPool);
                rv_item.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));
                rv_item.setAdapter(new CommonAdapter<BookBean>(context, R.layout.layout_book, data) {
                    @Override
                    protected void convert(ViewHolder holder, BookBean bookBean, int position) {
                        ImageView img_book = holder.getView(R.id.img_book);
                        TextView tv_star = holder.getView(R.id.tv_star);
                        TextView tv_chapter = holder.getView(R.id.tv_chapter);
                        TextView tv_title = holder.getView(R.id.tv_title);
                        TextView tv_desc = holder.getView(R.id.tv_desc);

                        tv_star.setText(bookBean.getStar() + "分");
                        tv_title.setText(bookBean.getTitle());
                        tv_chapter.setText(bookBean.getChapter());
                        tv_desc.setText(bookBean.getDesc());

                        G.img(context, bookBean.getData_src(), img_book);

                        final String href1 = bookBean.getHref();
                        img_book.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.goDetails(href1);
                            }
                        });
                    }


                });


            }

            RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        });
    }

    @Override
    public void update() {
        mPresenter.loadData();
    }

    @Override
    public Object f(int w, Object... objects) {
        List<BoBean> bos = (List<BoBean>) objects[0];
        mPanels.get(0).f(w, bos);

        mData.clear();
        List<ItemBean> itemBeans = (List<ItemBean>) objects[1];
        for (ItemBean item : itemBeans) {
            mData.add(item);
        }
        notifyDataSetChanged();
        return super.f(w, objects);
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new TopBoPanel(activity, mPresenter));
    }

    @Override
    protected void setHeader() {
        super.setHeader();
        mHeader = mInflater.inflate(R.layout.fragment_1_header, null);
        LinearLayout layout = mHeader.findViewById(R.id.ll_panel);
        layout.addView(getPanelView(0));
        LinearLayout ll_h_1 = mHeader.findViewById(R.id.ll_h_1);
        LinearLayout ll_h_2 = mHeader.findViewById(R.id.ll_h_2);
        ll_h_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("你点击了更新！");
            }
        });
        ll_h_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("你点击了排行！");
            }
        });
    }

    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer, null);
        TextView tv = mFooter.findViewById(R.id.tv_end);
        tv.setText("-- 没有了哦 --");
    }
}
