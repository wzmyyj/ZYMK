package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.net.box.RankBox;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.Urls;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IRankView;


/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public class RankPresenter extends BasePresenter<IRankView> {
    private MainModel mModel;

    public RankPresenter(Activity activity, IRankView iv) {
        super(activity, iv);
        mModel = new MainModel();
    }


    public void addEmptyData(List<BookBean> data) {
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
        data.add(new BookBean());
    }

    public void loadData() {
        mModel.getRankData(new Observer<RankBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RankBox box) {
                mView.update(0, box.getBookList1(), box.getBookList2(),box.getBookList3());
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

    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }
}
