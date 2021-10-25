package com.example.myapplication.acivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.R;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.router.RouterPath;

@Route(path = RouterPath.ACTIVITY_URL_SPLASH)
public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_splash);
        Integer time = 1000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(RouterPath.ACTIVITY_URL_LOGIN).navigation();
                Splash.this.finish();
            }
        }, time);
    }
}
