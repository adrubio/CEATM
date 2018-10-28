package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class Activity_Atleta extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atletas);

//        cambiar = (Button) findViewById(R.id.btn_cambiar);
        //        cambiar = (Button) findViewById(R.id.btn_cambiar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_atletas);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new frg_atleta_perfil()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bnav_atleta_perfil:
                            selectedFragment = new frg_atleta_perfil();
                            break;
                        case R.id.bnav_atleta_estadisticas:
                            selectedFragment = new frg_atleta_estadisticas();
                            break;
                        case R.id.bnav_atleta_competencias:
                            selectedFragment = new frg_atleta_competencias();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


    public void onClick(View view) {
        //Para que mate la actividad y no se ponga en espera
        finish();
    }
}
