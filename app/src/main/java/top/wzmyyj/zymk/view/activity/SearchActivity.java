package top.wzmyyj.zymk.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.tag.TagLayout;
import com.dl7.tag.TagView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.presenter.SearchPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.iv.ISearchView;


/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements ISearchView {
    @Override
    protected void initPresenter() {
        mPresenter = new SearchPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @BindView(R.id.et_text)
    EditText et_text;

    @OnClick(R.id.img_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.img_search)
    public void search() {
        String s = et_text.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            mPresenter.search(s);
        } else {
            showToast("请输入 漫画名 或 漫画家 ~");
        }
    }

    @OnClick(R.id.tv_clear)
    public void clearAll() {
        mPresenter.delAllHistory();
    }

    @BindView(R.id.tl_tag)
    TagLayout tl_tag;

    private List<BookBean> mTagList = new ArrayList<>();


    @BindView(R.id.rv_history)
    RecyclerView rv_history;

    List<String> mHsList = new ArrayList<>();
    CommonAdapter mHsAdapter;


    @BindView(R.id.rv_smart)
    RecyclerView rv_smart;

    List<String> mSmartList = new ArrayList<>();
    CommonAdapter mSmartAdapter;

    @Override
    protected void initView() {
        super.initView();
        rv_history.setLayoutManager(new LinearLayoutManager(context));
        mHsAdapter = new CommonAdapter<String>(context, R.layout.layout_search_history_item, mHsList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                TextView tv_text = holder.getView(R.id.tv_title);
                ImageView img_fork = holder.getView(R.id.img_fork);
                tv_text.setText(s);
                final String txt = s;
                img_fork.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.delHistory(txt);
                    }
                });
            }
        };
        rv_history.setAdapter(mHsAdapter);


    }

    @Override
    protected void initListener() {
        super.initListener();
        et_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.smart(s.toString());
            }
        });
        tl_tag.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int i, String s, int i1) {
                mPresenter.search(mTagList.get(i).getHref());
            }
        });

        mHsAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPresenter.search(mHsList.get(position));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    @Override
    public void hotSearch(List list) {


    }

    @Override
    public void historySearch(List list) {
        List<BookBean> tags = list;
        if (tags != null && tags.size() > 0) {
            mTagList.clear();
            mTagList.addAll(tags);
            tl_tag.cleanTags();
            for (BookBean book : mTagList) {
                tl_tag.addTag(book.getTitle());
            }

        }
    }

    @Override
    public void smartSearch(List list) {

    }
}
