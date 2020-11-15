package com.jabarian.adabazi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public String LOG = "battery_change";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
        {
            boolean is_not_connected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            if(is_not_connected)
                Toast.makeText(context, "Oh noooooooooo 🤔", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "Hurrrrrrray, 😊", Toast.LENGTH_LONG).show();
        }
        // for my account at linkedin.com/in/peyman-majidi-moein/
        if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction()))
        {
            int level = intent.getIntExtra("level", -1);
            int plugged = intent.getIntExtra("plugged", -1);
            Log.d(LOG, "Level: " + level);
            Log.d(LOG, "plugged: " + plugged);
            MainActivity.Battery = level;
        }


    }
}
