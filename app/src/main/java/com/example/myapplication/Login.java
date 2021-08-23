package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.ToastUtils;

public class Login extends AppCompatActivity {
    private EditText textView;
    private EditText phoneView;
    private Button clearView;
    private Button getTextView;
    private Button isVisibility;
    private Button isVisibility2;
    private Button isVisibility3;
    private TextView changeText;
    private TextView setPassword;
    private LinearLayout forgetPassword1;
    private View forgetPassword2;
    private LinearLayout forgetPassword3;
    private View forgetPassword4;
    private Button login;
    private boolean isEye1= true;
    private boolean isEye2= true;
    private boolean isEye3= true;

    public Login() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.text);
        phoneView = findViewById(R.id.phone);
        clearView = findViewById(R.id.clear_button);
        getTextView = findViewById(R.id.get_text);
        isVisibility = findViewById(R.id.is_visibility);
        isVisibility2 = findViewById(R.id.is_visibility2);
        isVisibility3 = findViewById(R.id.is_visibility3);
        changeText = findViewById(R.id.text_switch);
        setPassword = findViewById(R.id.set_password);
        forgetPassword1 = findViewById(R.id.forget_password1);
        forgetPassword2 = findViewById(R.id.forget_password2);
        forgetPassword3 = findViewById(R.id.forget_password3);
        forgetPassword4 = findViewById(R.id.forget_password4);
        login = findViewById(R.id.login);

        phoneView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (phoneView.getText().toString() != null
                        && !phoneView.getText().toString().equals("")) {
                    clearView.setVisibility(View.VISIBLE);
                } else {
                    clearView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);

        getTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommonUtils.isPhone(phoneView.getText().toString())){
                    myCountDownTimer.start();
                } else {
                    ToastUtils.showToast(Login.this,"手机号码格式错误");
                }
            }
        });
    }

    public void onSwitch(View view) {
        if(setPassword.getVisibility() == View.GONE){
            setPassword.setVisibility(View.VISIBLE);
            forgetPassword1.setVisibility(View.GONE);
            forgetPassword2.setVisibility(View.GONE);
            forgetPassword3.setVisibility(View.GONE);
            forgetPassword4.setVisibility(View.GONE);
            login.setText("登录");
        }
        if (textView.getHint().equals("请输入验证码")) {
            textView.setHint("请输入密码");
            textView.setText("");
            changeText.setText("验证码登录");
            getTextView.setVisibility(View.GONE);
            isVisibility.setVisibility(View.VISIBLE);
            textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            textView.setHint("请输入验证码");
            textView.setText("");
            changeText.setText("帐号密码登录");
            getTextView.setVisibility(View.VISIBLE);
            isVisibility.setVisibility(View.GONE);
            textView.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    public void clear(View view) {
        phoneView.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setIsVisibility1(View view){
        isVisibility(view,isEye1,isVisibility);
        isEye1 = !isEye1;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setIsVisibility2(View view){
        isVisibility(view,isEye2,isVisibility2);
        isEye2 = !isEye2;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setIsVisibility3(View view){
        isVisibility(view,isEye3,isVisibility3);
        isEye3 = !isEye3;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void isVisibility(View view,boolean isEye,Button isVisibility) {
        if (isEye) {
            isVisibility.setBackground(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
            textView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            isVisibility.setBackground(getResources().getDrawable(R.drawable.ic_baseline_visibility_24));
            textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void changePassword(View view){
        setPassword.setVisibility(View.GONE);
        forgetPassword1.setVisibility(View.VISIBLE);
        forgetPassword2.setVisibility(View.VISIBLE);
        forgetPassword3.setVisibility(View.VISIBLE);
        forgetPassword4.setVisibility(View.VISIBLE);
        login.setText("修改密码");
        textView.setHint("请输入验证码");
        textView.setText("");
        changeText.setText("帐号密码登录");
        getTextView.setVisibility(View.VISIBLE);
        isVisibility.setVisibility(View.GONE);
        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
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

