package com.itt.ceatm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class cl_lista_atletas_adaptador extends RecyclerView.Adapter<cl_lista_atletas_adaptador.Lista_Atletas_ViewHolder>{

    public static final String NOMBRE = "NOMBRE";
    public static final String DEPORTE = "DEPOERTE";

    // Nombre de clase de Java
    private List<cl_lista_atletas> atletas_lista;
    public cl_lista_atletas_adaptador(List<cl_lista_atletas> atletas_lista ){
        this.atletas_lista = atletas_lista;
    }

    @NonNull
    @Override
    public Lista_Atletas_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_atletas_lista,viewGroup,false);
        return new Lista_Atletas_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Lista_Atletas_ViewHolder lista_atletas_viewHolder, int i) {
        cl_lista_atletas lista = atletas_lista.get(i);
        lista_atletas_viewHolder.tv_atleta_nombre.setText(lista.getNombre());
        lista_atletas_viewHolder.tv_atleta_deporte.setText(lista.getDeporte());
    }

    @Override
    public int getItemCount() {
        return atletas_lista.size();
    }

    public class Lista_Atletas_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_atleta_nombre;
        private TextView tv_atleta_deporte;

        public Lista_Atletas_ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            tv_atleta_nombre = (TextView) itemView.findViewById(R.id.list_tv_atleta_nombre);
            tv_atleta_deporte = (TextView) itemView.findViewById(R.id.list_tv_atleta_deporte);

        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            int position = getAdapterPosition();
            cl_lista_atletas lista = atletas_lista.get(position);
            Intent intent = new Intent(v.getContext(), Activity_Administrador_Ver_Atletas.class);
            intent.putExtra(NOMBRE, lista.getNombre());
            intent.putExtra(DEPORTE, lista.getDeporte());
            context.startActivity(intent);

        }
    }


}
