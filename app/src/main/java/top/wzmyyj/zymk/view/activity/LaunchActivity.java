package top.wzmyyj.zymk.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent();
                i.setClass(LaunchActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        }, 1000);
    }
}
