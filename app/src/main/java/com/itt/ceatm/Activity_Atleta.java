package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

public class Activity_Atleta extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener{

    TextView tv_atletas_rango_hasta;
    TextView tv_atletas_rango_desde;

    public static final String lusuarios = "lusuarios";
    String usUsuario;
    Bundle bundle = new Bundle();
    frg_atleta_perfil perfil = new frg_atleta_perfil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atletas);

//        cambiar = (Button) findViewById(R.id.btn_cambiar);
        //        cambiar = (Button) findViewById(R.id.btn_cambiar);

        usUsuario = getIntent().getStringExtra("lusuarios");
        bundle.putString("USUARIO",usUsuario);
        perfil.setArguments(bundle);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_admin_ver_atletas);
        tv_atletas_rango_hasta = findViewById(R.id.tv_Atletas_Rango_Hasta);
        tv_atletas_rango_desde = findViewById(R.id.tv_Atletas_Rango_Desde);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,perfil).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bnav_atleta_perfil:
                            bundle.putString("USUARIO",usUsuario);
                            selectedFragment = new frg_atleta_perfil();
                            break;
                        case R.id.bnav_atleta_estadisticas:
                            bundle.putString("USUARIO",usUsuario);
                            selectedFragment = new frg_atleta_estadisticas();
                            break;
                        case R.id.bnav_atleta_competencias:
                            bundle.putString("USUARIO",usUsuario);
                            selectedFragment = new frg_atleta_competencias();
                            break;
                    }
                    selectedFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


    public void onClick(View view) {
        //Para que mate la actividad y no se ponga en espera
//        switch (view.getId()){
//            case R.id.btn_Atleta_Estadisticas_Desde:
//                Calendar now = Calendar.getInstance();
//                FragmentManager fm = getFragmentManager();
//
//                DatePickerDialog fecha_dialogo_de = DatePickerDialog.newInstance( Activity_Atleta.this,
//
//                        //Se establece que se muestra un promedio de edad y se pone en medio en mes y el dia para que sea mas facil ir a los extremos (Enero y Diciembre)
//                        now.get(Calendar.YEAR), // Initial year selection
//                        now.get(Calendar.MONTH), // Initial month selection
//                        now.get(Calendar.DAY_OF_MONTH)// Inital day selection
//                );
//
//                fecha_dialogo_de.showYearPickerFirst(true);
//                fecha_dialogo_de.setTitle("¿Desde qué fecha?");
//                fecha_dialogo_de.show(getFragmentManager(), "Fecha Desde");;
//                fecha_dialogo_de.setAccentColor(getResources().getColor(R.color.colorPrimario));
//
//                //Fecha Minima
//                Calendar minimo = Calendar.getInstance();
//                minimo.set(2016,0,1);
//                fecha_dialogo_de.setMinDate(minimo);
//
//                //Fecha de nacimiento maxima
////                Calendar maximo = Calendar.getInstance();
//                fecha_dialogo_de.setMaxDate(Calendar.getInstance());
//
//                break;
//
//            case R.id.btn_Atleta_Estadisticas_Hasta:
//                Calendar now2 = Calendar.getInstance();
//
//
//                DatePickerDialog fecha_dialogo_a = DatePickerDialog.newInstance( Activity_Atleta.this,
//
//                        //Se establece que se muestra un promedio de edad y se pone en medio en mes y el dia para que sea mas facil ir a los extremos (Enero y Diciembre)
//                        now2.get(Calendar.YEAR), // Initial year selection
//                        now2.get(Calendar.MONTH), // Initial month selection
//                        now2.get(Calendar.DAY_OF_MONTH)// Inital day selection
//                );
//
//                fecha_dialogo_a.showYearPickerFirst(true);
//                fecha_dialogo_a.setTitle("¿Desde qué fecha?");
//                fecha_dialogo_a.show(getFragmentManager(), "Fecha Desde");;
//                fecha_dialogo_a.setAccentColor(getResources().getColor(R.color.colorPrimario));
//
//                //Fecha Minima
//                Calendar minimo2 = Calendar.getInstance();
//                minimo2.set(2016,0,1);
//                fecha_dialogo_a.setMinDate(minimo2);
//
//                //Fecha de nacimiento maxima
////                Calendar maximo = Calendar.getInstance();
//                fecha_dialogo_a.setMaxDate(Calendar.getInstance());
//
//                break;
//        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {


    }
}
