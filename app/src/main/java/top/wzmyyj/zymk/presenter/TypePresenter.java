package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.net.box.TypeBox;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_2View;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class TypePresenter extends BasePresenter<IF_2View> {
    private MainModel mModel;

    public TypePresenter(Activity activity, IF_2View iv) {
        super(activity, iv);
        mModel = new MainModel();
    }

    public void loadData() {
        mModel.getTypeData(new Observer<TypeBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TypeBox box) {
                mView.update(0,
                        box.getTypeList1(),
                        box.getTypeList2(),
                        box.getTypeList3(),
                        box.getTypeList4());
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

    public void goTy(String href) {
        I.toTyActivity(mActivity, href);
    }
    public void goSearch() {
        I.toSearchActivity(mActivity);
    }
}
