package com.example.broadcastreciverapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Action: " + 		intent.getAction(), Toast.LENGTH_SHORT).show();
        Log.i("Receiver", "Broadcast received: " + intent.getAction());
        if(intent.getAction().equals("my.action.string")){

            String str = intent.getExtras().getString("extra");
            NotificationChannel(context,str);
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }

    }

    private void NotificationChannel(Context context,String str) {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            NotificationChannel notificationChannel = new NotificationChannel("1", "first", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]

                    {
                            100, 200, 300, 400, 500, 400, 300, 200, 400
                    });
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);


            NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "channel_id")
                    .setContentTitle(str)
                    .setContentText(" Message")
                    .setSmallIcon(R.mipmap.ic_launcher);

            notificationManager.notify(1, notification.build());
        }else {

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "Default")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(str)
                    .setContentText("Message")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, mBuilder.build());

        }
    }
}
