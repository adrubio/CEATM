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


    public static final String lusuarios = "lusuarios";
    //String usUsuario;
    //Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

//        cambiar = (Button) findViewById(R.id.btn_cambiar);
        //        cambiar = (Button) findViewById(R.id.btn_cambiar);

        //usUsuario = getIntent().getStringExtra("lusuarios");
        //bundle.putString("USUARIO",usUsuario);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_admin);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frg_admin,new frg_administrador_atletas()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bnav_administrador_atletas:
                            //bundle.putString("USUARIO",usUsuario);
                            selectedFragment = new frg_administrador_atletas();
                            break;
                        case R.id.bnav_administrador_entrenadores:
                            //bundle.putString("USUARIO",usUsuario);
                            selectedFragment = new frg_atleta_estadisticas();
                            break;
                        case R.id.bnav_administrador_competencias:
                            //bundle.putString("USUARIO",usUsuario);
                            selectedFragment = new frg_admin_competencias();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frg_admin,
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
