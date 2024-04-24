package com.onesignal.sample.android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import com.onesignal.notifications.IActionButton;
import com.onesignal.notifications.IDisplayableMutableNotification;
import com.onesignal.notifications.INotificationReceivedEvent;
import com.onesignal.notifications.INotificationServiceExtension;

import org.json.JSONException;

public class NotificationServiceExtension implements INotificationServiceExtension {
    private static final String CHANNEL_ID = "progress_channel";

    @Override
    public void onNotificationReceived(INotificationReceivedEvent event) {
        IDisplayableMutableNotification notification = event.getNotification();
        try {
            String William = notification.getAdditionalData().get("live_notification_key").toString();
            System.out.println(William);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Context context = event.getContext(); // Assuming there's a way to obtain context
        createNotificationChannel(context);

        // Simulated download progress
        int progressMax = 100;
        int currentProgress = 50; // This would be dynamically updated in a real scenario

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Android Live Notifications is in progress")
                .setContentText("Elly is working...")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_info))
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(progressMax, currentProgress, false);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build()); // ID 1 is arbitrary

        // As the download progresses, you would need to update the progress like so:
        // builder.setProgress(progressMax, newCurrentProgress, false);
        // notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Progress Notification";
            String description = "Shows the progress of a download";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
