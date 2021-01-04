package com.paz.loggerlib;

import android.app.Application;

import com.paz.logger.EZLog;


public class EZLoggerApp extends Application {
    private final String devKey = "oi1GCaoAn1bU8s5m3zrn6L5QKb93";

    @Override
    public void onCreate() {
        super.onCreate();
        EZLog.init(devKey, this);
        EZLog.getInstance()
                .setDebug(true)
                .setPrintToLogcat(true)
                .setPrintToLogcat(true)
                .setCostumerId("test")
                .start();
    }
}
