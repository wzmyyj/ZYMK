package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import top.wzmyyj.wzm_sdk.R;

/**
 * Created by wzm on 2018/5/9 0009.
 */

public class WebPanel extends InitPanel {
    public WebPanel(Context context) {
        super(context);
        this.title = "Web";
    }

    public WebPanel(Context context, String url) {
        super(context);
        this.title = "Web";
        this.url = url;
    }


    private RelativeLayout rl_back;
    private RelativeLayout rl_forward;
    private RelativeLayout rl_refresh;
    private RelativeLayout rl_reset;
    private WebView web;
    private ProgressBar pb1;
    private String url;

    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_web, null);
        rl_back = view.findViewById(R.id.rl_web_back);
        rl_forward = view.findViewById(R.id.rl_web_forward);
        rl_refresh = view.findViewById(R.id.rl_web_refresh);
        rl_reset = view.findViewById(R.id.rl_web_reset);
        web = view.findViewById(R.id.wv_web);
        pb1 = view.findViewById(R.id.pb_web);
    }

    @Override
    protected void initData() {
        initWeb();
    }

    @Override
    protected void initListener() {
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        rl_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goForward();
            }
        });
        rl_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
        rl_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private boolean canGoBack() {
        if (web == null) return false;
        return web.canGoBack();
    }

    private void goBack() {
        if (web.canGoBack()) {
            web.goBack();
        }
    }

    private void goForward() {
        if (web.canGoForward()) {
            web.goForward();
        }
    }

    private void reload() {
        web.reload();
    }

    private void reset() {
        initWeb();
    }


    private void initWeb() {
        if (url == null) {
            return;
        }
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });
        WebSettings set = web.getSettings();
        set.setJavaScriptEnabled(true);
        set.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress < 70) {
                    pb1.setProgress(newProgress);
                } else {
                    pb1.setProgress(100);
                }

            }

        });
    }

    private void clearCookie() {
        CookieSyncManager.createInstance(activity);
        CookieManager cookiemanager = CookieManager.getInstance();
        cookiemanager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
        web.setWebChromeClient(null);
        web.setWebViewClient(null);
        web.getSettings().setJavaScriptEnabled(false);
        web.clearCache(true);

    }

}
