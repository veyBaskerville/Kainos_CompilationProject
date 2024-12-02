package com.example.elective1compilationproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class _12thMyCustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Custom Broadcast Received: " + intent.getAction(), Toast.LENGTH_SHORT).show();
    }
}