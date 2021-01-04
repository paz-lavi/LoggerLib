package com.paz.logger.helper;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

public class TimestampGenerator {
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss:ms");

    public static String currentTimeStamp() {
        return dateFormat.format(System.currentTimeMillis());
    }

}
