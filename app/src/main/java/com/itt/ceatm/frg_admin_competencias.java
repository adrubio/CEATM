package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;

public class frg_admin_competencias extends Fragment {

    MaterialSpinner sp_deporte_competencias;
    MaterialSpinner sp_modalidad_competencias;
    EditText et_nombre_competencias;
    EditText et_lugar_competencias;
    Button btn_fecha_competencias;
    TextInputLayout ti_nombre;
    TextInputLayout ti_edad;
    TextView tv_fecha_competencias;
    TextView tv_hora_competencias;
    RadioGroup rg_atletas_competencias;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_admin_agregar_competencias, container, false);


        et_nombre_competencias = (EditText)view.findViewById(R.id.et_Nombre_Competencias);
        sp_deporte_competencias = (MaterialSpinner)view.findViewById(R.id.sp_Deporte_Competencias);
        sp_modalidad_competencias = (MaterialSpinner)view.findViewById(R.id.sp_Modalidad_Competencias);
        et_lugar_competencias = (EditText)view.findViewById(R.id.et_Lugar_Competencias);
        btn_fecha_competencias = (Button) view.findViewById(R.id.btn_Fecha_Competencias);
        tv_fecha_competencias = (TextView) view.findViewById(R.id.tv_Fecha_Competencias);
        tv_hora_competencias = (TextView) view.findViewById(R.id.tv_Hora_Competencias);


        final SwitchDateTimeDialogFragment fecha_competencias = SwitchDateTimeDialogFragment.newInstance(
                "Fecha de la competencia",
                "Aceptar",
                "Cancelar"
        );

        // Assign values
        fecha_competencias.startAtCalendarView();
        fecha_competencias.set24HoursMode(false);
//                fecha_competencias.setMinimumDateTime(new GregorianCalendar(2015, Calendar.JANUARY, 1).getTime());
//                fecha_competencias.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());
        Calendar min = Calendar.getInstance();

        //Restringir fecha para que no ponga una competencia en un dia anterior al actual
        fecha_competencias.setMinimumDateTime(min.getTime());

        fecha_competencias.setDefaultHourOfDay(15);
        fecha_competencias.setDefaultMinute(20);


        fecha_competencias.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                // Date is get on positive button click
                // Do something
                Toast.makeText(getContext(), "sup", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Date is get on negative button click
            }
        });



        btn_fecha_competencias.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                fecha_competencias.show(getFragmentManager(), "dialog_time");



            }
        });



        return  view;

    }
}
