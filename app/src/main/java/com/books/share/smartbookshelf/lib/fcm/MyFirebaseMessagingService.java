package com.books.share.smartbookshelf.lib.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.ui.MainActivity;
import com.books.share.smartbookshelf.ui.NewBookActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by limjuhyun on 21/07/2017.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.i(TAG, "From: " + remoteMessage.getFrom());


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getData());
        }

    }

    private void sendNotification(String messageBody, Map<String, String> messageData) {
        Intent intent;

        if(messageData.get("type")!= null && messageData.get("type").toString().equals("1")) {
            Log.d(TAG, "kkkk");
            intent = new Intent(this, NewBookActivity.class);
            intent.putExtra("keyword", messageData.get("keyword"));
        } else {
            Log.d(TAG, "nnnn");
            intent = new Intent(this, MainActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("SmartBookshelf")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(new long[] {500, 100})
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(99, notificationBuilder.build());
    }

}
