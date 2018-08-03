package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import android.text.TextUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.net.box.TyBox;
import top.wzmyyj.zymk.model.net.TyModel;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.ITyView;


/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public class TyPresenter extends BasePresenter<ITyView> {
    private TyModel mModel;

    public TyPresenter(Activity activity, ITyView iv) {
        super(activity, iv);
        mModel = new TyModel();
        href = mActivity.getIntent().getStringExtra("href");
        key = mActivity.getIntent().getStringExtra("key");
    }

    private String key, href;


    public void addEmptyData(List<BookBean> data) {
//        data.add(new BookBean());
//        data.add(new BookBean());
//        data.add(new BookBean());
//        data.add(new BookBean());
    }


    public void loadData(String url, final int w) {
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
                mView.update(w, box.getBooks(), box.getBase(), box.getNext());
            }

            @Override
            public void onError(Throwable e) {
                mView.update(-1, e.getMessage());
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 下滑加载调用这个
    public void loadData(String url) {
        loadData(url, 1);
    }

    // 第一次加载调用这个
    public void loadData() {
        loadData(href, 0);
    }


    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }
}
