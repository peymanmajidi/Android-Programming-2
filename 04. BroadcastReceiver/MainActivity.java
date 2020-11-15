package com.jabarian.adabazi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    public MyBroadcastReceiver broadcastReceiver = new MyBroadcastReceiver();
    public MyBroadcastReceiver broadcastReceiverConnectivity = new MyBroadcastReceiver();

    public static int Battery = -1;

    TextView lblBattery;
    ImageView imgBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblBattery = findViewById(R.id.lblBattery);
        imgBattery = findViewById(R.id.imgBattery);

//
//        Intent messanger = new Intent("com.jabarian.adabazi");
//        messanger.putExtra("Peyman", "this is mt very first messsage via BR");
//        sendBroadcast(messanger);

        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        new Thread(){
            @Override
            public void run() {
               int last = -1;
                while(true)
                    if(last != Battery)
                    {
                        last = Battery;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(Battery ==0)
                                    imgBattery.setImageResource(R.drawable.batt0);
                                else if(Battery <= 5)
                                    imgBattery.setImageResource(R.drawable.batt1);
                                else if(Battery < 10)
                                    imgBattery.setImageResource(R.drawable.batt2);
                                else if(Battery < 20)
                                    imgBattery.setImageResource(R.drawable.batt3);
                                else if(Battery < 30)
                                    imgBattery.setImageResource(R.drawable.batt4);
                                else if(Battery < 45)
                                    imgBattery.setImageResource(R.drawable.batt45);
                                else if(Battery <= 50)
                                    imgBattery.setImageResource(R.drawable.batt5);
                                else if(Battery < 60)
                                    imgBattery.setImageResource(R.drawable.batt6);
                                else if(Battery < 70)
                                    imgBattery.setImageResource(R.drawable.batt7);
                                else if(Battery < 80)
                                    imgBattery.setImageResource(R.drawable.batt8);
                                else if(Battery < 90)
                                    imgBattery.setImageResource(R.drawable.batt9);
                                else
                                    imgBattery.setImageResource(R.drawable.batt10);

                                lblBattery.setText("Battery: " + Battery + "%" );

                            }
                        });
                    }


            }
        }.start();



    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiverConnectivity, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiverConnectivity);
    }
}