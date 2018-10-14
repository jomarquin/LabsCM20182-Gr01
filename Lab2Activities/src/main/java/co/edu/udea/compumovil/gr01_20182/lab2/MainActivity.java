package co.edu.udea.compumovil.gr01_20182.lab2;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;

import co.edu.udea.compumovil.gr01_20182.lab2.Entities.Drink;
import co.edu.udea.compumovil.gr01_20182.lab2.Entities.Plate;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AboutFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener,
        ComidasFragment.OnFragmentInteractionListener, ListplatesFragment.OnFragmentInteractionListener,
        ListdrinksFragment.OnFragmentInteractionListener, PreferencesFragment.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener, BebidasFragment.OnFragmentInteractionListener,
        IComunicaFragments, DetailplateFragment.OnFragmentInteractionListener, DetaildrinkFragment.OnFragmentInteractionListener,
        EditprofileFragment.OnFragmentInteractionListener{

    Fragment menuFragment;
    ListplatesFragment listplatesFragment;
    DetailplateFragment detailplateFragment;
    DetaildrinkFragment detaildrinkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, menuFragment)
                .addToBackStack(null).commit();

        /**if (findViewById(R.id.content_main)!=null){
            if (savedInstanceState!=null){
                return;
            }
        }*/


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
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
        } else if (id == R.id.nav_closeSession) {

        } else if (id == R.id.nav_about) {
            miFragment=new AboutFragment();
            fragmentSelected=true;
        } else if (id == R.id.nav_menu) {
            miFragment=new MenuFragment();
            fragmentSelected=true;
        }


            if (fragmentSelected==true){
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, miFragment).addToBackStack(null).commit();

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
    public void sendDrink(Drink drink) {
        detaildrinkFragment = new DetaildrinkFragment();
        Bundle bundleSend = new Bundle();
        bundleSend.putSerializable("objeto", drink);
        detaildrinkFragment.setArguments(bundleSend);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, detaildrinkFragment).addToBackStack(null).commit();
    }
}
