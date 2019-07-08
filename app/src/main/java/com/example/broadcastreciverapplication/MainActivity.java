package com.example.broadcastreciverapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);


//        Intent intent = new Intent("my.action.string");
//        intent.putExtra("extra", "My string"); // phoneNo is the value to sent
//        sendBroadcast(intent);
    }

    public void SendBroadcast(View view) {
        //NotificationChannel();

        Intent intent = new Intent("my.action.string");
        intent.putExtra("extra", editText.getText().toString()); // phoneNo is the value to sent
        sendBroadcast(intent);

    }


    private void NotificationChannel() {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            NotificationChannel notificationChannel = new NotificationChannel("1", "first", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]

                    {
                            100, 200, 300, 400, 500, 400, 300, 200, 400
                    });
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);


            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "channel_id")
                    .setContentTitle("Test Title")
                    .setContentText("Test Message")
                    .setSmallIcon(R.mipmap.ic_launcher);

            notificationManager.notify(1, notification.build());
        }else {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "Default")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("My notification")
                    .setContentText("Message")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, mBuilder.build());

        }
    }




}
