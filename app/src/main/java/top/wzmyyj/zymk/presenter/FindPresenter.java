package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.DownloadBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.contract.FindContract;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.model.db.HistoryModel;
import top.wzmyyj.zymk.base.presenter.BasePresenter;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */
public class FindPresenter extends BasePresenter<FindContract.IView> implements FindContract.IPresenter {

    private final FavorModel favorModel;
    private final HistoryModel historyModel;
    private final List<FavorBean> favorList = new ArrayList<>();

    public FindPresenter(Activity activity, FindContract.IView iv) {
        super(activity, iv);
        favorModel = new FavorModel(activity);
        historyModel = new HistoryModel(activity);
    }

    @Override
    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            IntentHelper.toDetailsActivity(mActivity, href);
        } else {
            IntentHelper.toBrowser(mActivity, href);
        }
    }

    @Override
    public void goComic(int comic_id, long chapter_id) {
        IntentHelper.toComicActivity(mActivity, comic_id, chapter_id);
    }

    @Override
    public void loadNetFavor() {
        favorModel.updateAll(new BaseObserver<FavorBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                favorList.clear();
            }

            @Override
            public void onNext(@NonNull FavorBean favorBean) {
                favorList.add(favorBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
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
        favorModel.loadAll(new BaseObserver<List<FavorBean>>() {
            @Override
            public void onNext(@NonNull List<FavorBean> favorBeans) {
                mView.showFavor(favorBeans);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void deleteSomeFavor(Long[] ids) {
        favorModel.delete(ids, new BaseObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean is) {
                mView.removeFavor(is);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void loadHistory() {
        historyModel.loadAll(new BaseObserver<List<HistoryBean>>() {
            @Override
            public void onNext(@NonNull List<HistoryBean> historyBeans) {
                mView.showHistory(historyBeans);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void deleteSomeHistory(Long[] ids) {
        historyModel.delete(ids, new BaseObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean is) {
                mView.removeHistory(is);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
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
