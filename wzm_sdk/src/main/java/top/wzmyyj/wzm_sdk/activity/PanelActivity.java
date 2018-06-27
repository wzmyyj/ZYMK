package top.wzmyyj.wzm_sdk.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import top.wzmyyj.wzm_sdk.panel.Panel;

/**
 * Created by wzm on 2018/5/4 0004.
 */

public abstract class PanelActivity extends AppCompatActivity {

    protected Panel mPanel;

    @NonNull
    protected abstract Panel getPanel();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mPanel = getPanel();
        mPanel.onCreate(savedInstanceState);
        setContentView(mPanel.getView());

    }

    @Override
    public void onResume() {
        super.onResume();
        mPanel.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPanel.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPanel.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPanel.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPanel.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPanel.onDestroy();
        mPanel = null;
    }
}
