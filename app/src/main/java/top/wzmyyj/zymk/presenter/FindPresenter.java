package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.DownloadBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.FindContract;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.model.db.HistoryModel;
import top.wzmyyj.zymk.presenter.base.BasePresenter;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class FindPresenter extends BasePresenter<FindContract.IView> implements FindContract.IPresenter {

    private FavorModel favorModel;
    private HistoryModel historyModel;

    public FindPresenter(Activity activity, FindContract.IView iv) {
        super(activity, iv);
        favorModel = new FavorModel(activity);
        historyModel = new HistoryModel(activity);
    }

    @Override
    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

    @Override
    public void goComic(int comic_id, long chapter_id) {
        I.toComicActivity(mActivity, comic_id, chapter_id);
    }


    List<FavorBean> favorList = new ArrayList<>();


    @Override
    public void loadNetFavor() {
        favorModel.updateAll(new Observer<FavorBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                favorList.clear();
            }

            @Override
            public void onNext(FavorBean favorBean) {
                favorList.add(favorBean);
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                mView.showFavor(favorList);
            }
        });
    }


    @Override
    public void loadFavor() {
        favorModel.loadAll(new Observer<List<FavorBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<FavorBean> favorBeans) {
                mView.showFavor(favorBeans);
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
    public void deleteSomeFavor(Long[] ids) {
        favorModel.delete(ids, new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean is) {
                mView.removeFavor(is);
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
    public void loadHistory() {
        historyModel.loadAll(new Observer<List<HistoryBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<HistoryBean> historyBeans) {
                mView.showHistory(historyBeans);
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
    public void deleteSomeHistory(Long[] ids) {
        historyModel.delete(ids, new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean is) {
                mView.removeHistory(is);
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
    public void loadDownload() {
        List<DownloadBean> downloadList = new ArrayList<>();
        mView.showDownload(downloadList);
    }


    @Override
    public void deleteSomeDownload(Long[] ids) {
        mView.removeDownload(true);
    }
}
