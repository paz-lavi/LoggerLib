package com.paz.logger;

import android.content.Context;

import com.paz.logger.data.DeviceData;
import com.paz.logger.helper.TimestampGenerator;

public class SessionDocument {
    private String logs; // logs as json string
    private String costumerId;
    private String ezLogId;
    private int sessionCounter;
    private Context context;
    private Float batteryLevel;
    private int brightness;
    private String ringerMode;
    private String manufacturerName;
    private boolean btEnable;
    private boolean connectedToWifi;
    private boolean gpsEnable;
    private String deviceLang;
    private String date;
    private long timestamp;
    private String packageName;

    public SessionDocument(Context context) {
        this(context, null, "");

    }


    protected SessionDocument(Context context, String logs, String costumerId) {
        this.logs = logs;
        this.costumerId = costumerId;
        this.context = context;
        date = TimestampGenerator.currentTimeStamp();
        timestamp = System.currentTimeMillis();
        DeviceData.init(context);

    }


    public void updateDeviceData() {
        DeviceData dd = DeviceData.getInstance();
        batteryLevel = dd.getBatteryLevel();
        brightness = dd.getBrightness();
        ringerMode = dd.getDeviceRingerMode();
        manufacturerName = dd.getManufacturerName();
        btEnable = dd.isBTEnable();
        connectedToWifi = dd.isConnectedToWifi();
        gpsEnable = dd.isGpsEnable();
        deviceLang = dd.getDeviceLang();
        packageName = dd.getPackageName();

    }

    public SessionDocument setLogs(String logs) {
        this.logs = logs;
        return this;
    }

    public SessionDocument setCostumerId(String costumerId) {
        this.costumerId = costumerId;
        return this;
    }

    public String getEzLogId() {
        return ezLogId;
    }

    public SessionDocument setEzLogId(String ezLogId) {
        this.ezLogId = ezLogId;
        return this;
    }

    public int getSessionCounter() {
        return sessionCounter;
    }

    public SessionDocument setSessionCounter(int sessionCounter) {
        this.sessionCounter = sessionCounter;
        return this;
    }

    public String getLogs() {
        return logs;
    }

    public String getCostumerId() {
        return costumerId;
    }


    public Float getBatteryLevel() {
        return batteryLevel;
    }

    public int getBrightness() {
        return brightness;
    }

    public String getRingerMode() {
        return ringerMode;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public boolean isBtEnable() {
        return btEnable;
    }

    public boolean isConnectedToWifi() {
        return connectedToWifi;
    }

    public boolean isGpsEnable() {
        return gpsEnable;
    }

    public String getDeviceLang() {
        return deviceLang;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public SessionDocument setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getDate() {
        return date;
    }

    public SessionDocument setDate(String date) {
        this.date = date;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public SessionDocument setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }
}
