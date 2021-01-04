package com.paz.logger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.paz.prefy_lib.Prefy;

import java.util.ArrayList;
import java.util.Arrays;

import static com.paz.logger.helper.TimestampGenerator.currentTimeStamp;

public class LoggerCore extends EZLog {
    private boolean debug = true;
    private boolean printToLogcat = true;
    private ArrayList<Log> logs;
    private String devKey;
    private String costumerId;
    private Application application;
    public static LoggerCore instance;
    private final String TAG = "Logger";
    private final LoggerDB db;

    public static LoggerCore getInstance() {
        return instance;
    }


    private LoggerCore(@NonNull String key, @NonNull final Context context) {
        devKey = key;
        application = (Application) context.getApplicationContext();
        logs = new ArrayList<>();
        db = new LoggerDB();
        Prefy.init(context.getApplicationContext(), true);

    }


    protected static void initHelper(@NonNull String key, @NonNull final Context context) {
        if (instance == null) {
            instance = new LoggerCore(key, context.getApplicationContext());
        } else {
            instance.info("Logger already initialized");
        }


    }
    @Override
    public void start() {
        ForegroundHandler.registerListener(application.getApplicationContext(), new ForegroundHandler.Listener() {
            public void onBecameForeground(Activity activity) {
//                timeEnteredForeground = System.currentTimeMillis();
//                eventDataCollector(activity).foreground();
                EZLog.getInstance().info("onBecameForeground");
            }

            public void onBecameBackground(Context context) {
                EZLog.getInstance().info("onBecameBackground");

                String json = new Gson().toJson(logs.toArray(new Log[0]));
                SessionDocument doc = new SessionDocument(application, json,costumerId);
                db.uploadLogsToDB(application, devKey, doc);

                android.util.Log.d(TAG + " sum", json);
                logs.clear();

//                timeWentToBackground = System.currentTimeMillis();
//                eventDataCollector(context)
//                        .set(PREVIOUS_SESSION_DURATION, TimeUnit.MILLISECONDS.toSeconds(
//                                timeWentToBackground - timeEnteredForeground));
//                AFLogger.afInfoLog("callStatsBackground background call");
//                callStatsBackground(new WeakReference<>(context));
//                ProxyManager rdInstance = ProxyManager.getInstance();
//                if (rdInstance.isProxyEnabledFromServer()) {
//                    rdInstance.stopProxyMode();
//                    if (context != null) {
//                        String packageName = context.getPackageName();
//                        PackageManager packageManager = context.getPackageManager();
//                        rdInstance.sendProxyData(packageName, packageManager);
//                    }
//                    rdInstance.releaseProxy();
//                } else {
//                    AFLogger.afDebugLog("RD status is OFF");
//                }
//                AFExecutor.getInstance().shutdownExecutors();
//                AFSensorManager.getInstance(context).stopStartedTracking();
            }
        });

    }


    @Override
    public void debug(String msg) {
        if (!debug)
            return;
        Log log = new Log().setMsg(msg).setLevel(LogLevel.DEBUG).setDate(currentTimeStamp());
        logs.add(log);
        if (printToLogcat)
            android.util.Log.d(TAG, msg);

    }

    @Override
    public void info(String msg) {
        Log log = new Log().setMsg(msg).setLevel(LogLevel.INFO).setDate(currentTimeStamp());
        logs.add(log);
        android.util.Log.i(TAG, msg);

    }

    @Override
    public void warn(String msg) {
        Log log = new Log().setMsg(msg).setLevel(LogLevel.WARN).setDate(currentTimeStamp());
        logs.add(log);
        android.util.Log.w(TAG, msg);

    }

    @Override
    public void verbose(String msg) {
        Log log = new Log().setMsg(msg).setLevel(LogLevel.VERBOSE).setDate(currentTimeStamp());
        logs.add(log);
        if (printToLogcat)
            android.util.Log.v(TAG, msg);

    }

    @Override
    public void error(String msg) {
        Log log = new Log().setMsg(msg).setLevel(LogLevel.ERROR).setDate(currentTimeStamp());
        logs.add(log);
        if (printToLogcat)
            android.util.Log.e(TAG, msg);
    }

    @Override
    public void logException(String msg, @NonNull Throwable ex) {
        error(msg);
        error(ex.getMessage());
        StringBuilder trace = new StringBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Arrays.stream(ex.getStackTrace()).forEach(stackTraceElement -> {
                trace.append(stackTraceElement.toString()).append("\n");
            });
        } else {
            for (StackTraceElement element : ex.getStackTrace()) {
                trace.append(element.toString()).append("\n");
            }
        }
        error(trace.toString());
    }


    @Override
    public EZLog setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    @Override
    public EZLog setPrintToLogcat(boolean printToLogcat) {
        this.printToLogcat = printToLogcat;
        return this;
    }

    @Override
    public EZLog setCostumerId(String costumerId) {
        this.costumerId = costumerId;
        return this;
    }
}
