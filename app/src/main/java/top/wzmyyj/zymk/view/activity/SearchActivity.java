package top.wzmyyj.zymk.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.dl7.tag.TagLayout;
import com.dl7.tag.TagView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.contract.SearchContract;
import top.wzmyyj.zymk.presenter.SearchPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;


/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchActivity extends BaseActivity<SearchContract.IPresenter> implements SearchContract.IView {
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

    private List<BookBean> mHotList = new ArrayList<>();


    @BindView(R.id.rv_history)
    RecyclerView rv_history;

    List<SearchHistoryBean> mShList = new ArrayList<>();
    CommonAdapter mShAdapter;

    @BindView(R.id.ll_history)
    LinearLayout ll_history;


    @BindView(R.id.rv_smart)
    RecyclerView rv_smart;

    List<BookBean> mSmartList = new ArrayList<>();
    CommonAdapter mSmartAdapter;

    @Override
    protected void initView() {
        super.initView();
        rv_history.setLayoutManager(new LinearLayoutManager(context));
        mShAdapter = new CommonAdapter<SearchHistoryBean>(context, R.layout.layout_search_history, mShList) {
            @Override
            protected void convert(ViewHolder holder, SearchHistoryBean sh, int position) {
                TextView tv_text = holder.getView(R.id.tv_text);
                ImageView img_fork = holder.getView(R.id.img_fork);
                tv_text.setText("" + sh.getWord());
                final long id = sh.getId();
                img_fork.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.delHistory(id);
                    }
                });
            }
        };
        rv_history.setAdapter(mShAdapter);

        rv_smart.setLayoutManager(new LinearLayoutManager(context));
        mSmartAdapter = new CommonAdapter<BookBean>(context, R.layout.layout_search_smart, mSmartList) {

            @Override
            protected void convert(ViewHolder holder, BookBean book, int position) {
                TextView tv_num = holder.getView(R.id.tv_num);
                TextView tv_text = holder.getView(R.id.tv_title);
                TextView tv_chapter = holder.getView(R.id.tv_chapter);

                tv_num.setText(position + 1 + "");
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
        rv_smart.setAdapter(mSmartAdapter);

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
        et_text.addTextChangedListener(new TextWatcher() {
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
                    rv_smart.setVisibility(View.VISIBLE);
                } else {
                    rv_smart.setVisibility(View.GONE);
                    mSmartList.clear();
                    mSmartAdapter.notifyDataSetChanged();
                }

            }
        });
        tl_tag.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int i, String s, int i1) {
                String title = mHotList.get(i).getTitle();
                String href = mHotList.get(i).getHref();
                mPresenter.goDetails(href, title);
            }
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
        et_text.setText("");
    }

    @Override
    public void showHot(List list) {
        List<BookBean> hots = list;
        if (hots != null) {
            mHotList.clear();
            mHotList.addAll(hots);
            tl_tag.cleanTags();
            for (BookBean book : mHotList) {
                tl_tag.addTag(book.getTitle());
            }
        }

    }


    @NonNull
    private String KEY = "";

    @Override
    public void showSmart(String key, List list) {
        if (key != null) {
            KEY = key;
        }
        List<BookBean> smarts = list;
        if (smarts != null) {
            mSmartList.clear();
            mSmartList.addAll(smarts);
            mSmartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showHistory(List list) {
        if (list == null || list.size() == 0) {
            ll_history.setVisibility(View.GONE);
            return;
        }
        List<SearchHistoryBean> shList = list;
        mShList.addAll(shList);
        notifyHistoryChanged();


    }

    private void notifyHistoryChanged() {
        if (mShList == null || mShList.size() == 0) {
            ll_history.setVisibility(View.GONE);
        } else {
            ll_history.setVisibility(View.VISIBLE);
        }
        Collections.sort(mShList, new Comparator<SearchHistoryBean>() {
            @Override
            public int compare(SearchHistoryBean o1, SearchHistoryBean o2) {
                if (o1.getTime() < o2.getTime()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
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
