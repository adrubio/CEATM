package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class frg_atleta_competencias extends Fragment{

    public ArrayList<datos_atletas_competencias> lista_comp;
    public RecyclerView recycler_comp;
    public adt_atletas_comptetencias adaptador_lista_competencias;
    Button btn_Enviar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_atleta_competencias, container, false);

        //Recycler
        //--------------------------------------------------------------------------------------------
//        recycler_comp = (RecyclerView)view.findViewById(R.id.rcc_atleta_competencias);
//        recycler_comp.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));
//        //Grid
//        //recycler_comp.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
//        data();
//        adaptador_lista_competencias = new adt_atletas_comptetencias(lista_comp);
//        recycler_comp.setAdapter(adaptador_lista_competencias);
        //--------------------------------------------------------------------------------------------

        btn_Enviar = (Button)view.findViewById(R.id.btn_Atleta_Competencias_Enviar);

        btn_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Datos enviados exitosamente", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public void data(){
        lista_comp = new ArrayList<>();
        lista_comp.add(new datos_atletas_competencias("Juegos Transnacionales ", "8","15"));
        lista_comp.add(new datos_atletas_competencias("Juegos Olimpicos de la Juventud 2018 ", "9","12"));
        lista_comp.add(new datos_atletas_competencias("Juegos Juventud 2018", "12","11"));
        lista_comp.add(new datos_atletas_competencias("Bala Alta 2018", "TKD","10"));
        lista_comp.add(new datos_atletas_competencias("11vos Juegos Panamericanos", "10","14"));
    }


}
