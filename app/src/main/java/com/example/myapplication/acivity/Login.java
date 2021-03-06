package com.example.myapplication.acivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.router.RouterPath;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.ToastUtils;
import com.example.myapplication.utils.http.HttpClient;

import okhttp3.FormBody;

@Route(path = RouterPath.ACTIVITY_URL_LOGIN)
public class Login extends BaseActivity {
    private EditText textView;
    private EditText phoneView;
    private EditText passwordView;
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
        passwordView = findViewById(R.id.get_password);
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

        findViewById(R.id.Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterPath.ACTIVITY_URL_REGISTER).navigation();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterPath.ACTIVITY_URL_MAIN).navigation();
            }
        });

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
                    ToastUtils.showToast(Login.this,"???????????????????????????????????????");
                } else {
                    ToastUtils.showToast(Login.this,"????????????????????????");
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
            login.setText("??????");
        }
        if (textView.getHint().equals("??????????????????")) {
            textView.setHint("???????????????");
            textView.setText("");
            changeText.setText("???????????????");
            getTextView.setVisibility(View.GONE);
            isVisibility.setVisibility(View.VISIBLE);
            textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            textView.setHint("??????????????????");
            textView.setText("");
            changeText.setText("??????????????????");
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
        login.setText("????????????");
        textView.setHint("??????????????????");
        textView.setText("");
        changeText.setText("??????????????????");
        getTextView.setVisibility(View.VISIBLE);
        isVisibility.setVisibility(View.GONE);
        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public void Login(View view) {
        FormBody formBody = new FormBody.Builder().add("phone",phoneView.getText().toString()).add("password",textView.getText().toString()).build();
        HttpClient.getHttpClient().postHttp("/app/user/login",formBody,Login.this);
    }


    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            getTextView.setClickable(false);
            getTextView.setText(l / 1000 + "???");
        }

        //?????????????????????
        @Override
        public void onFinish() {
            getTextView.setText("????????????");
            getTextView.setClickable(true);
        }
    }

}

