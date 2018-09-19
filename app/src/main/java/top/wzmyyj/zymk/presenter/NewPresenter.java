package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.NewContract;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.box.NewBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;


/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public class NewPresenter extends BasePresenter<NewContract.IView> implements NewContract.IPresenter {
    private MainModel mModel;

    public NewPresenter(Activity activity, NewContract.IView iv) {
        super(activity, iv);
        mModel = new MainModel();
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
        mModel.getNewData(new Observer<NewBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewBox box) {
                mView.showData(box.getBookList1(), box.getBookList2());
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
