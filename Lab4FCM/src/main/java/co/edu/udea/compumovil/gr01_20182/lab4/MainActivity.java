package co.edu.udea.compumovil.gr01_20182.lab4;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Drink;
import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Food;
import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Plate;
import co.edu.udea.compumovil.gr01_20182.lab4.Utilities.Utilities;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AboutFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener,
        ComidasFragment.OnFragmentInteractionListener, ListplatesFragment.OnFragmentInteractionListener,
        ListdrinksFragment.OnFragmentInteractionListener, PreferencesFragment.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener, BebidasFragment.OnFragmentInteractionListener,
        IComunicaFragments, DetailplateFragment.OnFragmentInteractionListener, DetaildrinkFragment.OnFragmentInteractionListener,
        EditprofileFragment.OnFragmentInteractionListener, SearchView.OnQueryTextListener,
        SearchDrinkFragment.OnFragmentInteractionListener, SearchFoodFragment.OnFragmentInteractionListener, ContainerswipeFragment.OnFragmentInteractionListener {

    /**
     * Johan Martinez
     * Codigo Lab4
     */


    Fragment menuFragment;
    DetailplateFragment detailplateFragment;
    DetaildrinkFragment detaildrinkFragment;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        /**Bandera para mantener la misma vista al rotar el dispositivo*/
        if (Utilities.validaPantalla){
             Fragment miFragment = new ContainerswipeFragment();
             getSupportFragmentManager().beginTransaction()
             .replace(R.id.content_main, miFragment)
             .addToBackStack(null).commit();
             Utilities.validaPantalla=false;
         }


        /**
        //initFoods();
        //initDrinks();
        Toast.makeText(getApplicationContext(), "Actualizando Database...", Toast.LENGTH_SHORT).show();
        intent = new Intent(this, UpdateDatabaseIntentService.class);
        //startService(intent);*/

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setBackgroundColor(getResources().getColor(R.color.colorPrimarySoft)); //color de fondo del nav_drawer
        navigationView.setItemIconTintList(null);

    }

    /**
     * Este método se encarga de llenar la tabla 'foods' con la información de las comidas
     */
    private void initFoods(){
        if (cantRowsFoods()==0){
            String[] text= readFileFoods();
            ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this, "bd_maxipollo", null, 1 );
            SQLiteDatabase db = conexionSQLiteHelper.getWritableDatabase();
            db.beginTransaction();

            for (int i=0;i<text.length;i++){
                String[] line = text[i].split(";");
                ContentValues values = new ContentValues();
                values.put(Utilities.FIELD_F_NAME, line[0]);
                values.put(Utilities.FIELD_F_DESCRIPTION, line[1]);
                values.put(Utilities.FIELD_F_IMAGE, line[2]);
                values.put(Utilities.FIELD_F_TYPE, line[3]);
                values.put(Utilities.FIELD_F_TIME, line[4]);
                values.put(Utilities.FIELD_F_PRICE, line[5]);
                db.insert(Utilities.TABLE_FOODS, null, values);
            }
            Toast.makeText(getApplicationContext(), "Registros insertados: "+text.length, Toast.LENGTH_LONG).show();
            db.setTransactionSuccessful();
            db.endTransaction();
        }else{
            Toast.makeText(getApplicationContext(), "Información de comidas actualizada!!", Toast.LENGTH_LONG).show();
        }
    }//Fin initFoods

    /**
     * Este método se encarga de llenar la tabla 'drinks' con la información de las bebidas
     */
    private void initDrinks(){
        if (cantRowsDrinks()==0){
            String[] text= readFileDrinks();
            ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this, "bd_maxipollo", null, 1 );
            SQLiteDatabase db = conexionSQLiteHelper.getWritableDatabase();
            db.beginTransaction();

            for (int i=0;i<text.length;i++){
                String[] line = text[i].split(";");
                ContentValues values = new ContentValues();
                values.put(Utilities.FIELD_D_NAME, line[0]);
                values.put(Utilities.FIELD_D_DESCRIPTION, line[1]);
                values.put(Utilities.FIELD_D_PRICE, line[2]);
                values.put(Utilities.FIELD_D_IMAGE, line[3]);
                db.insert(Utilities.TABLE_DRINKS, null, values);
            }
            Toast.makeText(getApplicationContext(), "Registros insertados: "+text.length, Toast.LENGTH_LONG).show();
            db.setTransactionSuccessful();
            db.endTransaction();
        }else{
            Toast.makeText(getApplicationContext(), "Información de bebidas actualizada!!", Toast.LENGTH_LONG).show();
        }
    }//Fin initDrinks

    /**
     * Este método se encarga de contar cuantos registros hay en la tabla 'foods' para determinar si se debe cargar
     * la información de comidas
     * @return retorna un long con la cantidad de registros que tiene la tabla foods
     */
    private long cantRowsFoods(){
        ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this, "bd_maxipollo", null, 1 );
        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();
        long cn = DatabaseUtils.queryNumEntries(db, Utilities.TABLE_FOODS);
        db.close();
        return cn;
    }

    /**
     * Este método se encarga de contar cuantos registros hay en la tabla 'drinks' para determinar si se debe cargar
     * la información de bebidas
     * @return retorna un long con la cantidad de registros que tiene la tabla drinks
     */
    private long cantRowsDrinks(){
        ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this, "bd_maxipollo", null, 1 );
        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();
        long cn = DatabaseUtils.queryNumEntries(db, Utilities.TABLE_DRINKS);
        db.close();
        return cn;
    }

    /**
     * Este método lee el archivo donde esta la información a cargar en la tabla 'foods'
     * @return Retorna un arreglo string con la información de las comidas
     */
    private String[] readFileFoods(){
        InputStream inputStream = getResources().openRawResource(R.raw.foods);
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            int i = inputStream.read();
            while (i != -1){
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString().split("\n");
    }

    /**
     * Este método lee el archivo donde esta la información a cargar en la tabla 'drinks'
     * @return Retorna un arreglo string con la información de las bebidas
     */
    private String[] readFileDrinks(){
        InputStream inputStream = getResources().openRawResource(R.raw.drinks);
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            int i = inputStream.read();
            while (i != -1){
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString().split("\n");
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSelected=false;

        if (id == R.id.nav_plates) {
            miFragment = new ListplatesFragment();
            fragmentSelected = true;
        } else if (id == R.id.nav_drink) {
            miFragment = new ListdrinksFragment();
            fragmentSelected = true;
        } else if (id == R.id.nav_profile) {
            miFragment = new ProfileFragment();
            fragmentSelected = true;
        } else if (id == R.id.nav_configurations) {
            miFragment = new PreferencesFragment();
            fragmentSelected = true;
        } else if (id == R.id.nav_searchFood) {
            miFragment = new SearchFoodFragment();
            fragmentSelected = true;
        }else if (id == R.id.nav_searchDrink) {
            miFragment = new SearchDrinkFragment();
            fragmentSelected = true;
        }else if (id == R.id.nav_closeSession) {
            SharedPreferences preferences1 = this.getApplication().getSharedPreferences("credentials", getApplicationContext().MODE_PRIVATE);
            String usrSesion = preferences1.getString("user_sesion", "No existe la información");
            if (usrSesion.equals("user")){
                Utilities.USER_PROFILE="close";
                SharedPreferences preferences = getSharedPreferences("credentials", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("user_sesion", "close");
                editor.commit();
                Intent login = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(login);
                finish();
            }else if(usrSesion.equals("google")){
                Intent login = new Intent(getApplicationContext(), ProfileGoogleActivity.class);
                startActivity(login);
            }

        } else if (id == R.id.nav_about) {
            miFragment=new AboutFragment();
            fragmentSelected=true;
        } else if (id == R.id.nav_menu) {
            //miFragment=new MenuFragment();
            miFragment=new ContainerswipeFragment();
            fragmentSelected=true;
        }
            if (fragmentSelected==true){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, miFragment).addToBackStack(null).commit();
            }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void sendPlate(Plate plate) {
        detailplateFragment = new DetailplateFragment();
        Bundle bundleSend = new Bundle();
        bundleSend.putSerializable("objeto", plate);
        detailplateFragment.setArguments(bundleSend);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, detailplateFragment).addToBackStack(null).commit();
    }

    @Override
    public void sendFood(Food food) {
        detailplateFragment = new DetailplateFragment();
        Bundle bundleSend = new Bundle();
        bundleSend.putSerializable("objeto", food);
        detailplateFragment.setArguments(bundleSend);

        //Cargar el fragment en el activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, detailplateFragment).addToBackStack(null).commit();
    }

    @Override
    public void sendDrink(Drink drink) {
        detaildrinkFragment = new DetaildrinkFragment();
        Bundle bundleSend = new Bundle();
        bundleSend.putSerializable("objeto", drink);
        detaildrinkFragment.setArguments(bundleSend);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, detaildrinkFragment).addToBackStack(null).commit();
    }




    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
