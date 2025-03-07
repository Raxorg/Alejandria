package com.epicness.fundamentals.logic;

public interface DataListener<T> {

    void onSuccess(T t);

    void onFail();
}