package com.onesignal.sample.android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import com.onesignal.notifications.IDisplayableMutableNotification;
import com.onesignal.notifications.INotificationReceivedEvent;
import com.onesignal.notifications.INotificationServiceExtension;

import org.json.JSONException;

import java.util.Objects;

/** @noinspection unused*/
public class NotificationServiceExtension implements INotificationServiceExtension {
    // Live Notification IDs
    private static final String PROGRESS_LIVE_NOTIFICATION = "progress";
    private static final String ANOTHER_LIVE_NOTIFICATION = "another";
    private static final String DISMISS_LIVE_NOTIFICATION = "dismiss";

    // Channels
    private static final String PROGRESS_CHANNEL_ID = "progress_channel";
    private static final String ANOTHER_CHANNEL_ID = "another_channel";

    @Override
    public void onNotificationReceived(INotificationReceivedEvent event) {
        IDisplayableMutableNotification notification = event.getNotification();
        Context context = event.getContext();
        System.out.println("💩💩💩💩💩");
        try {
            String liveNotificationTypeId = Objects.requireNonNull(notification.getAdditionalData()).getString("live_notification_key");
            NotificationCompat.Builder builder;
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            switch (liveNotificationTypeId) {
                case PROGRESS_LIVE_NOTIFICATION:
                    createNotificationChannel(event.getContext(), liveNotificationTypeId);
                    int currentProgress = Objects.requireNonNull(notification.getAdditionalData()).getJSONObject("live_notification_key").getInt("current_progress");
                    builder = new NotificationCompat.Builder(context, PROGRESS_CHANNEL_ID)
                            .setContentTitle("Android Live Notifications is in progress")
                            .setContentText("Elly is working...")
                            .setSmallIcon(android.R.drawable.ic_media_play)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_info))
                            .setOngoing(true)
                            .setOnlyAlertOnce(true)
                            .setProgress(100, currentProgress, false);
                    break;
                case ANOTHER_LIVE_NOTIFICATION:
                    builder = new NotificationCompat.Builder(context, PROGRESS_CHANNEL_ID)
                            .setContentTitle("Some other Live Notification")
                            .setContentText("Content goes here")
                            .setSmallIcon(android.R.drawable.ic_media_play)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_info))
                            .setOngoing(true)
                            .setOnlyAlertOnce(true);
                    break;
                case DISMISS_LIVE_NOTIFICATION:
                    notificationManager.cancelAll();
                default:
                    throw new IllegalStateException("Unsupported Live Notification Key provided: " + liveNotificationTypeId);
            }
            notificationManager.notify(1, builder.build()); // ID 1 is arbitrary
        } catch (JSONException | NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }
    private void createNotificationChannel(Context context, String liveNotificationTypeId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        NotificationChannel channel;
        switch (liveNotificationTypeId) {
            case PROGRESS_LIVE_NOTIFICATION:
                NotificationChannel maybeProgressChannel = notificationManager.getNotificationChannel(PROGRESS_CHANNEL_ID);
                if (maybeProgressChannel == null) {
                    channel = new NotificationChannel(PROGRESS_CHANNEL_ID, "Progress Live Notification", NotificationManager.IMPORTANCE_LOW);
                    channel.setDescription("Shows the progress of a download");
                } else {
                    channel = maybeProgressChannel;
                }
                break;
            case ANOTHER_LIVE_NOTIFICATION:
                System.out.println("💩💩💩💩💩");
                NotificationChannel maybeAnotherChannel = notificationManager.getNotificationChannel(ANOTHER_CHANNEL_ID);
                if (maybeAnotherChannel == null) {
                    channel = new NotificationChannel(ANOTHER_CHANNEL_ID, "Another Live Notification", NotificationManager.IMPORTANCE_LOW);
                    channel.setDescription("Whatever you like");
                } else {
                    channel = maybeAnotherChannel;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected live notification type id: " + liveNotificationTypeId);
        }

        notificationManager.createNotificationChannel(channel);
    }
}
