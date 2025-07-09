package com.example.smshelper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    static MyService myService;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myService = this;
        //start the foreground service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground()
    {
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";

        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_MIN);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Intent NotiClickIntent = new Intent(this,logoMain.class);
        PendingIntent notiClickPen = PendingIntent.getActivity(this,1,NotiClickIntent,PendingIntent.FLAG_MUTABLE);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("SmsHelper Starting Help you")
                .setContentText("Do Your Work Without Stress")
                //this is important, otherwise the notification will show the way
                //you want i.e. it will show some default notification
                .setSmallIcon(R.mipmap.app_newlogo_round)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setContentIntent(notiClickPen)
                .build();
        SharedPreferences myser = getSharedPreferences("myser", Context.MODE_PRIVATE);
        SharedPreferences.Editor e_myser = myser.edit();
        e_myser.putString("myser", "true");
        e_myser.apply();
        startForeground(2, notification);
    }

    @Override
    public void onDestroy() {
        SharedPreferences myser = getSharedPreferences("myser", Context.MODE_PRIVATE);
        SharedPreferences.Editor e_myser = myser.edit();
        e_myser.putString("myser", "false");
        e_myser.apply();
        //create an Intent to call the Broadcast receiver
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this,MyReceiver.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }
}