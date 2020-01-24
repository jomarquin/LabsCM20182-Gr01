package co.edu.udea.compumovil.gr01_20182.lab4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    Button register;
    Button login;
    Button login_google;
    //Button exit;  //botón para salir de la aplicación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide(); //Hide the ActionBar


        SharedPreferences preferences = this.getApplication().getSharedPreferences("credentials", getApplicationContext().MODE_PRIVATE);
        String usrSesion = preferences.getString("user_sesion", "No existe la información");

        Toast.makeText(getApplicationContext(), usrSesion,
                Toast.LENGTH_SHORT).show();

        if (usrSesion.equals("user") || usrSesion.equals("google")){
            Intent intent=new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
        }

        register = findViewById(R.id.id_BtnFacebook);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        login = findViewById(R.id.id_BtnMail);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        login_google = findViewById(R.id.id_BtnGoogle);
        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_google = new Intent(StartActivity.this, LoginGoogleActivity.class);
                startActivity(login_google);
            }
        });

        //exit = findViewById(R.id.id_BtnExit);
        /*exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });**/
    }

}
