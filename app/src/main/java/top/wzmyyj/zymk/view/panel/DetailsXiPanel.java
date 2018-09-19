package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.AuthorBean;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.view.adapter.BookAdapter;
import top.wzmyyj.zymk.view.panel.base.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */

public class DetailsXiPanel extends BasePanel<DetailsContract.IPresenter> {
    public DetailsXiPanel(Context context, DetailsContract.IPresenter p) {
        super(context, p);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_details_xi;
    }

    @BindView(R.id.tv_book_juqing)
    TextView tv_juqing;
    @BindView(R.id.tv_author_name)
    TextView tv_author_name;
    @BindView(R.id.tv_author_fans_num)
    TextView tv_author_fans_num;
    @BindView(R.id.img_author_head)
    ImageView img_author_head;
    @BindView(R.id.img_author_type)
    ImageView img_author_type;
    @BindView(R.id.tv_author_say)
    TextView tv_author_say;
    @BindView(R.id.rv_author_books)
    RecyclerView rv_author_books;

    private BookAdapter bookAdapter;
    private List<BookBean> authorBooks = new ArrayList<>();


    @Override
    protected void initView() {
        super.initView();
        rv_author_books.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));
        bookAdapter = new BookAdapter(context, R.layout.layout_book, authorBooks);
        rv_author_books.setAdapter(bookAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        bookAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(position==authorBooks.size()-1){
                    T.s("喜欢就收藏哦~");
                }
                mPresenter.goDetails(authorBooks.get(position).getHref());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public void setXiData(XiBean xi){
        AuthorBean author = xi.getAuthor();
        setAuthor(author);
        tv_juqing.setText(xi.getJuqing());
    }

    private void setAuthor(AuthorBean author) {

        SpannableString ss = new SpannableString("粉丝" + author.getFans_num() + "人");
        ss.setSpan(new ForegroundColorSpan(Color.rgb(0xff, 00, 00)),
                2, author.getFans_num().length() + 2, Spanned.SPAN_COMPOSING);
        tv_author_name.setText(author.getName());
        tv_author_fans_num.setText(ss);
        tv_author_say.setText(author.getContent());

        G.img(context, author.getAvatar(), img_author_head);
        if (author.getBookList() != null) {
            authorBooks.clear();
            for (BookBean book : author.getBookList()) {
                authorBooks.add(book);
            }
            bookAdapter.notifyDataSetChanged();


            if (author.getFans_num().length() > 3 || author.getBookList().size() > 3) {
                G.img(context, R.mipmap.svg_author_dk, img_author_type);
            } else {
                G.img(context, R.mipmap.svg_author_xr, img_author_type);
            }
        }

    }
}
