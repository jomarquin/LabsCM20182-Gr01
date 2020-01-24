package co.edu.udea.compumovil.gr01_20182.lab4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Usuario;
import co.edu.udea.compumovil.gr01_20182.lab4.Utilities.Utilities;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button register_email;
    EditText fieldName, fieldEmail, fieldPassword;
    private static final String TAG = "MyActivity";

    ArrayList<Usuario> ListUser;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseRef = ref.child("usuarios");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide(); //Hide the ActionBar

        fieldEmail = findViewById(R.id.id_editText_loginEmail);
        fieldPassword = findViewById(R.id.id_editText_loginPass);

        ListUser=new ArrayList<>();

        register_email = findViewById(R.id.btn_register_email);
        register_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        login = findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginUser=false;
                Iterator<Usuario> it = ListUser.iterator();
                String email = fieldEmail.getText().toString();
                String pass = fieldPassword.getText().toString();
                while (it.hasNext()){
                    Usuario usr = it.next();
                    if (email.equals(usr.getEmail()) && pass.equals(usr.getPassword())){
                        loginUser=true;
                        Utilities.USER_NAME = usr.getName();
                        Utilities.USER_EMAIL = usr.getEmail();
                        Utilities.USER_IMAGE = usr.getImage();
                        Utilities.USER_PROFILE = "user";
                        SharedPreferences preferences = getSharedPreferences("credentials", getApplicationContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("user_sesion", "user");
                        editor.commit();
                    }
                    //Log.i(TAG, email+" prueba");
                }
                if (loginUser){
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                    //Aca cambié para cuando se loguee muestre las tabs
                    /**Fragment miFragment = new ContainerswipeFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, miFragment)
                            .addToBackStack(null).commit();*/
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto",
                            Toast.LENGTH_SHORT).show();
                    cleanFields();
                }
            }

            private void cleanFields() {
                fieldEmail.setText("");
                fieldPassword.setText("");
            }
        });
    }

    /**
     * Acá obtengo la lista de usuarios de la base de datos firebase para comparar el correo y contraseña
     * ingresados por el usuario y permitir o no el acceso
     */
    @Override
    protected void onStart() {
        super.onStart();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ListUser.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Usuario user = postSnapshot.getValue(Usuario.class);
                    ListUser.add(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No se pudo obtener información ", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
