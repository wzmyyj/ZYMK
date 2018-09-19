package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import android.text.TextUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.ResultContract;
import top.wzmyyj.zymk.model.net.TyModel;
import top.wzmyyj.zymk.model.net.box.TyBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;


/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public class ResultPresenter extends BasePresenter<ResultContract.IView> implements ResultContract.IPresenter {
    private TyModel mModel;

    public ResultPresenter(Activity activity, ResultContract.IView iv) {
        super(activity, iv);
        mModel = new TyModel();
        href = mActivity.getIntent().getStringExtra("href");
        key = mActivity.getIntent().getStringExtra("key");
    }

    private String key, href;



    @Override
    public void loadData(String url, final boolean isFirst) {
        if (!TextUtils.isEmpty(key)) {
            url = url + "?key=" + key;
        }
        mModel.getTyData(url, new Observer<TyBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TyBox box) {
                mView.setTitle(box.getTitle());
                mView.showData(isFirst, box.getBooks(), box.getBase(), box.getNext());
            }

            @Override
            public void onError(Throwable e) {
                mView.showLoadFail(e.getMessage());
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 下滑加载调用这个
    @Override
    public void loadData(String url) {
        loadData(url, false);
    }

    // 第一次加载调用这个
    @Override
    public void loadData() {
        loadData(href, true);
    }


    @Override
    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }
}
