package com.paz.loggerlib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.paz.logger.EZLog;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private MaterialButton main_BTN_add, main_BTN_next;
    private TextView main_LBL_counter;
    EZLog ezLog = EZLog.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setVies();
        mockupLogs();

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
    }

    private void nextActivity() {
        Intent intent = new Intent(MainActivity.this, SecActivity.class);
        startActivity(intent);
    }


    private void findView() {
        main_BTN_add = findViewById(R.id.main_BTN_add);
        main_LBL_counter = findViewById(R.id.main_LBL_counter);
        main_BTN_next = findViewById(R.id.main_BTN_next);
    }
}