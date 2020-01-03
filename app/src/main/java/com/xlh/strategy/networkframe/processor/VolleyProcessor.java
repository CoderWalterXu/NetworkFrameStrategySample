package com.xlh.strategy.networkframe.processor;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xlh.strategy.networkframe.http.IHttpCallBack;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class VolleyProcessor implements IHttpProcessor {

    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void post(String url, Map<String, Object> params, final IHttpCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("Test","VolleyProcessor--->"+s);
                callBack.onHttpSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        mQueue.add(stringRequest);
    }


}
