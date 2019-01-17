package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    public ArrayList<datos_admin_competencias> lista_admin_comp;
    public RecyclerView recycler_admin_comp;
    public adt_admin_competencias adt_admin_lista_competencias;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_admin_competencias, container, false);


        et_nombre_competencias = (EditText)view.findViewById(R.id.et_Nombre_Competencias);
        sp_deporte_competencias = (MaterialSpinner)view.findViewById(R.id.sp_Deporte_Competencias);
        sp_modalidad_competencias = (MaterialSpinner)view.findViewById(R.id.sp_Modalidad_Competencias);
        et_lugar_competencias = (EditText)view.findViewById(R.id.et_Lugar_Competencias);
        btn_fecha_competencias = (Button) view.findViewById(R.id.btn_Fecha_Competencias);
        tv_fecha_competencias = (TextView) view.findViewById(R.id.tv_Fecha_Competencias);
        tv_hora_competencias = (TextView) view.findViewById(R.id.tv_Hora_Competencias);

        recycler_admin_comp = (RecyclerView)view.findViewById(R.id.rcc_admin_competencias);
        recycler_admin_comp.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));
        //Grid
        //recycler_comp.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        data();
        adt_admin_lista_competencias = new adt_admin_competencias(lista_admin_comp);
        recycler_admin_comp.setAdapter(adt_admin_lista_competencias);


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

                //tv_fecha_competencias.setText(String.format("%d / %d / %d",dayOfMonth,monthOfYear,year));
                //tv_fecha_competencias.setText(String.valueOf(date));
                tv_fecha_competencias.setText(String.format("%d / %d / %d",date.getDate(),date.getMonth()+1,date.getYear()+1900));
                tv_hora_competencias.setText(String.format("%d : %d ", date.getHours(),date.getMinutes()));


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

        return view;
    }

    public void data(){
        lista_admin_comp = new ArrayList<>();
        lista_admin_comp.add(new datos_admin_competencias("Astrid Ruvalcaba Ramos", "Esgrima","generica"));
        lista_admin_comp.add(new datos_admin_competencias("Daniel Sanchez Cuevas", "G. Artistica","generica"));
        lista_admin_comp.add(new datos_admin_competencias("Alexa Luna Contreras", "TKD","generica"));
        lista_admin_comp.add(new datos_admin_competencias("Paul Carillo Mendez", "Natacion","generica"));
        lista_admin_comp.add(new datos_admin_competencias("Karen Mendoza Galindo", "Boxeo","generiaca"));
    }
}
