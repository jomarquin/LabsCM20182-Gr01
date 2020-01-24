package co.edu.udea.compumovil.gr01_20182.lab4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Usuario;

public class RegisterActivity extends AppCompatActivity {

    EditText  fieldName, fieldEmail, fieldPassword;
    ImageView imageProfile;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseRef = ref.child("usuarios");
    StorageReference storageRef;
    String path_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fieldName = findViewById(R.id.id_editText_name);
        fieldEmail = findViewById(R.id.id_editText_email);
        fieldPassword = findViewById(R.id.id_editText_password);
        imageProfile = findViewById(R.id.id_image_profile);

        storageRef = FirebaseStorage.getInstance().getReference();
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.id_Btn_Register:
                registerUsers();
                break;
            case R.id.id_load_image:
                loadImage();
                break;
        }
    }

    /**
     * Método para cargar imagen y obtener su url
     */
    private void loadImage() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");//si hay problemas para cargar algunas imagenes colocar "image/*"
        startActivityForResult(intent.createChooser(intent, "Seleccione una aplicación"), 10);
    }

    /**
     * Acá se carga la imagen del usuario y se toma la url de la imagen en la base de datos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10 && resultCode==RESULT_OK){
            Uri uri=data.getData();
            imageProfile.setImageURI(uri);
            StorageReference filePath = storageRef.child("users")
                    .child(uri.getLastPathSegment()); //Referenciamos donde quedará guardada nuestra imagen
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(),"Imagen Cargada", Toast.LENGTH_SHORT).show();
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    path_uri = urlTask.getResult().toString();
                    Toast.makeText(getApplicationContext(),"URL: "+path_uri, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * Este método permite registrar un usuario
     */
    private void registerUsers() {
        //getting the values to save
        String name = fieldName.getText().toString().trim();
        String email = fieldEmail.getText().toString().trim();
        String pass = fieldPassword.getText().toString().trim();
        String image = path_uri;

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {

                String id = databaseRef.push().getKey();
                Usuario user = new Usuario(email, name, pass, image);

                databaseRef.child(id).setValue(user);

                fieldName.setText("");
                fieldEmail.setText("");
                fieldPassword.setText("");
                imageProfile.setImageResource(R.drawable.sin_imagen);

                Toast.makeText(this, "Usuario Registrado Correctamente", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Por favor complete la información", Toast.LENGTH_LONG).show();
            }
        }



}
