package co.edu.udea.compumovil.gr01_20182.lab4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class UpdateReceiver extends BroadcastReceiver {

    private final String TAG = "UpdateReceiver";
    private Intent intentBoot;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "INTENT RECEIVED");
        Toast.makeText(context, "Actualizando Database...", Toast.LENGTH_SHORT).show();
        intentBoot = new Intent(context.getApplicationContext(), UpdateDatabaseIntentService.class);
        context.startService(intentBoot);
    }
}
