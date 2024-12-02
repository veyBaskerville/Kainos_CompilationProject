package com.example.elective1compilationproject;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class _12thGuidedExercise extends AppCompatActivity {

    String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._12th_guided_exercise);

        // Set the package name dynamically
        packageName = getApplicationContext().getPackageName().concat(".");

        // Register system broadcast receivers for Airplane Mode and Battery Changes
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        _12thMyReceiver myReceiver = new _12thMyReceiver();
        registerReceiver(myReceiver, intentFilter);

        // Send custom broadcast (for testing purposes)
        broadcastIntent();
    }

    // Custom method to send a broadcast
    public void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction(packageName + "MY_CUSTOM_ACTION");
        intent.setClass(this, _12thMyCustomReceiver.class);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the receiver when the activity is destroyed
        unregisterReceiver(new _12thMyReceiver());
    }
}