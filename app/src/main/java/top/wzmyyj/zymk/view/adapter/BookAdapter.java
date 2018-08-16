package top.wzmyyj.zymk.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.tools.G;


/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */

public class BookAdapter extends CommonAdapter<BookBean> {

    public BookAdapter(Context context, int layoutId, final List<BookBean> datas) {
        super(context, layoutId, datas);
    }

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
        G.img(mContext, bookBean.getData_src(), img_book);
//        final String href = bookBean.getHref();
//        img_book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                I.toDetailsActivity(mContext, href);
//            }
//        });
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder != null) {
            G.clear(mContext, (ImageView) holder.getView(R.id.img_book));
        }
    }
}
