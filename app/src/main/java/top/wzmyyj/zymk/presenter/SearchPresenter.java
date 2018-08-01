package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.box.SearchBox;
import top.wzmyyj.zymk.model.net.SearchModel;
import top.wzmyyj.zymk.model.net.Urls;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.ISearchView;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchPresenter extends BasePresenter<ISearchView> {
    private SearchModel mModel;

    public SearchPresenter(Activity activity, ISearchView iv) {
        super(activity, iv);
        mModel = new SearchModel();
    }

    public void search(String s) {
        I.toTyActivity(mActivity, Urls.ZYMK_All, s);
    }

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

    public void getHistory() {

    }

    public void addHistory(String s) {

    }

    public void delHistory(String s) {

    }

    public void delAllHistory() {

    }

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


    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

}
