package com.xlh.strategy.networkframe.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallBack<T> implements IHttpCallBack {
    @Override
    public void onHttpSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clazz = getT(this);
        T t = (T) gson.fromJson(result, clazz);
        onJsonSuccess(t);
    }

    public abstract void onJsonSuccess(T t);

    private Class<?> getT(Object obj) {
        Type genType = obj.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];
    }

    @Override
    public void onHttpFailure() {

    }
}
