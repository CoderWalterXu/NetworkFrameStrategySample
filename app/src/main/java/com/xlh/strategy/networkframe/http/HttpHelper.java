package com.xlh.strategy.networkframe.http;

import android.util.Log;

import com.xlh.strategy.networkframe.processor.IHttpProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public class HttpHelper implements IHttpProcessor {

    private static HttpHelper instance;

    private static IHttpProcessor iHttpProcessor;

    public HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }

    /**
     * @param httpProcessor 具体的处理类
     */
    public static void init(IHttpProcessor httpProcessor) {
        iHttpProcessor = httpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, IHttpCallBack callBack) {
        String finalUrl = appendParams(url, params);
        iHttpProcessor.post(finalUrl, params, callBack);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlBuilder.indexOf("?") <= 0) {
            urlBuilder.append("?");
        } else {
            if (!urlBuilder.toString().endsWith("?")) {
                urlBuilder.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlBuilder.append("&" + entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        Log.e("Test", "请求url--->" + urlBuilder.toString());
        return urlBuilder.toString();
    }

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
