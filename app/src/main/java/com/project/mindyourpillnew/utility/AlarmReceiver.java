package com.project.mindyourpillnew.utility;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.project.mindyourpillnew.R;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManager;
    public static int NOTIFICATION_NUMBER=1;
    public static final String CHANNEL_ID = "channel";
    @Override
    public void onReceive(Context context, Intent intent) {
        String medName = intent.getStringExtra("medicineName");
        String medQty = intent.getStringExtra("medQty");
        String userName = intent.getStringExtra("userName");

        notificationManager = NotificationManagerCompat.from(context);
        sendOnChannel(context,"Please take your "+medName+".",intent);
    }

    public void sendOnChannel(Context context, String message, Intent intent){
        Intent resultIntent = new Intent(context,AlarmActivity.class);
        resultIntent.putExtra("medName",intent.getStringExtra("medName"));
        resultIntent.putExtra("medTime",intent.getStringExtra("medTime"));
        resultIntent.putExtra("medQty",intent.getStringExtra("medQty"));
        resultIntent.putExtra("userName",intent.getStringExtra("userName"));
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,1,resultIntent,PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Medicine Reminder")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .build();
        notificationManager.notify(NOTIFICATION_NUMBER++,notification);

    }
}
