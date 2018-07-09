package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.tools.L;

/**
 * Created by wzm on 2018/07/06. email: 2209011667@qq.com
 */

public abstract class WebPanel extends InitPanel {
    public WebPanel(Context context) {
        super(context);
    }

    private WebView web;
    private ProgressBar pb;
    private String url;
    private TextView tv;

    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_web, null);
        web = view.findViewById(R.id.wv_web);
        pb = view.findViewById(R.id.pb_web);
    }

    @Override
    protected void initData() {
        url = getUrl();
        initWeb();
        loadWeb(url);
    }

    @Override
    protected void initListener() {

    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    protected abstract String getUrl();

    public void loadUrl(String url) {
        this.url = url;
        loadUrl(url);
    }

    private boolean canGoBack() {
        if (web == null) return false;
        return web.canGoBack();
    }

    public void goBack() {
        if (web.canGoBack()) {
            web.goBack();
        }
    }

    public void goForward() {
        if (web.canGoForward()) {
            web.goForward();
        }
    }

    public void reload() {
        web.reload();
    }

    public void reset() {
        loadWeb(url);
    }


    public void initWeb() {
//        web.clearCache(true);
//        web.clearHistory();
        web.requestFocus();
        WebSettings webSettings = web.getSettings();

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setUseWideViewPort(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    protected void loadWeb(String url) {
        if (url == null) {
            return;
        }
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                L.d(url);
                return true;
            }
        });
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 70) {
                    pb.setProgress(newProgress);
                } else {
                    pb.setProgress(100);
                    if (tv != null) {
                        tv.setText(web.getTitle());
                    }
                }

            }

        });
    }


    public void clearCookie() {
        CookieSyncManager.createInstance(activity);
        CookieManager cookiemanager = CookieManager.getInstance();
        cookiemanager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
        web.setWebChromeClient(null);
        web.setWebViewClient(null);
        web.getSettings().setJavaScriptEnabled(false);
        web.clearCache(true);

    }


    protected boolean isClearCookie;

    @Override
    public void onDestroy() {
        if (isClearCookie)
            clearCookie();
        super.onDestroy();
    }
}
