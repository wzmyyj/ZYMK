package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.tools.I;
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
        mModel.getHotSearch(new Observer<List<BookBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<BookBean> list) {
                mView.showHot(list);
//                mView.showToast("加载成功");
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
        mModel.getSmartSearch(key, new Observer<List<BookBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<BookBean> list) {
                mView.showSmart(list);
//                mView.showToast("加载成功");
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


    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

}
