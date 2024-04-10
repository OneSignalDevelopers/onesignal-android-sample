package com.onesignal.sample.android;

import com.onesignal.notifications.IActionButton;
import com.onesignal.notifications.IDisplayableMutableNotification;
import com.onesignal.notifications.INotificationReceivedEvent;
import com.onesignal.notifications.INotificationServiceExtension;

import org.json.JSONException;

public class NotificationServiceExtension implements INotificationServiceExtension {
    @Override
    public void onNotificationReceived(INotificationReceivedEvent event) {
        IDisplayableMutableNotification notification = event.getNotification();

        if (notification.getActionButtons() != null) {
            for (IActionButton button : notification.getActionButtons()) {
                // you can modify your action buttons here
            }
        }

        // this is an example of how to modify the notification by changing the background color to blue
        notification.setExtender(builder -> builder.setColor(0xFF0000FF));
        notification.setExtender(builder -> builder.setAutoCancel(false));
        try {
            // We could check some property on the notification that we set to determine
            // whether the notification should be cancelled.
            // Ex uses "OSAction": "stop"
            // We could potentially use `dismissal_date` to determine to cancel
            if (event.getNotification().getAdditionalData().get("OSAction") == "stop") {
                // We can get the notification ID from the event itself
                int notificationId = Integer.parseInt(event.getNotification().getNotificationId());

                // Does our SDK provide a hook into this?
                // My assumption is the SDK has a manager to manage all incoming OneSignal notifications

                // NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // Assuming we can cancel
                //notificationManager.cancel(notificationId);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}

