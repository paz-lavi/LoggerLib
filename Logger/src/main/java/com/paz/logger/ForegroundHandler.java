package com.paz.logger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.paz.taskrunnerlib.task_runner.RunnerCallback;
import com.paz.taskrunnerlib.task_runner.TaskRunner;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ForegroundHandler {
    public static ForegroundHandler.Listener listener;

    protected interface Listener {
        void onBecameForeground(Activity activity);

        void onBecameBackground(Context context);
    }


    protected  static void registerListener(Context context, final Listener listener) {
        ForegroundHandler.listener = listener;
        Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() {
            boolean foreground;
            boolean paused = true;
          //  private Executor executor = Executors.newSingleThreadExecutor();
            private final TaskRunner<Void> runner = new TaskRunner<>();

            @Override
            public void onActivityResumed(final Activity activity) {
                runner.executeAsync(new RunnerCallback<Void>() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public Void call() throws Exception {
                        if (!foreground)
                            try {
                                listener.onBecameForeground(activity);
                            } catch (Exception e) {
                                EZLog.getInstance().logException("Listener thrown an exception: ", e);
                            }
                        paused = false;
                        foreground = true;
                        return null;
                    }

                    @Override
                    public void onPostExecute(Void result) {

                    }
                });
//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (!foreground)
//                            try {
//                                listener.onBecameForeground(activity);
//                            } catch (Exception e) {
//                                Logger.getInstance().logException("Listener thrown an exception: ", e);
//                            }
//                        paused = false;
//                        foreground = true;
//                    }
//                });
            }

            @Override
            public void onActivityPaused(@NonNull final Activity activity) {
                runner.executeAsync(new RunnerCallback<Void>() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public Void call() throws Exception {
                        paused = true;
                        final Context context = activity.getApplicationContext();
                        try {
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    if (foreground && paused) {
                                        foreground = false;
                                        try {
                                            listener.onBecameBackground(context);
                                        } catch (Exception e) {
                                            EZLog.getInstance().logException("Listener threw exception! ", e);
                                        }
                                    }
                                }
                            }, 500);
                        } catch (Throwable t) {
                            EZLog.getInstance().logException("Background task failed with a throwable: ", t);
                        }
                        return null;
                    }

                    @Override
                    public void onPostExecute(Void result) {

                    }
                });

//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        paused = true;
//                        final Context context = activity.getApplicationContext();
//                        try {
//                            new Timer().schedule(new TimerTask() {
//                                @Override
//                                public void run() {
//                                    if (foreground && paused) {
//                                        foreground = false;
//                                        try {
//                                            listener.onBecameBackground(context);
//                                        } catch (Exception e) {
//                                            AFLogger.afErrorLog("Listener threw exception! ", e);
//                                        }
//                                    }
//                                }
//                            }, 500);
//                        } catch (Throwable t) {
//                            afErrorLog("Background task failed with a throwable: ", t);
//                        }
//                    }
//                });
            }

            @Override
            public void onActivityCreated(@NonNull final Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        };
        if (context instanceof Activity) callbacks.onActivityResumed((Activity) context);
        ((Application) context.getApplicationContext())
                .registerActivityLifecycleCallbacks(callbacks);
    }


}
