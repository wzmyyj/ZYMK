package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;

import java.util.List;

import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.base.presenter.BasePresenter;
import top.wzmyyj.zymk.contract.SearchContract;
import top.wzmyyj.zymk.model.db.SearchHistoryModel;
import top.wzmyyj.zymk.model.net.SearchModel;
import top.wzmyyj.zymk.model.net.box.SearchBox;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */
public class SearchPresenter extends BasePresenter<SearchContract.IView> implements SearchContract.IPresenter {

    private final SearchModel mModel;
    private final SearchHistoryModel mModel2;

    public SearchPresenter(Activity activity, SearchContract.IView iv) {
        super(activity, iv);
        mModel = new SearchModel();
        mModel2 = new SearchHistoryModel(activity);
    }

    @Override
    public void search(String s) {
        addHistory(s);
        IntentHelper.toTyActivity(mActivity, Urls.ZYMK_All, s);
    }

    @Override
    public void getHotTags() {
        mModel.getHotSearch(new BaseObserver<SearchBox>() {
            @Override
            public void onNext(@NonNull SearchBox box) {
                int status = box.getStatus();
                if (status == 0) {
                    mView.showHot(box.getBookList());
                } else {
                    mView.showToast(box.getMsg());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void smart(String key) {
        mModel.getSmartSearch(key, new BaseObserver<SearchBox>() {
            @Override
            public void onNext(@NonNull SearchBox box) {
                int status = box.getStatus();
                if (status == 0) {
                    mView.showSmart(box.getKey(), box.getBookList());
                } else {
                    mView.showToast(box.getMsg());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                L.e(e.getMessage());
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void getHistory() {
        mModel2.loadAll(new BaseObserver<List<SearchHistoryBean>>() {
            @Override
            public void onNext(@NonNull List<SearchHistoryBean> list) {
                mView.showHistory(list);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void addHistory(String word) {
        mModel2.insert(word, new BaseObserver<SearchHistoryBean>() {
            @Override
            public void onNext(@NonNull SearchHistoryBean bean) {
                mView.addHistory(bean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void delHistory(long id) {
        mModel2.delete(id, new BaseObserver<Long>() {
            @Override
            public void onNext(@NonNull Long l) {
                mView.removeHistory(l);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void delAllHistory() {
        mModel2.deleteAll(new BaseObserver<Long>() {
            @Override
            public void onNext(@NonNull Long l) {
                mView.removeAllHistory();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void goDetails(String href, String title) {
        if (href.contains(Urls.ZYMK_Base)) {
            IntentHelper.toDetailsActivity(mActivity, href);
        } else {
            IntentHelper.toBrowser(mActivity, href);
        }
    }
}
