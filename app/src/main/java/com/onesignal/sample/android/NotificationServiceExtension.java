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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** @noinspection unused*/
public class NotificationServiceExtension implements INotificationServiceExtension {
    // Live Notification IDs
    private static final String PROGRESS_LIVE_NOTIFICATION = "progress";
    private static final String ANOTHER_LIVE_NOTIFICATION = "another";
    private static final String DISMISS_LIVE_NOTIFICATION = "dismiss";

    // Channels
    private static final String PROGRESS_CHANNEL_ID = "progress_channel";

    @Override
    public void onNotificationReceived(INotificationReceivedEvent event) {
        HashMap<String, Integer> keyMap = new HashMap<String, Integer>();
        keyMap.put(PROGRESS_LIVE_NOTIFICATION, 0);

        IDisplayableMutableNotification notification = event.getNotification();
        Context context = event.getContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannels(notificationManager);
        JSONObject additionalData = notification.getAdditionalData();
        if (additionalData == null) return;
        JSONObject liveNotificationPayload = additionalData
                .optJSONObject("live_notification");

        if (liveNotificationPayload == null) {
            return;
        }

        try {
            String liveNotificationKey = liveNotificationPayload.optString("key");

            String liveNotificationEvent = liveNotificationPayload.getString("event");
            if (liveNotificationEvent.equals("dismiss")) {
                event.preventDefault();
                int key = keyMap.get(liveNotificationKey);
                notificationManager.cancel(key);
                return;
            }


            JSONObject liveNotificationUpdates = liveNotificationPayload.getJSONObject("event_updates");
            NotificationCompat.Builder builder;

            switch (liveNotificationKey) {
                case PROGRESS_LIVE_NOTIFICATION:
                    int currentProgress = liveNotificationUpdates
                            .getInt("current_progress");

                    builder = new NotificationCompat.Builder(context, PROGRESS_CHANNEL_ID)
                            .setContentTitle(" Progress Live Notifications")
                            .setContentText("It's working...")
                            .setSmallIcon(android.R.drawable.ic_media_play)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_info))
                            .setOngoing(true)
                            .setOnlyAlertOnce(true)
                            .setProgress(100, currentProgress, false);
                    notificationManager.notify(keyMap.get(PROGRESS_LIVE_NOTIFICATION), builder.build());
                    break;
                default:
                    throw new IllegalStateException("Unsupported Live Notification Key provided: " + liveNotificationKey);
            }
           // ID 1 is arbitrary
        } catch (JSONException | NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createNotificationChannels(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;

        NotificationChannel progressChannel = notificationManager.getNotificationChannel(PROGRESS_CHANNEL_ID);
        if (progressChannel == null) {
            progressChannel = new NotificationChannel(PROGRESS_CHANNEL_ID, "Progress Live Notification", NotificationManager.IMPORTANCE_LOW);
            progressChannel.setDescription("Shows the progress of a download");
            notificationManager.createNotificationChannel(progressChannel);
        }
    }
}
