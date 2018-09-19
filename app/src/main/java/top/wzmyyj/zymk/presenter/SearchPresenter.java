package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.SearchContract;
import top.wzmyyj.zymk.model.db.SearchHistoryModel;
import top.wzmyyj.zymk.model.net.SearchModel;
import top.wzmyyj.zymk.model.net.box.SearchBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchPresenter extends BasePresenter<SearchContract.IView> implements SearchContract.IPresenter {
    private SearchModel mModel;
    private SearchHistoryModel mModel2;

    public SearchPresenter(Activity activity, SearchContract.IView iv) {
        super(activity, iv);
        mModel = new SearchModel();
        mModel2 = new SearchHistoryModel(activity);
    }

    @Override
    public void search(String s) {
        addHistory(s);
        I.toTyActivity(mActivity, Urls.ZYMK_All, s);
    }

    @Override
    public void getHotTags() {
        mModel.getHotSearch(new Observer<SearchBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchBox box) {
                int status = box.getStatus();
                if (status == 0) {
                    mView.showHot(box.getBookList());
                } else {
                    mView.showToast(box.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void smart(String key) {
        mModel.getSmartSearch(key, new Observer<SearchBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchBox box) {
                int status = box.getStatus();
                if (status == 0) {
                    mView.showSmart(box.getKey(), box.getBookList());
                } else {
                    mView.showToast(box.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                L.e(e.getMessage());
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getHistory() {
        mModel2.loadAll(new Observer<List<SearchHistoryBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<SearchHistoryBean> list) {
                mView.showHistory(list);
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void addHistory(String word) {
        mModel2.insert(word, new Observer<SearchHistoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchHistoryBean bean) {
                mView.addHistory(bean);
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void delHistory(long id) {
        mModel2.delete(id, new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long l) {
                mView.removeHistory(l);
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void delAllHistory() {
        mModel2.deleteAll(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long l) {
                mView.removeAllHistory();
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void goDetails(String href, String title) {
        addHistory(title);
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

}
