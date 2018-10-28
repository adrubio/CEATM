package com.itt.ceatm;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

import static android.R.layout.simple_spinner_item;

public class Activity_Admin_Agregar_Atletas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    MaterialSpinner sp_deporte;
    MaterialSpinner sp_entrenador;
    MaterialSpinner sp_modalidad;
    MaterialSpinner sp_municipio;
    EditText et_nombre;
    EditText et_edad;
    EditText et_nacimiento;
    TextInputLayout ti_nombre;
    TextInputLayout ti_edad;
    TextInputLayout ti_nacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar_atletas);

        sp_deporte = (MaterialSpinner) findViewById(R.id.sp_Deporte);
        sp_modalidad = (MaterialSpinner) findViewById(R.id.sp_Modalidad);
        sp_entrenador = (MaterialSpinner) findViewById(R.id.sp_Entrenador);
        sp_municipio = (MaterialSpinner) findViewById(R.id.sp_Municipio);
        et_nombre = (EditText) findViewById(R.id.et_Nombre);
        et_edad = (EditText) findViewById(R.id.et_Edad);
        et_nacimiento = (EditText) findViewById(R.id.et_Nacimiento);

        ti_nombre = (TextInputLayout) findViewById(R.id.ti_Nombre);
        ti_edad = (TextInputLayout) findViewById(R.id.ti_Edad);
        ti_nacimiento = (TextInputLayout) findViewById(R.id.ti_Nacimiento);

        //Creacion de listas para los datos
        ArrayList<String> datos_Deportes = new ArrayList<String>();
        ArrayList<String> datos_Modalidades = new ArrayList<String>();
        ArrayList<String> datos_Entrenadores = new ArrayList<String>();
        ArrayList<String> datos_Municipios = new ArrayList<String>();


        //Llenado de datos
        datos_Deportes.add("Tiro con arco");
        datos_Deportes.add("Atletismo");
        datos_Deportes.add("Lanzamiento con bala");

        datos_Modalidades.add("A");
        datos_Modalidades.add("B");
        datos_Modalidades.add("C");

        datos_Entrenadores.add("Guillermo del Toro");
        datos_Entrenadores.add("PewDiePie");
        datos_Entrenadores.add("freddy Mercury");

        datos_Municipios.add("Tijuana");
        datos_Municipios.add("Mexicali");
        datos_Municipios.add("Ensenada");
        datos_Municipios.add("Mexicali");


        //Declaracion de adaptadores
        ArrayAdapter<String> deportes_adaptador = new ArrayAdapter(this, simple_spinner_item, datos_Deportes);
        ArrayAdapter<String> modalidad_adaptador = new ArrayAdapter(this, simple_spinner_item, datos_Modalidades);
        ArrayAdapter<String> entrenadores_adaptador = new ArrayAdapter(this, simple_spinner_item, datos_Entrenadores);
        ArrayAdapter<String> municipios_adaptador = new ArrayAdapter(this, simple_spinner_item, datos_Municipios);

        //Hace que se vea como un drop down, si no se le pone, los items estan muy juntos
        deportes_adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modalidad_adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        entrenadores_adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipios_adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //Establecer los adaptadores
        sp_deporte.setAdapter(deportes_adaptador);
        sp_modalidad.setAdapter(modalidad_adaptador);
        sp_entrenador.setAdapter(entrenadores_adaptador);
        sp_municipio.setAdapter(municipios_adaptador);

        sp_deporte.setOnItemSelectedListener(this);
        sp_modalidad.setOnItemSelectedListener(this);
        sp_entrenador.setOnItemSelectedListener(this);
        sp_municipio.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "Eleccion:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

        //Quitar los focus de algun editText que se haya modificado anteriormente
        ti_nombre.clearFocus();
        ti_edad.clearFocus();
        ti_nacimiento.clearFocus();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View view) {
        //Proceso de comprobacion de campos y mandar datos a la base de datos
        ///...

        //Si se hizo correctamente, salir de la actividad
        Toast.makeText(this, "Atleta creado exitosamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
