package com.itt.ceatm;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.FragmentManager;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;

import static android.R.layout.simple_spinner_item;

public class Activity_Admin_Agregar_Atletas extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {


    MaterialSpinner sp_deporte, sp_modalidad, sp_municipio, sp_entrenador;
    EditText et_usuario, et_contrasena, et_nombre, et_apellido_paterno, et_apellido_materno;
    Button btn_nacimiento;
    TextInputLayout ti_nombre, ti_edad;
    TextView tv_nacimiento, tv_edad;

    //Variables para mandar a la base de datos
    int edad_actual,nacimiento_dia, nacimiento_mes, nacimiento_ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar_atletas);

        et_usuario = (EditText) findViewById(R.id.et_Usuario);
        et_contrasena = (EditText) findViewById(R.id.et_Contrasena);
        et_nombre = (EditText) findViewById(R.id.et_Nombre);
        ti_nombre = (TextInputLayout) findViewById(R.id.ti_Nombre);
        et_apellido_paterno = (EditText) findViewById(R.id.et_Apellido_Paterno);
        et_apellido_materno = (EditText) findViewById(R.id.et_Apellido_Materno);
        btn_nacimiento = (Button) findViewById(R.id.btn_Nacimiento);
        tv_nacimiento = (TextView) findViewById(R.id.tv_Nacimiento);
        tv_edad = (TextView) findViewById(R.id.tv_Edad);
        sp_deporte = (MaterialSpinner) findViewById(R.id.sp_Deporte);
        sp_modalidad = (MaterialSpinner) findViewById(R.id.sp_Modalidad);
        sp_entrenador = (MaterialSpinner) findViewById(R.id.sp_Entrenador);
        sp_municipio = (MaterialSpinner) findViewById(R.id.sp_Municipio);

        //Creacion de listas para los datos
        ArrayList<String> datos_Deportes = new ArrayList<>();
        ArrayList<String> datos_Modalidades = new ArrayList<>();
        ArrayList<String> datos_Entrenadores = new ArrayList<>();
        ArrayList<String> datos_Municipios = new ArrayList<>();


        //Llenado de datos

        datos_Deportes.add("Tiro con arco");
        datos_Deportes.add("Atletismo");
        datos_Deportes.add("Lanzamiento con bala");

        datos_Modalidades.add("A");
        datos_Modalidades.add("B");
        datos_Modalidades.add("C");

        datos_Entrenadores.add("Mauricio Lavardez Castillo");
        datos_Entrenadores.add("Omar Gonzalez Marian");
        datos_Entrenadores.add("freddy Mercury");

        datos_Municipios.add("Tijuana");
        datos_Municipios.add("Mexicali");
        datos_Municipios.add("Ensenada");


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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_Nacimiento:
                Calendar now = Calendar.getInstance();
                DatePickerDialog fecha_dialogo = DatePickerDialog.newInstance(
                        Activity_Admin_Agregar_Atletas.this,

                        //Se establece que se muestra un promedio de edad y se pone en medio en mes y el dia para que sea mas facil ir a los extremos (Enero y Diciembre)
                        2000, // Initial year selection
                        6, // Initial month selection
                        15// Inital day selection
                );

                fecha_dialogo.showYearPickerFirst(true);
                fecha_dialogo.setTitle("Fecha de nacimiento");
                fecha_dialogo.show(getFragmentManager(), "Datepickerdialog");;
                fecha_dialogo.setAccentColor(getResources().getColor(R.color.colorPrimario));

                //Para las competencias
//                Calendar minimo = Calendar.getInstance();
//                fecha_dialogo.setMinDate(minimo);



                //Se obtiene el año actual menos 18, que hace referencia a que lo minimo que se debe de tener son 18 años
                int año_maximo = Calendar.getInstance().get(Calendar.YEAR) - 18;

                //Fecha de nacimiento maxima
                Calendar maximo = Calendar.getInstance();
                maximo.set(año_maximo,11,31);
                fecha_dialogo.setMaxDate(maximo);

                break;

            case R.id.btn_Agregar_Atletas_Guardar:
                //Proceso de comprobacion de campos y mandar datos a la base de datos
                ///...
                //Si se hizo correctamente, salir de la actividad
                Toast.makeText(this, "Atleta creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();

                break;

            case R.id.btn_Agregar_Atleta_Regresar:
                finish();
                break;
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(this, "Seleccionaste: 1", Toast.LENGTH_SHORT).show();
        tv_nacimiento.setText(String.format("%d / %d / %d",dayOfMonth,monthOfYear,year));

//        int edad_actual = Calendar.getInstance().get(Calendar.YEAR) - year;

        edad_actual = getAge(year,monthOfYear,dayOfMonth);
        nacimiento_dia = dayOfMonth;
        nacimiento_mes = monthOfYear;
        nacimiento_ano = year;
        tv_edad.setText(String.format("%d",edad_actual));
    }

    public int getAge (int _year, int _month, int _day) {
        //Fecha del calendario actual
        Calendar cal = Calendar.getInstance();
        int y, m, d, edad;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH) + 1; // Because if from 0 to 11
        d = cal.get(Calendar.DAY_OF_MONTH);
        edad = cal.get(Calendar.YEAR) - _year;

        //Diferenciar si ya cumplio años en el alo presente o no
        if ((_month > cal.get(Calendar.MONTH)) || ((_month == cal.get(Calendar.MONTH)) && (_day > cal.get(Calendar.DAY_OF_MONTH)))) {
            --edad;
        }
        return edad;
    }
}
