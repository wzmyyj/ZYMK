package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.base.presenter.BasePresenter;
import top.wzmyyj.zymk.contract.RankContract;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.box.RankBox;

/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */
public class RankPresenter extends BasePresenter<RankContract.IView> implements RankContract.IPresenter {

    private final MainModel mModel;

    public RankPresenter(Activity activity, RankContract.IView iv) {
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
        mModel.getRankData(new BaseObserver<RankBox>() {
            @Override
            public void onNext(@NonNull RankBox box) {
                mView.showData(box.getBookList1(), box.getBookList2(), box.getBookList3());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
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
