package com.itt.ceatm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class adt_admin_competencias extends  RecyclerView.Adapter<adt_admin_competencias.ViewHolderDatos> {

    ArrayList<datos_admin_competencias> lista_admin_competencias;

    public adt_admin_competencias(ArrayList<datos_admin_competencias> lista_admin_competencias) {
        this.lista_admin_competencias = lista_admin_competencias;
    }

    @NonNull
    @Override
    public adt_admin_competencias.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_admin_competencias,null,false);
        return new adt_admin_competencias.ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adt_admin_competencias.ViewHolderDatos holder, int i) {
        holder.rb_atletas_competencias.setText(lista_admin_competencias.get(i).getNombre());

    }

    @Override
    public int getItemCount() {
        return lista_admin_competencias.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        RadioButton rb_atletas_competencias;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            rb_atletas_competencias = itemView.findViewById(R.id.rb_admin_competencias);
        }
    }
}
