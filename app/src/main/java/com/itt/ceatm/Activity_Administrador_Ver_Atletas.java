package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import static com.itt.ceatm.cl_lista_atletas_adaptador.NOMBRE;
import static com.itt.ceatm.cl_lista_atletas_adaptador.DEPORTE;

public class Activity_Administrador_Ver_Atletas extends AppCompatActivity {

    private  String nombre;
    private  String deporte;
    TextView tvNombre;
    TextView tvDeporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_ver_atleta);

//        cambiar = (Button) findViewById(R.id.btn_cambiar);
        //        cambiar = (Button) findViewById(R.id.btn_cambiar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_atletas);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_visualizar_atleta,new administrador_visualizar_atletas_perfil()).commit();


    }

    @Override
    protected void onStart() {
        super.onStart();
        nombre = getIntent().getStringExtra(NOMBRE);
        deporte = getIntent().getStringExtra(DEPORTE);

        tvNombre = (TextView) findViewById(R.id.tv_atleta_visualizar);
        tvNombre.setText(nombre);
        tvDeporte = (TextView) findViewById(R.id.tv_deporte_visualizar);
        tvDeporte.setText(deporte);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_administrador_atletas_estadisticas:
                            selectedFragment = new administrador_visualizar_atletas_estadisticas();
                            break;
                        case R.id.nav_administrador_atletas_perfil:
                            selectedFragment = new administrador_visualizar_atletas_perfil();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_visualizar_atleta,
                            selectedFragment).commit();

                    return true;
                }
            };

}
