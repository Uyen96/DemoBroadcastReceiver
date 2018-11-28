package com.example.uyen.demobroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MainActivity.ACTION_BROADCAST)) {
            String title = intent.getStringExtra(MainActivity.ACTION_BROADCAST);
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
        }
    }
}
