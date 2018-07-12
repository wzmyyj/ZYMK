package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.box.HomeBox;
import top.wzmyyj.zymk.model.net.HomeModel;
import top.wzmyyj.zymk.model.net.Urls;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_1View;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class HomePresenter extends BasePresenter<IF_1View> {
    private HomeModel mModel;

    public HomePresenter(Activity activity, IF_1View iv) {
        super(activity, iv);
        mModel = new HomeModel();
    }


    public void addEmptyData(List<ItemBean> data) {
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
    }

    public void loadData() {
        mModel.getData(new Observer<HomeBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBox homeBean) {
                mView.update(0, homeBean.getBoList(), homeBean.getItemList());
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

    public void goMore(String href) {
        I.toMoreActivity(mActivity, href);
    }

    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_HomePage)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }
}
