package com.example.erickivet.notificationslab;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //private NotificationCompat.Builder notificationBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, intent, 0);

        NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Wifi Notification");
        notificationBuilder.setContentTitle("Wifi Notification Text");
        notificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setPriority(Notification.PRIORITY_MAX);
        notificationBuilder.setContentIntent(pendingIntent);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            final NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.wifi_logo_icon_82503));
            notificationBuilder.setStyle(bigPictureStyle);
            NotificationManagerCompat.from(MainActivity.this).notify(1, notificationBuilder.build());
        }else{
            notificationBuilder.setAutoCancel(false);
            Toast.makeText(MainActivity.this,"Check Connection",Toast.LENGTH_LONG).show();
            final NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.no_wifi));
            notificationBuilder.setStyle(bigPictureStyle);
            NotificationManagerCompat.from(MainActivity.this).notify(1,notificationBuilder.build());
        }
    }


}
