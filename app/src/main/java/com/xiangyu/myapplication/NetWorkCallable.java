package com.xiangyu.myapplication;

import android.util.Log;

import java.util.concurrent.Callable;

import utils.HttpMethodUtil;

public class NetWorkCallable implements Callable<String> {

    private String inputText;

    public NetWorkCallable(String inputText) {
        this.inputText = inputText;
    }

    @Override
    public String call() throws Exception {
        return HttpMethodUtil.doGet("http://192.168.0.102:8080/translats?inputValue=" + inputText);
    }
}
