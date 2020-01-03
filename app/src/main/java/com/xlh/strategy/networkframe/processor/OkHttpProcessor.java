package com.xlh.strategy.networkframe.processor;

import android.os.Handler;
import android.util.Log;

import com.xlh.strategy.networkframe.http.IHttpCallBack;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor {


    OkHttpClient mOkHttpClient;
    Handler mHandler;

    public OkHttpProcessor() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, final IHttpCallBack callBack) {
        RequestBody requestBody = appendParams(params);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String str = response.body().string();
                    Log.e("Test", "OkHttpProcessor--->" + str);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onHttpSuccess(str);
                        }
                    });
                }
            }
        });
    }

    private RequestBody appendParams(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }

}
