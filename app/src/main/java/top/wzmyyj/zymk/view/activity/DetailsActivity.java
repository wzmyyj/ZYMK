package top.wzmyyj.zymk.view.activity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.tag.TagLayout;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.bean.ZiBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.presenter.DetailsPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.iv.IDetailsView;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView {


    @Override
    protected void initPresenter() {
        mPresenter = new DetailsPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.img_love)
    ImageView img_love;
    @BindView(R.id.img_back)
    ImageView img_bock;
    @BindView(R.id.img_book_bg)
    ImageView img_book_bg;
    @BindView(R.id.tv_book_title)
    TextView tv_book_title;
    @BindView(R.id.tv_book_author)
    TextView tv_book_author;
    @BindView(R.id.tl_book_tag)
    TagLayout tl_book_tag;
    @BindView(R.id.tv_book_ift)
    TextView tv_book_ift;
    @BindView(R.id.img_book)
    ImageView img_book;
    @BindView(R.id.tv_book_star)
    TextView tv_book_star;
    @BindView(R.id.bt_save)
    Button bt_save;
    @BindView(R.id.bt_read)
    Button bt_read;


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    public void update(int w, Object... objs) {

    }

    @Override
    public void setBook(BookBean book) {
        tv_title.setText(book.getTitle());
        tv_book_title.setText(book.getTitle());
        tv_book_author.setText(book.getAuthor());
        tv_book_ift.setText(book.getIft());
        tv_book_star.setText(book.getStar()+"分");

        tl_book_tag.cleanTags();

        if (book.getTags() != null) {
            for (String tag : book.getTags()) {
                tl_book_tag.addTag(tag);
            }
        }
        G.img(context, book.getData_src(), img_book);
        G.imgBlur(context, book.getData_src(), img_book_bg,23);

    }

    @Override
    public void setXi(XiBean xi) {

    }

    @Override
    public void setMu(MuBean mu) {

    }

    @Override
    public void setZi(ZiBean zi) {

    }

    @Override
    public void setBookList(List<BookBean> list) {

    }
}
