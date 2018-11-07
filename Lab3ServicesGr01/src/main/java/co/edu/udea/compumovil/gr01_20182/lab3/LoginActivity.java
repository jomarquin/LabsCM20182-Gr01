package co.edu.udea.compumovil.gr01_20182.lab3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20182.lab3.Utilities.Utilities;

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
                    Toast.makeText(getApplicationContext(), "Email o Password incorrecto...", Toast.LENGTH_LONG).show();
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

        editor.commit();
    }


}
