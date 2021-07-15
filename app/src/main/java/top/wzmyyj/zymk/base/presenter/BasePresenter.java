package top.wzmyyj.zymk.base.presenter;

import android.app.Activity;
import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.base.contract.IBasePresenter;
import top.wzmyyj.zymk.base.contract.IBaseView;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter {

    protected V mView;
    protected Activity mActivity;

    public BasePresenter(Activity activity, V iv) {
        this.mActivity = activity;
        this.mView = iv;
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onReStart() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
        this.mActivity = null;
        this.mView = null;
    }

    @Override
    public void finish() {
        mActivity.finish();
    }

    @Override
    public void log(String msg) {
        L.d(msg);
    }

    protected interface BaseObserver<O> extends Observer<O> {

        @Override
        default void onSubscribe(@NonNull Disposable d) {
        }

        @Override
        default void onComplete() {
        }
    }
}
