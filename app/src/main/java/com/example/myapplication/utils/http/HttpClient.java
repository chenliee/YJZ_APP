package com.example.myapplication.utils.http;

//import org.apache.http.params.HttpParams;

import android.content.Context;
import android.os.Looper;
import android.text.format.Time;
import android.util.Log;

import com.example.myapplication.acivity.Login;
import com.example.myapplication.utils.ToastUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {
    static String url = "http://one.bbkt.cn";
    static String token = "";
    private static HttpClient httpClient;
    static Gson gson = new Gson();
    private HttpClient(){
        client = initOkHttpClient();
    }

    public final OkHttpClient client;
    private OkHttpClient initOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                .build();
        return okHttpClient;
    }

    public static HttpClient getHttpClient(){
        if(httpClient == null){
            httpClient = new HttpClient();
        }
        return httpClient;
    }

    public String getHttp(String uri,final Context context){
        Request request;
        final String[] responseBody = new String[1];
        if(token.length() != 0){
            request = new Request.Builder().url(this.url + uri).addHeader("auth",token).build();
        } else{
            request = new Request.Builder().url(this.url + uri).build();
        }
        Call call = getHttpClient().initOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("错误异常",e.toString());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    HttpResult result = gson.fromJson(response.body().string(),HttpResult.class);
                    if(result.getStatus() == false){
                        ToastUtils.showToast(context,result.getErrorMsg());
                    } else {
                        responseBody[0] = response.body().string();
                    }
                }
            }
        });
        return responseBody[0];
    }

    public String postHttp(String uri, FormBody body, final Context context){
        Request request;
        final String[] responseBody = new String[1];
        if(token.length()!= 0) {
            Log.e("抛异常","1");
            request = new Request.Builder().url(this.url + uri).addHeader("auth", token).post(body).build();
        } else {
            request = new Request.Builder().url(this.url + uri).post(body).build();
        }
        Call call = getHttpClient().initOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("错误异常",e.toString());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    HttpResult result = gson.fromJson(response.body().string(),HttpResult.class);
                    if(result.getStatus() == false){
                        Looper.prepare();
                        ToastUtils.showToast(context,result.getErrorMsg());
                        Looper.loop();
                    } else {
                        responseBody[0] = response.body().string();
                    }
                }
            }
        });
        return responseBody[0];
    }

}
