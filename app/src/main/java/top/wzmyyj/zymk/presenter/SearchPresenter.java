package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.net.Urls;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.ISearchView;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchPresenter extends BasePresenter<ISearchView> {
    public SearchPresenter(Activity activity, ISearchView iv) {
        super(activity, iv);
    }

    public void search(String s) {
        I.toTyActivity(mActivity, Urls.ZYMK_All, s);
    }

    public void getHotTags() {

    }

    public void getHistory() {

    }

    public void addHistory(String s) {

    }

    public void delHistory(String s) {

    }

    public void delAllHistory() {

    }

    public void smart(String s) {

    }


    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

}
