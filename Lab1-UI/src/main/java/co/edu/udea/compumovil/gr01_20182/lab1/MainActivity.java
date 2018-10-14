package co.edu.udea.compumovil.gr01_20182.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button comidas;
    Button bebidas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comidas = findViewById(R.id.id_comidas);
        comidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comidas = new Intent(MainActivity.this, activity_comidas.class);
                startActivity(comidas);
            }
        });

        bebidas = findViewById(R.id.id_bebidas);
        bebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bebidas = new Intent(MainActivity.this, activity_bebidas.class);
                startActivity(bebidas);
            }
        });
    }
}
