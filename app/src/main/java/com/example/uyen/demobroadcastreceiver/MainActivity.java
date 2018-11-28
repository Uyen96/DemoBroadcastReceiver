package com.example.uyen.demobroadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String ACTION_BROADCAST = "Receiver Demo";
    public static final String CONTENT_ACTION = " Demo send broadcast receiver!";
    private NetworkChangedReceiver mReceiver;
    private Button mButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
        initBroadcastCustom();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void initView() {
        mButtonSend = findViewById(R.id.button_send);
    }

    public void registerListener() {
        mButtonSend.setOnClickListener(this);
    }

    public void initBroadcast() {
        mReceiver = new NetworkChangedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mReceiver, filter);
    }

    public void initBroadcastCustom() {
        mReceiver = new NetworkChangedReceiver();
        IntentFilter filterCustom = new IntentFilter();
        filterCustom.addAction(ACTION_BROADCAST);
        registerReceiver(mReceiver, filterCustom);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_send) {
            Intent intentActivity = new Intent(MainActivity.this, DemoActivity.class);
            startActivity(intentActivity);
            Intent intentSend = new Intent();
            intentSend.putExtra(ACTION_BROADCAST, CONTENT_ACTION);
            intentSend.setAction(ACTION_BROADCAST);
            sendBroadcast(intentSend);
        }
    }
}
