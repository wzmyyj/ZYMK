package top.wzmyyj.zymk.presenter.p;

import android.app.Activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.HomeBean;
import top.wzmyyj.zymk.model.net.HomeModel;
import top.wzmyyj.zymk.presenter.ip.IRecyclePresent;
import top.wzmyyj.zymk.presenter.p.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_1View;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class HomePresenter extends BasePresenter<IF_1View> implements IRecyclePresent {
    private HomeModel mModel;

    public HomePresenter(Activity activity, IF_1View iv) {
        super(activity, iv);
        mModel = new HomeModel();
    }

    @Override
    public void loadData() {
        mModel.getHomeData(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBean homeBean) {
                mView.update(homeBean.getBoList(),homeBean.getItemList());
                mView.showToast("加载成功");
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
