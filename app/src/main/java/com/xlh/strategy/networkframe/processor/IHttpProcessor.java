package com.xlh.strategy.networkframe.processor;

import com.xlh.strategy.networkframe.http.IHttpCallBack;

import java.util.Map;

public interface IHttpProcessor {

    void post(String url, Map<String, Object> params, IHttpCallBack callBack);

}
