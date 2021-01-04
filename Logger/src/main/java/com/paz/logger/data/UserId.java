package com.paz.logger.data;

import android.content.Context;

import com.paz.prefy_lib.Prefy;


import static com.paz.logger.data.Constants.*;

public class UserId {
    private Context context;
    private String devKey;



    public UserId(Context context, String devKey) {
        this.context = context;
        this.devKey = devKey;


    }

    public int getSessionCounter() {
        Prefy prefy = Prefy.getInstance();
        int counter = prefy.getInt(SESSIONS_KEY, 0) + 1;
        prefy.putInt(SESSIONS_KEY, counter);
        return counter;
    }

    public String getUserId() {
        Prefy prefy = Prefy.getInstance();
        String id = prefy.getString(ID_KEY, DEF_VALUE);
        if (id.equals(DEF_VALUE)) {
            id = generateId();
            prefy.putString(ID_KEY, id);
        }
        return id;
    }

    private String generateId() {
        long now = System.currentTimeMillis();
        int mul = Integer.parseInt(String.valueOf(now).substring(String.valueOf(now).length() - 3)) * 31;
        int val = 0;
        for (int i = 0; i < devKey.length(); i++) {
            val += devKey.charAt(i);
        }

        return now * 31 + "-" + val * mul;
    }


}
