package com.itt.ceatm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

public class Activity_Administrador extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

//        cambiar = (Button) findViewById(R.id.btn_cambiar);
        //        cambiar = (Button) findViewById(R.id.btn_cambiar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_admin);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new frg_administrador_atletas()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bnav_administrador_atletas:
                            selectedFragment = new frg_administrador_atletas();
                            break;
                        case R.id.bnav_administrador_entrenadores:
                            selectedFragment = new frg_atleta_estadisticas();
                            break;
                        case R.id.bnav_administrador_competencias:
                            selectedFragment = new frg_atleta_competencias();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void int_agregar(View view) {
        //        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        Intent agregar = new Intent(Activity_Administrador.this, Activity_Admin_Agregar_Atletas.class);
        startActivity(agregar);
    }
}
