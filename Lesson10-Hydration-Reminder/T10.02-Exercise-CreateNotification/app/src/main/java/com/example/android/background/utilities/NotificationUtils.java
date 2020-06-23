package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    private static final int REQUEST_CODE = 385;
    private static final int NOTIFICATION_ID = 284;
    private static final String NOTIFICATION_CHANNEL_ID = "notification-channel";

    public static void remindUserBecauseCharging(Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                            context.getString(R.string.main_notification_channel_name),
                            NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .setSmallIcon(R.drawable.ic_drink_notification)
                        .setLargeIcon(largeIcon(context))
                        .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                        .setContentText(context.getString(R.string.charging_reminder_notification_body))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(context.getString(
                                        R.string.charging_reminder_notification_body)))
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setContentIntent(contentIntent(context))
                        .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(Notification.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    // This method will create a notification for charging. It might be helpful
    // to take a look at this guide to see an example of what the code in this method will look like:
    // https://developer.android.com/training/notify-user/build-notification.html

    private static PendingIntent contentIntent(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context,
                REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        return BitmapFactory.decodeResource
                (res, R.drawable.ic_local_drink_black_24px);
    }

}
