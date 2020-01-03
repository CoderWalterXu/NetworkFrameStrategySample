package com.xlh.strategy.networkframe.http;

public interface IHttpCallBack {
    void onHttpSuccess(String result);

    void onHttpFailure();
}
