package com.itt.ceatm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class frg_administrador_atletas extends Fragment {

    //Lista de atletas
    public List<cl_lista_atletas> lista_atl;
    public RecyclerView rcc_lista_atletas;
    public cl_lista_atletas_adaptador adaptador_lista_atletas;



    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_admin_atletas, container, false);

        rcc_lista_atletas = (RecyclerView)view.findViewById(R.id.recycler_administrador_atletas);

        LinearLayoutManager linear = new LinearLayoutManager(this.getActivity());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        rcc_lista_atletas.setLayoutManager(linear);
        data();
        iniciar_adaptador_atletas();
        return view;
    }


    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do your variables initialisations here except Views!!!


    }


    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }

    public void data(){
        lista_atl = new ArrayList<>();
        lista_atl.add(new cl_lista_atletas("Astrid Ruvalcaba Ramos", "Esgrima"));
        lista_atl.add(new cl_lista_atletas("Daniel Sanchez Cuevas", "G. Artistica"));
        lista_atl.add(new cl_lista_atletas("Alexa Luna Contreras", "TKD"));
        lista_atl.add(new cl_lista_atletas("Paul Carillo Mendez", "Natacion"));
        lista_atl.add(new cl_lista_atletas("Karen Mendoza Galindo", "Boxeo"));
        lista_atl.add(new cl_lista_atletas("Marco Torres Miranda", "Tiro con arco"));
    }

    public void iniciar_adaptador_atletas(){
        adaptador_lista_atletas = new cl_lista_atletas_adaptador(lista_atl);
        rcc_lista_atletas.setAdapter(adaptador_lista_atletas);
    }

}
