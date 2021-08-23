package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.myapplication.router.RouterPath;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.ToastUtils;

@Route(path = RouterPath.ACTIVITY_URL_REGISTER)
public class Register extends BaseActivity {
    private Button check;
    private Button getTextView;
    private EditText phoneView;
    private boolean checked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        check = findViewById(R.id.check);
        getTextView = findViewById(R.id.get_text);
        phoneView = findViewById(R.id.phone);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if(checked == false) {
                    check.setBackground(getResources().getDrawable(R.drawable.ic_baseline_check_box_24));
                    checked = true;
                } else {
                    check.setBackground(getResources().getDrawable(R.drawable.ic_baseline_uncheck_box_24));
                    checked = false;
                }
            }
        });
//        Login a = new Login();
//        final Login.MyCountDownTimer myCountDownTimer = a.new MyCountDownTimer(60000,1000);
        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000,1000);
        getTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommonUtils.isPhone(phoneView.getText().toString())){
                    myCountDownTimer.start();
                    ToastUtils.showToast(Register.this,"请勿重复点击，已发送验证码");
                } else {
                    ToastUtils.showToast(Register.this,"手机号码格式错误");
                }
            }
        });
    }
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            getTextView.setClickable(false);
            getTextView.setText(l / 1000 + "秒");
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            getTextView.setText("重新获取");
            getTextView.setClickable(true);
        }
    }
}