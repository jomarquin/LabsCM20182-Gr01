package co.edu.udea.compumovil.gr01_20182.lab2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20182.lab2.Utilities.Utilities;

public class LoginActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    Button login;
    EditText fieldName, fieldEmail, fieldPassword;
    ImageView imageProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conn = new ConexionSQLiteHelper(this, "bd_maxipollo", null, 1);
        fieldEmail = findViewById(R.id.id_editText_loginEmail);
        fieldName = findViewById(R.id.id_editText_loginName);
        fieldPassword = findViewById(R.id.id_editText_loginPass);

        login = findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginUser = loginUsers();
                if (loginUser){
                    savePreferences();
                    RegisterInformation();
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                }

            }
            //String[] param ={fieldEmail.getText().toString()};
            private boolean loginUsers() {
                boolean log=false;
                SQLiteDatabase db = conn.getReadableDatabase();
                String[] param ={fieldEmail.getText().toString()};
                String[] fields = {Utilities.FIELD_NAME, Utilities.FIELD_PASSWORD};
                try {
                    Cursor cursor = db.query(Utilities.TABLE_USERS, fields, Utilities.FIELD_EMAIL+"=?",param,null,null,null);
                    cursor.moveToFirst();
                    fieldName.setText(cursor.getString(0));
                    cursor.close();
                    log=true;
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No existe usuario con este correo", Toast.LENGTH_LONG).show();
                    cleanFields();
                }
                return log;
            }

            private void cleanFields() {
                fieldEmail.setText("");
                fieldPassword.setText("");
            }
        });
    }

    private void RegisterInformation() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_maxipollo", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        //Creacion de bebidas
        ContentValues values = new ContentValues();
        values.put(Utilities.FIELD_D_NAME, "Jugo Natural");
        values.put(Utilities.FIELD_D_PRICE, "3500");
        long idResult = db.insert(Utilities.TABLE_DRINKS, Utilities.FIELD_D_NAME, values);
        Toast.makeText(getApplicationContext(), "Bebida Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_D_NAME, "Limonada de Coco");
        values.put(Utilities.FIELD_D_PRICE, "5500");
        idResult = db.insert(Utilities.TABLE_DRINKS, Utilities.FIELD_D_NAME, values);
        Toast.makeText(getApplicationContext(), "Bebida Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_D_NAME, "Granizado");
        values.put(Utilities.FIELD_D_PRICE, "4000");
        idResult = db.insert(Utilities.TABLE_DRINKS, Utilities.FIELD_D_NAME, values);
        Toast.makeText(getApplicationContext(), "Bebida Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_D_NAME, "Michelada");
        values.put(Utilities.FIELD_D_PRICE, "7000");
        idResult = db.insert(Utilities.TABLE_DRINKS, Utilities.FIELD_D_NAME, values);
        Toast.makeText(getApplicationContext(), "Bebida Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_D_NAME, "Gaseosa");
        values.put(Utilities.FIELD_D_PRICE, "3000");
        idResult = db.insert(Utilities.TABLE_DRINKS, Utilities.FIELD_D_NAME, values);
        Toast.makeText(getApplicationContext(), "Bebida Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        //Creacion de platos
        values.put(Utilities.FIELD_P_NAME, "Pollo Asado");
        values.put(Utilities.FIELD_P_TYPE, "Plato Fuerte");
        values.put(Utilities.FIELD_P_PRICE, "15000");
        values.put(Utilities.FIELD_P_TIME, "15");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_P_NAME, "Pollo Broaster");
        values.put(Utilities.FIELD_P_TYPE, "Plato Fuerte");
        values.put(Utilities.FIELD_P_PRICE, "20000");
        values.put(Utilities.FIELD_P_TIME, "15");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_P_NAME, "Pollo Apanado");
        values.put(Utilities.FIELD_P_TYPE, "Plato Fuerte");
        values.put(Utilities.FIELD_P_PRICE, "18000");
        values.put(Utilities.FIELD_P_TIME, "10");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_P_NAME, "Nuggets De Pollo");
        values.put(Utilities.FIELD_P_TYPE, "Entrada");
        values.put(Utilities.FIELD_P_PRICE, "13000");
        values.put(Utilities.FIELD_P_TIME, "15");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_P_NAME, "Pollo a la Plancha");
        values.put(Utilities.FIELD_P_TYPE, "Plato Fuerte");
        values.put(Utilities.FIELD_P_PRICE, "18000");
        values.put(Utilities.FIELD_P_TIME, "15");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_P_NAME, "Pinchos de Pollo");
        values.put(Utilities.FIELD_P_TYPE, "Entrada");
        values.put(Utilities.FIELD_P_PRICE, "17000");
        values.put(Utilities.FIELD_P_TIME, "12");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_P_NAME, "Bandeja Paisa");
        values.put(Utilities.FIELD_P_TYPE, "Plato Fuerte");
        values.put(Utilities.FIELD_P_PRICE, "18000");
        values.put(Utilities.FIELD_P_TIME, "20");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        values.put(Utilities.FIELD_P_NAME, "Consome");
        values.put(Utilities.FIELD_P_TYPE, "Plato Fuerte");
        values.put(Utilities.FIELD_P_PRICE, "10000");
        values.put(Utilities.FIELD_P_TIME, "12");
        idResult = db.insert(Utilities.TABLE_PLATES, Utilities.FIELD_P_NAME, values);
        Toast.makeText(getApplicationContext(), "Plato Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        values.clear();

        db.close();

    }


    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String userName = fieldName.getText().toString();
        String userEmail = fieldEmail.getText().toString();
        String userPass = fieldPassword.getText().toString();
        //String userImage = imageProfile.
        //Toast.makeText(getApplicationContext(), "Ruta imagen: "+userImage, Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("uName", userName);
        editor.putString("uEmail", userEmail);
        editor.putString("uPass", userPass);
        //editor.putString("uImage", userImage);

        //plate.setpImage(R.drawable.bandeja_paisa);

        editor.commit();
    }


}
