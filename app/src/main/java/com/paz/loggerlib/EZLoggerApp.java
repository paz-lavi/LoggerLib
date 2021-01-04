package com.paz.loggerlib;

import android.app.Application;
import com.paz.logger.EZLog;


public class EZLoggerApp extends Application {
    private final String devKey = "FTGDmOi0PnUFuspIRu6vKdZQu812";

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
