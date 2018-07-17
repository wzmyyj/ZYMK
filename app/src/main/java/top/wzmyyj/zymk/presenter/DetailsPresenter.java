package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.model.box.DetailsBox;
import top.wzmyyj.zymk.model.net.DetailsModel;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IDetailsView;


/**
 * Created by yyj on 2018/07/14. email: 2209011667@qq.com
 */

public class DetailsPresenter extends BasePresenter<IDetailsView> {
    private DetailsModel mModel;

    public DetailsPresenter(Activity activity, IDetailsView iv) {
        super(activity, iv);
        mModel = new DetailsModel();
    }


    public void loadData() {
        String url = mActivity.getIntent().getStringExtra("href");
        mModel.getMoreData(url, new Observer<DetailsBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DetailsBox box) {
                mView.setBook(box.getBook());
                mView.setXi(box.getXi());
                mView.setMu(box.getMu());
                mView.setZi(box.getZi());
                mView.setBookList(box.getXgBookList());

//                mView.showToast("加载成功");
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
