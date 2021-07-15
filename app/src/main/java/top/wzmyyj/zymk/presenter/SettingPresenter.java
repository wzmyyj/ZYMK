package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.wzm_sdk.tools.P;
import top.wzmyyj.wzm_sdk.utils.PackageUtil;
import top.wzmyyj.zymk.app.data.Config;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.helper.GlideCacheHelper;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.base.presenter.BasePresenter;
import top.wzmyyj.zymk.contract.SettingContract;

/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */
public class SettingPresenter extends BasePresenter<SettingContract.IView> implements SettingContract.IPresenter {

    private int count = 0;

    public SettingPresenter(Activity activity, SettingContract.IView iv) {
        super(activity, iv);
    }

    @Override
    public void goAboutMe() {
        IntentHelper.toBrowser(mActivity, Urls.YYJ_About);
    }

    @Override
    public void goGitHubWeb() {
        IntentHelper.toBrowser(mActivity, Urls.YYJ_GitHub);
    }

    @Override
    public void goFeedback() {
        IntentHelper.toQQChat(mActivity, Config.QQ_Number);
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
        String s = GlideCacheHelper.getInstance().getCacheSize(mActivity);
        mView.setCache(s);
    }

    @Override
    public void clearCache() {
        GlideCacheHelper.getInstance().clearImageMemoryCache(mActivity);
        Observable.create((ObservableOnSubscribe<String>) observableEmitter -> {
            GlideCacheHelper.getInstance().clearImageDiskCache(mActivity);
            GlideCacheHelper.getInstance().clearFolderFile(mActivity);
            observableEmitter.onNext("");
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        getCacheSize();
                        mView.showToast("清除成功！");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showToast("Error:" + e.getMessage());
                    }
                });
    }

    @Override
    public void setting() {
        count++;
        if (count == 10) {
            Config.canReadSf = true;
            P.create(mActivity).putBoolean("canReadSf", true).commit();
        }
    }
}
