package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.app.data.Keys;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.wzm_sdk.tools.P;
import top.wzmyyj.zymk.app.utils.GlideCacheUtil;
import top.wzmyyj.zymk.common.utils.PackageUtil;
import top.wzmyyj.zymk.contract.SettingContract;
import top.wzmyyj.zymk.presenter.base.BasePresenter;


/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */

public class SettingPresenter extends BasePresenter<SettingContract.IView> implements SettingContract.IPresenter {
    public SettingPresenter(Activity activity, SettingContract.IView iv) {
        super(activity, iv);
    }

    @Override
    public void goAboutMe() {
        I.toBrowser(mActivity, Urls.YYJ_About);
    }

    @Override
    public void goGitHubWeb() {
        I.toBrowser(mActivity, Urls.YYJ_GitHub);
    }

    @Override
    public void goFeedback() {
        I.toQQChat(mActivity, Keys.QQ_Number);
    }

    @Override
    public void changeCue() {
        P p = P.create(mActivity);
        boolean is = p.getBoolean("isCue", true);
        p.putBoolean("isCue", !is).commit();
        mView.setCue(!is);
    }

    @Override
    public void getCue() {
        boolean is = P.create(mActivity).getBoolean("isCue", true);
        mView.setCue(is);
    }

    @Override
    public void getVersion() {
        String version = PackageUtil.getVersionName(mActivity);
        mView.setVersion(version);
    }

    @Override
    public void loadNewApp() {
        mView.showToast("已经是最新版本！");
    }

    @Override
    public void getCacheSize() {
        String s = GlideCacheUtil.getInstance().getCacheSize(mActivity);
        mView.setCache(s);
    }

    @Override
    public void clearCache() {
        GlideCacheUtil.getInstance().clearImageMemoryCache(mActivity);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                GlideCacheUtil.getInstance().clearImageDiskCache(mActivity);
                GlideCacheUtil.getInstance().clearFolderFile(mActivity);
                observableEmitter.onNext("");
            }
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        getCacheSize();
                        mView.showToast("清除成功！");
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

}
