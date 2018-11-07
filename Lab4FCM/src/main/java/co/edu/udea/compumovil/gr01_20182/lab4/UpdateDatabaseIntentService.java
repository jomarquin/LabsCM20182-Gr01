package co.edu.udea.compumovil.gr01_20182.lab4;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class UpdateDatabaseIntentService extends IntentService  {

    private static final String TAG = UpdateDatabaseIntentService.class.getSimpleName();


    //Constructor
    public UpdateDatabaseIntentService() {
        super("UpdateDatabaseIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        handleActionRun();
    }

    private void handleActionRun() {

        try {
            // Se construye la notificación
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setContentTitle("Actualizando Bases de Datos")
                    .setContentText("Procesando...");

            // Bucle de simulación
            for (int i = 1; i <= 15; i++) {
                Log.d(TAG, i + ""); // Logueo
                // Poner en primer plano
                builder.setProgress(15, i, false);
                startForeground(1, builder.build());
                // Retardo de 1 segundo en la iteración
                Thread.sleep(1000);
            }
            // Quitar de primer plano
            stopForeground(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     private void loadWebServiceFoods() {

     progress = new ProgressDialog(getApplicationContext());
     progress.setMessage("Consultando...");
     progress.show();
     String url = "http://www.mocky.io/v2/5bb69bd22e00007b00683715";
     jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, this, this);
     request.add(jsonObjectRequest);

     }

     @Override
     public void onResponse(JSONObject response) {
     Food food;
     JSONArray json=response.optJSONArray("foods");


     try {
     for (int i=0;i<json.length();i++){
     food=new Food();
     JSONObject jsonObject;
     jsonObject=json.getJSONObject(i);
     food.setName(jsonObject.optString("name"));
     food.setType("Tipo plato: "+(jsonObject.optString("type")));
     food.setPrice("Precio: $"+(jsonObject.optString("price")));
     food.setTime("Tiempo de preparación: "+(jsonObject.optString("time")+" min."));
     ListFood.add(food);
     }
     progress.hide();
     FoodAdapter foodAdapter = new FoodAdapter(ListFood);
     recyclerListPlates.setAdapter(foodAdapter);

     }catch (JSONException e){
     e.printStackTrace();
     Toast.makeText(getContext(), "No se puede conectar con el servidor "+response, Toast.LENGTH_LONG).show();
     progress.hide();
     }

     }

     @Override
     public void onErrorResponse(VolleyError error) {
     Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
     System.out.println();
     Log.d("ERROR: ", error.toString());
     progress.hide();
     }**/




}
