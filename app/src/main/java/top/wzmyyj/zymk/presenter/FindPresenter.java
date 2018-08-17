package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_3View;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class FindPresenter extends BasePresenter<IF_3View> {

    private FavorModel favorModel;

    public FindPresenter(Activity activity, IF_3View iv) {
        super(activity, iv);
        favorModel = new FavorModel(activity);
    }

    public void loadData() {
    }

    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

    public void loadFavor() {
        favorModel.loadAll(new Observer<List<FavorBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<FavorBean> favorBeans) {
                mView.loadFavor(favorBeans);
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

    public void deleteSomeFavor(Long[] ids) {
        favorModel.deleteSome(ids, new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean is) {
                mView.deleteFavor(is);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
