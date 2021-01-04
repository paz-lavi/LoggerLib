package com.paz.loggerlib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.paz.logger.EZLog;
import com.paz.logger.LogLevel;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private MaterialButton main_BTN_add, main_BTN_next, main_BTN_verbose, main_BTN_info, main_BTN_warn, main_BTN_error, main_BTN_debug, main_BTN_moc;
    private TextView main_LBL_counter;
    EZLog ezLog = EZLog.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setVies();
        // mockupLogs();

    }


    private void mockupLogs() {
        ezLog.debug("this is debug log");
        ezLog.info("this is info log");
        ezLog.warn("this is warn log");
        ezLog.error("this is error log");
        ezLog.verbose("this is verbose log");
        ezLog.debug("1fsvdvfsvsfsfsfv");
        ezLog.error("1fsvdvfsvsfsfsfv");
        ezLog.error("1fsvdvfsvsfsfsfv");
        ezLog.error("1fsvdvfsvsfsfsfv");
        ezLog.error("1fsvdvfsvsfsfsfv");
        ezLog.error("1fsvdvfsvsfsfsfv");
        ezLog.info("1fsvdvfsvsfsfsfv");
        ezLog.info("1fsvdvfsvsfsfsfv");
        ezLog.verbose("1fsvdvfsvsfsfsfv");
        ezLog.verbose("1fsvdvfsvsfsfsfv");
        ezLog.verbose("1fsvdvfsvsfsfsfv");
        ezLog.verbose("1fsvdvfsvsfsfsfv");
        ezLog.verbose("1fsvdvfsvsfsfsfv");
        ezLog.verbose("1fsvdvfsvsfsfsfv");
        ezLog.verbose("1fsvdvfsvsfsfsfv");
        ezLog.warn("1fsvdvfsvsfsfsfv");
        ezLog.warn("1fsvdvfsvsfsfsfv");
        ezLog.warn("1fsvdvfsvsfsfsfv");
        ezLog.warn("1fsvdvfsvsfsfsfv");
        ezLog.warn("1fsvdvfsvsfsfsfv");
        ezLog.debug("1fsvdvfsvsfsfsfv");
        ezLog.debug("1fsvdvfsvsfsfsfv");
    }

    @SuppressLint("SetTextI18n")
    private void updateCounter() {
        main_LBL_counter.setText("counter = " + ++counter);
        EZLog.getInstance().debug("counter = " + counter);
    }


    private void setVies() {
        main_BTN_add.setOnClickListener(e -> updateCounter());
        main_BTN_next.setOnClickListener(e -> nextActivity());
        main_BTN_debug.setOnClickListener(e -> addLog(LogLevel.DEBUG));
        main_BTN_error.setOnClickListener(e -> addLog(LogLevel.ERROR));
        main_BTN_warn.setOnClickListener(e -> addLog(LogLevel.WARN));
        main_BTN_info.setOnClickListener(e -> addLog(LogLevel.INFO));
        main_BTN_verbose.setOnClickListener(e -> addLog(LogLevel.VERBOSE));
        main_BTN_moc.setOnClickListener(e -> mockupLogs());
    }

    private void addLog(int type) {
        switch (type) {
            case LogLevel.DEBUG:
                ezLog.debug("this is debug log");
                break;
            case LogLevel.INFO:
                ezLog.info("this is info log");
                break;
            case LogLevel.ERROR:
                ezLog.error("this is error log");
                break;
            case LogLevel.WARN:
                ezLog.warn("this is warn log");
                break;
            case LogLevel.VERBOSE:
                ezLog.verbose("this is verbose log");
                break;
        }
    }

    private void nextActivity() {
        Intent intent = new Intent(MainActivity.this, SecActivity.class);
        startActivity(intent);
    }


    private void findView() {
        main_BTN_add = findViewById(R.id.main_BTN_add);
        main_LBL_counter = findViewById(R.id.main_LBL_counter);
        main_BTN_next = findViewById(R.id.main_BTN_next);
        main_BTN_debug = findViewById(R.id.main_BTN_debug);
        main_BTN_error = findViewById(R.id.main_BTN_error);
        main_BTN_warn = findViewById(R.id.main_BTN_warn);
        main_BTN_info = findViewById(R.id.main_BTN_info);
        main_BTN_verbose = findViewById(R.id.main_BTN_verbose);
        main_BTN_moc = findViewById(R.id.main_BTN_moc);
    }
}