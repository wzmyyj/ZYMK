package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.DownloadBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/08/17. email: 2209011667@qq.com
 */

public interface IDownloadView extends IBaseView {
    void loadDownload(List<DownloadBean> list);

    void deleteDownload(boolean is);
}
