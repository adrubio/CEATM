package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        cambiar = (Button) findViewById(R.id.btn_cambiar);
        //        cambiar = (Button) findViewById(R.id.btn_cambiar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_admin_atletas);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new atleta_perfil()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_atleta_perfil:
                            selectedFragment = new atleta_perfil();
                            break;
                        case R.id.nav_atleta_estadisticas:
                            selectedFragment = new atleta_estadisticas();
                            break;
                        case R.id.nav_atleta_competencias:
                            selectedFragment = new administrador_atletas();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}
