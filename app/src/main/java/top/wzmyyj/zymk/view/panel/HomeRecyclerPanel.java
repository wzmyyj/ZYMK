package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by wzm on 2018/07/04. email: 2209011667@qq.com
 */

public class HomeRecyclerPanel extends BaseRecyclerPanel<ItemBean> {
    public HomeRecyclerPanel(Context context) {
        super(context);
    }

    @Override
    protected void setData() {
        mData.add(new ItemBean());
        mData.add(new ItemBean());
        mData.add(new ItemBean());
        mData.add(new ItemBean());
        mData.add(new ItemBean());
        mData.add(new ItemBean());
        mData.add(new ItemBean());
        mData.add(new ItemBean());
        mData.add(new ItemBean());
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
                TextView tv_summary = holder.getView(R.id.tv_summary);
                Button bt_more = holder.getView(R.id.bt_more);
                bt_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.s("你点击了更多！");
                    }
                });

                List<BookBean> data = new ArrayList<>();
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());
                data.add(new BookBean());

                RecyclerView rv_item = holder.getView(R.id.rv_item);
                rv_item.setRecycledViewPool(viewPool);
                rv_item.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));
                rv_item.setAdapter(new CommonAdapter<BookBean>(context, R.layout.layout_book, data) {
                    @Override
                    protected void convert(ViewHolder holder, BookBean bookBean, int position) {
                        ImageView img_book = holder.getView(R.id.img_book);
                        TextView tv_star = holder.getView(R.id.tv_star);
                        TextView tv_last = holder.getView(R.id.tv_last);
                        TextView tv_title = holder.getView(R.id.tv_title);
                        TextView tv_desc = holder.getView(R.id.tv_desc);

                        Glide.with(context)
                                .load("https://image.zymkcdn.com/file/cover/000/000/700.jpg-300x400.webp")
                                .error(R.mipmap.ic_error)
                                .into(img_book);
                    }

                    @Override
                    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
                        super.setOnItemClickListener(onItemClickListener);
                    }
                });


            }

            RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        });
    }


    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new TopBoPanel(activity));
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
