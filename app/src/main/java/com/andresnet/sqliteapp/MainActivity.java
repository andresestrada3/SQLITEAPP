package com.andresnet.sqliteapp;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Comunicador {
    private android.support.v4.app.FragmentManager fm;
    private FragmentTransaction ft;
    private BaseSQLite baseSQLite;
    private Bundle ag = new Bundle();
    private SQLiteDatabase dbPeluche;
    private ContentValues dataDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        AgregarFragment agregarFragment = new AgregarFragment();
        ft.add(R.id.frame, agregarFragment).commit();
        baseSQLite = new BaseSQLite(
                this,
                "pelucheList",
                null,
                1);
        dbPeluche = baseSQLite.getWritableDatabase();

    }
    @Override
    public void envioDatos(int id1, String name1, int can1, double pre1) {
        dataDB = new ContentValues();
        dataDB.put("idd",id1);
        dataDB.put("nombre",name1);
        dataDB.put("cantidad",can1);
        dataDB.put("precio",pre1);
        dbPeluche.insert("inventario",null,dataDB);
    }

    @Override
    public void envioDatosBus(String name2) {
        Cursor cursor = dbPeluche.rawQuery(
                "SELECT * FROM inventario WHERE nombre='"+name2+"'",
                null
        );
        if (cursor.moveToFirst()){
            ag.putString("nombre",name2);
            ag.putInt("id",cursor.getInt(1));
            ag.putInt("cantidad",cursor.getInt(2));
            ag.putDouble("precio",cursor.getDouble(3));

        }else {
            Toast.makeText(this, "Peluche no encontrado", Toast.LENGTH_SHORT).show();
        }



        ag.putString("nombre", name2);
        BuscarFragment buscarFragment = new BuscarFragment();
        buscarFragment.setArguments(ag);
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, buscarFragment).commit();

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ft=fm.beginTransaction();


        if (id == R.id.fAgregar) {
            // Handle the camera action
            AgregarFragment agregarFragment = new AgregarFragment();
            ft.replace(R.id.frame,agregarFragment).commit();

        } else if (id == R.id.fBuscar) {
            BuscarFragment buscarFragment = new BuscarFragment();
            ft.replace(R.id.frame, buscarFragment).commit();

        } else if (id == R.id.fEliminar) {
            EliminarFragment eliminarFragment = new EliminarFragment();
            ft.replace(R.id.frame,eliminarFragment).commit();

        } else if (id == R.id.fVerInventario) {
            VerInventarioFragment verInventarioFragment = new VerInventarioFragment();
            ft.replace(R.id.frame,verInventarioFragment).commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
