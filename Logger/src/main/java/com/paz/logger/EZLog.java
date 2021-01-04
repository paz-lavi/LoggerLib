package com.paz.logger;

import android.content.Context;

import androidx.annotation.NonNull;

public abstract class EZLog {
    public static EZLog instance;

    /**
     * get EZ Log instance. Don't forget to initialize the SDK first
     *
     * @return EZ Log instance.
     */
    public static EZLog getInstance() {
        return instance;
    }

    /**
     * initialize the sdk
     *
     * @param key     - your devKey. If you don't have one, please download and register to the viewer app
     * @param context - app context
     */
    public static void init(@NonNull String key, @NonNull final Context context) {
        if (instance == null) {

            LoggerCore.initHelper(key, context);
            instance = LoggerCore.getInstance();
        }

    }

    /**
     * Start the SDK on app launch.
     */
    public abstract void start();

    /**
     * Log debug
     * @param msg - String. log message
     */
    public abstract void debug(String msg);

    /**
     * Log info
     * @param msg - String. log message
     */
    public abstract void info(String msg);

    /**
     * Log warn
     * @param msg - String. log message
     */
    public abstract void warn(String msg);

    /**
     * Log verbose
     * @param msg - String. log message
     */
    public abstract void verbose(String msg);

    /**
     * Log error
     * @param msg - String. log message
     */
    public abstract void error(String msg);
    /**
     * Log exception stacktrace
     * @param msg - String. log message
     * @param ex - Throwable. exception to log the stacktrace
     */
    public abstract void logException(String msg, @NonNull Throwable ex);

    /**
     * include debug logs in the document
     *
     * @param debug - boolean. pass true to include debug logs.
     *              set to true by default
     * @return EZ Log instance.
     */
    public abstract EZLog setDebug(boolean debug);

    /**
     * enable the sdk to print the logs to the logcat
     * @param printToLogcat - boolean. pass true to print the logs to logcat.
     *                      set to true by default
     * @return EZ Log instance.
     */
    public abstract EZLog setPrintToLogcat(boolean printToLogcat);

    /**
     * Set current user Id
     * @param costumerId - String. pass customer ID (for example current user UUID)
     * @return EZ Log instance.
     */
    public abstract EZLog setCostumerId(String costumerId);

}
