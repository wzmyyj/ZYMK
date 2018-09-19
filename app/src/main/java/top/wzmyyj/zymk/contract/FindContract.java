package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.DownloadBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface FindContract {

    interface IView extends IBaseView {

        void showFavor(List<FavorBean> list);

        void removeFavor(boolean is);

        void showHistory(List<HistoryBean> list);

        void removeHistory(boolean is);


        void showDownload(List<DownloadBean> list);

        void removeDownload(boolean is);

    }

    interface IPresenter extends IBasePresenter {

        void goDetails(String href);

        void goComic(int comic_id, long chapter_id);

        void loadFavor();

        void loadNetFavor();

        void deleteSomeFavor(Long[] ids);


        void loadHistory();

        void deleteSomeHistory(Long[] ids);

        void loadDownload();

        void deleteSomeDownload(Long[] ids);
    }


}
