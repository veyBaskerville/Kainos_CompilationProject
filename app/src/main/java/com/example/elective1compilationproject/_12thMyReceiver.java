package com.example.elective1compilationproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class _12thMyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state;

        // Handle the different broadcast actions
        switch (intent.getAction()) {
            case Intent.ACTION_BATTERY_CHANGED:
                state = "Battery level or status has changed.";
                showNotification("Battery Status", state, context);
                break;

            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                state = "Airplane mode state has changed.";
                showNotification("Airplane Mode Changed", state, context);
                break;

            default:
                break;
        }
    }

    private void showNotification(String title, String message, Context context) {
        // Create notification channel for Android 8.0+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "my_channel_id",           // Channel ID
                    "Notification Channel",    // Channel name (visible to user)
                    NotificationManager.IMPORTANCE_HIGH // Priority level
            );
            channel.setDescription("Channel for app notifications");
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "my_channel_id")
                .setSmallIcon(R.drawable.ic_announcement) // Replace with your actual icon resource
                .setContentTitle(title) // Title of the notification
                .setContentText(message) // Message/body of the notification
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Priority for pre-Oreo devices
                .setDefaults(NotificationCompat.DEFAULT_ALL) // Sound, vibration, and light
                .setAutoCancel(true); // Notification dismisses itself when clicked

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build()); // Use a fixed ID or dynamic ID

        // Play notification sound
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alert); // Replace with your actual sound file
        mediaPlayer.start();
    }
}