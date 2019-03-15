package com.example.broadcastreciverapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Action: " + 		intent.getAction(), Toast.LENGTH_SHORT).show();
        Log.i("Receiver", "Broadcast received: " + intent.getAction());
        if(intent.getAction().equals("my.action.string")){

            String str = intent.getExtras().getString("extra");
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }

    }
}
