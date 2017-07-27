package com.books.share.smartbookshelf.lib.fcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import com.books.share.smartbookshelf.lib.conf.Conf;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by limjuhyun on 21/07/2017.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        final SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                Conf.APPLICATION_ID, Context.MODE_PRIVATE);

        prefs.edit().putString("push_token", token).apply();
    }
}
