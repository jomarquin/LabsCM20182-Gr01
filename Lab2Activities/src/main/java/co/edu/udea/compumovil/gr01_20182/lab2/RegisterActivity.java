package co.edu.udea.compumovil.gr01_20182.lab2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20182.lab2.Utilities.Utilities;

public class RegisterActivity extends AppCompatActivity {

    EditText fieldName, fieldEmail, fieldPassword;
    ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fieldName = findViewById(R.id.id_editText_name);
        fieldEmail = findViewById(R.id.id_editText_email);
        fieldPassword = findViewById(R.id.id_editText_password);
        imageProfile = findViewById(R.id.id_image_profile);
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

    private void loadImage() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione una aplicación"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path=data.getData();
            imageProfile.setImageURI(path);
        }
    }

    private void registerUsers() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_maxipollo", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilities.FIELD_NAME, fieldName.getText().toString());
        values.put(Utilities.FIELD_EMAIL, fieldEmail.getText().toString());
        values.put(Utilities.FIELD_PASSWORD, fieldPassword.getText().toString());

        long idResult = db.insert(Utilities.TABLE_USERS, Utilities.FIELD_NAME, values);
        Toast.makeText(getApplicationContext(), "Usuario Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        db.close();
        fieldName.setText("");
        fieldEmail.setText("");
        fieldPassword.setText("");
    }
}
