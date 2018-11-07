package co.edu.udea.compumovil.gr01_20182.lab4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class UpdateDatabaseService extends Service {

    private final String TAG = "UpdateDatabaseService";
    //private Counter thread;
    private int currentId;

    public UpdateDatabaseService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.v(TAG, "onStartCommand");

        currentId = startId;
        Log.d(TAG, "Service started");



        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //thread.setFlag(false);
        Toast.makeText(this, "Service Destroyed ID:"+ currentId, Toast.LENGTH_LONG).show();
        //Log.v(TAG, "onDestroy");
        Log.v(TAG, "Service Destroyed ID:"+ currentId);
    }
}
