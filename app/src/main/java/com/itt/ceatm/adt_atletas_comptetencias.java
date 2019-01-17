package com.itt.ceatm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adt_atletas_comptetencias extends RecyclerView.Adapter<adt_atletas_comptetencias.ViewHolderDatos>{

    ArrayList<datos_atletas_competencias> lista_competencias;

    public adt_atletas_comptetencias(ArrayList<datos_atletas_competencias> lista_competencias) {
        this.lista_competencias = lista_competencias;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_atleta_competencias,null,false);
        return new ViewHolderDatos(v);
    }

    //Establece la comunicacion entre el adaptador y la clase ViewHolderDatos
    @Override
    public void onBindViewHolder(@NonNull adt_atletas_comptetencias.ViewHolderDatos holder, int i) {
        holder.comp.setText(lista_competencias.get(i).getCompetencia());
        holder.punt1.setText(lista_competencias.get(i).getPuntaje1());
        holder.punt2.setText(lista_competencias.get(i).getPuntaje2());
    }

    @Override
    public int getItemCount() {
        return lista_competencias.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView comp,punt1,punt2;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            comp = itemView.findViewById(R.id.atleta_competencia_competencia_nombre);
            punt1 = itemView.findViewById(R.id.atleta_competencia_opcion1);
            punt2 = itemView.findViewById(R.id.atleta_competencia_opcion2);
        }

        public void obtenerDatos(String s) {

        }
    }
}
