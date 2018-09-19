package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.MoreContract;
import top.wzmyyj.zymk.model.net.MoreModel;
import top.wzmyyj.zymk.model.net.box.MoreBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;


/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public class MorePresenter extends BasePresenter<MoreContract.IView> implements MoreContract.IPresenter{
    private MoreModel mModel;

    public MorePresenter(Activity activity, MoreContract.IView iv) {
        super(activity, iv);
        mModel = new MoreModel();
    }


    @Override
    public void addEmptyData(List<BookBean> data) {
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
    }

    @Override
    public void loadData() {
        String url = mActivity.getIntent().getStringExtra("href");
        mModel.getMoreData(url, new Observer<MoreBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MoreBox box) {
                mView.setTitle(box.getTitle());
                mView.showData(box.getContent(), box.getFigure(), box.getBookList());
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
    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }
}
