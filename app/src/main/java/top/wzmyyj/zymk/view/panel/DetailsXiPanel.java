package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.AuthorBean;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.view.adapter.BookAdapter;
import top.wzmyyj.zymk.base.panel.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class DetailsXiPanel extends BasePanel<DetailsContract.IPresenter> {

    @BindView(R.id.tv_book_plot)
    TextView tvPlot;
    @BindView(R.id.tv_author_name)
    TextView tvAuthorName;
    @BindView(R.id.tv_author_fans_num)
    TextView tvAuthorFansNum;
    @BindView(R.id.img_author_head)
    ImageView imgAuthorHead;
    @BindView(R.id.img_author_type)
    ImageView imgAuthorType;
    @BindView(R.id.tv_author_say)
    TextView tvAuthorSay;
    @BindView(R.id.rv_author_books)
    RecyclerView rvAuthorBooks;

    private BookAdapter bookAdapter;
    private final List<BookBean> authorBooks = new ArrayList<>();

    public DetailsXiPanel(Context context, DetailsContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_details_xi;
    }

    @Override
    protected void initView() {
        super.initView();
        rvAuthorBooks.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        bookAdapter = new BookAdapter(context, authorBooks);
        rvAuthorBooks.setAdapter(bookAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        bookAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (position == authorBooks.size() - 1) T.s("喜欢就收藏哦~");
                mPresenter.goDetails(authorBooks.get(position).getHref());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public void setXiData(XiBean xi) {
        AuthorBean author = xi.getAuthor();
        setAuthor(author);
        tvPlot.setText(xi.getPlot());
    }

    private void setAuthor(AuthorBean author) {
        SpannableString ss = new SpannableString("粉丝" + author.getFansNum() + "人");
        ss.setSpan(new ForegroundColorSpan(Color.rgb(0xff, 0x00, 0x00)),
                2, author.getFansNum().length() + 2, Spanned.SPAN_COMPOSING);
        tvAuthorName.setText(author.getName());
        tvAuthorFansNum.setText(ss);
        tvAuthorSay.setText(author.getContent());
        GlideLoaderHelper.img(imgAuthorHead, author.getAvatar());
        if (author.getBookList() != null) {
            authorBooks.clear();
            authorBooks.addAll(author.getBookList());
            bookAdapter.notifyDataSetChanged();
            if (author.getFansNum().length() > 3 || author.getBookList().size() > 3) {
                GlideLoaderHelper.img(imgAuthorType, R.mipmap.svg_author_dk);
            } else {
                GlideLoaderHelper.img(imgAuthorType, R.mipmap.svg_author_xr);
            }
        }
    }
}
