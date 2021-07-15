package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.base.presenter.BasePresenter;
import top.wzmyyj.zymk.contract.ResultContract;
import top.wzmyyj.zymk.model.net.TyModel;
import top.wzmyyj.zymk.model.net.box.TyBox;

/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */
public class ResultPresenter extends BasePresenter<ResultContract.IView> implements ResultContract.IPresenter {

    private final TyModel mModel;
    private final String key, href;

    public ResultPresenter(Activity activity, ResultContract.IView iv) {
        super(activity, iv);
        mModel = new TyModel();
        href = mActivity.getIntent().getStringExtra("href");
        key = mActivity.getIntent().getStringExtra("key");
    }

    @Override
    public void loadData(String url, final boolean isFirst) {
        if (!TextUtils.isEmpty(key)) {
            url = url + "?key=" + key;
        }
        mModel.getTyData(url, new BaseObserver<TyBox>() {
            @Override
            public void onNext(@NonNull TyBox box) {
                mView.setTitle(box.getTitle());
                mView.showData(isFirst, box.getBooks(), box.getBase(), box.getNext());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showLoadFail(e.getMessage());
                mView.showToast("Error:" + e.getMessage());
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
            IntentHelper.toDetailsActivity(mActivity, href);
        } else {
            IntentHelper.toBrowser(mActivity, href);
        }
    }
}
