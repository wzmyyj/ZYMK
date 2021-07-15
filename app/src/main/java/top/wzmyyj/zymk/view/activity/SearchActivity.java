package top.wzmyyj.zymk.view.activity;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dl7.tag.TagLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.contract.SearchContract;
import top.wzmyyj.zymk.presenter.SearchPresenter;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class SearchActivity extends BaseActivity<SearchContract.IPresenter> implements SearchContract.IView {

    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tl_tag)
    TagLayout tlTag;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.rv_smart)
    RecyclerView rvSmart;

    private final List<SearchHistoryBean> mShList = new ArrayList<>();
    private CommonAdapter<SearchHistoryBean> mShAdapter;
    private final List<BookBean> mSmartList = new ArrayList<>();
    private CommonAdapter<BookBean> mSmartAdapter;
    private final List<BookBean> mHotList = new ArrayList<>();
    private String KEY = "";

    @Override
    protected void initPresenter() {
        mPresenter = new SearchPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @OnClick(R.id.img_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.img_search)
    public void search() {
        String s = etText.getText().toString();
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

    @Override
    protected void initView() {
        super.initView();
        rvHistory.setLayoutManager(new LinearLayoutManager(context));
        mShAdapter = new CommonAdapter<SearchHistoryBean>(context, R.layout.layout_search_history, mShList) {
            @Override
            protected void convert(ViewHolder holder, SearchHistoryBean sh, int position) {
                TextView tv_text = holder.getView(R.id.tv_text);
                ImageView img_fork = holder.getView(R.id.img_fork);
                tv_text.setText(("" + sh.getWord()));
                final long id = sh.getId();
                img_fork.setOnClickListener(v -> mPresenter.delHistory(id));
            }
        };
        rvHistory.setAdapter(mShAdapter);
        rvSmart.setLayoutManager(new LinearLayoutManager(context));
        mSmartAdapter = new CommonAdapter<BookBean>(context, R.layout.layout_search_smart, mSmartList) {
            @Override
            protected void convert(ViewHolder holder, BookBean book, int position) {
                TextView tv_num = holder.getView(R.id.tv_num);
                TextView tv_text = holder.getView(R.id.tv_title);
                TextView tv_chapter = holder.getView(R.id.tv_chapter);
                tv_num.setText((position + 1 + ""));
                tv_chapter.setText(book.getChapter());
                String title = book.getTitle();
                SpannableString ss = new SpannableString(title);
                if (title.contains(KEY)) {
                    int a = title.indexOf(KEY);
                    ss.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)),
                            a, a + KEY.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
                tv_text.setText(ss);
            }
        };
        rvSmart.setAdapter(mSmartAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getHotTags();
        mPresenter.getHistory();
    }

    @Override
    protected void initListener() {
        super.initListener();
        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    mPresenter.smart(s.toString());
                    rvSmart.setVisibility(View.VISIBLE);
                } else {
                    rvSmart.setVisibility(View.GONE);
                    mSmartList.clear();
                    mSmartAdapter.notifyDataSetChanged();
                }
            }
        });
        tlTag.setTagClickListener((i, s, i1) -> {
            String title = mHotList.get(i).getTitle();
            String href = mHotList.get(i).getHref();
            mPresenter.goDetails(href, title);
            mPresenter.addHistory(title);
        });
        mShAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPresenter.search(mShList.get(position).getWord());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mSmartAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String title = mSmartList.get(position).getTitle();
                String href = mSmartList.get(position).getHref();
                mPresenter.goDetails(href, title);
                mPresenter.addHistory(title);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        etText.setText("");
    }

    @Override
    public void showHot(List<BookBean> list) {
        if (list != null) {
            mHotList.clear();
            mHotList.addAll(list);
            tlTag.cleanTags();
            for (BookBean book : mHotList) {
                tlTag.addTag(book.getTitle());
            }
        }
    }

    @Override
    public void showSmart(String key, List<BookBean> list) {
        if (key != null) {
            KEY = key;
        }
        if (list != null) {
            mSmartList.clear();
            mSmartList.addAll(list);
            mSmartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showHistory(List<SearchHistoryBean> list) {
        if (list == null || list.size() == 0) {
            llHistory.setVisibility(View.GONE);
            return;
        }
        mShList.addAll(list);
        notifyHistoryChanged();
    }

    private void notifyHistoryChanged() {
        if (mShList.size() == 0) {
            llHistory.setVisibility(View.GONE);
        } else {
            llHistory.setVisibility(View.VISIBLE);
        }
        Collections.sort(mShList, (o1, o2) -> Long.compare(o2.getTime(), o1.getTime()));
        mShAdapter.notifyDataSetChanged();
    }

    @Override
    public void addHistory(SearchHistoryBean bean) {
        if (bean == null) return;
        for (SearchHistoryBean b : mShList) {
            if (bean.getWord().equals(b.getWord())) {
                mShList.remove(b);
                mShList.add(bean);
                notifyHistoryChanged();
                return;
            }
        }
        mShList.add(bean);
        notifyHistoryChanged();
    }

    @Override
    public void removeHistory(long l) {
        for (SearchHistoryBean bean : mShList) {
            if (bean.getId() == l) {
                mShList.remove(bean);
                notifyHistoryChanged();
                return;
            }
        }
    }

    @Override
    public void removeAllHistory() {
        mShList.clear();
        notifyHistoryChanged();
    }
}
