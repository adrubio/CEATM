package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.itt.ceatm.Clases.Perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class frg_atleta_perfil extends Fragment{

    private String usuario;
    TextView nombre, direccion, deporte, modalidad, fecha, entrenador, edad;
    RequestQueue Rq;
    JsonRequest Jrq;

    int edad_actual,nacimiento_dia, nacimiento_mes, nacimiento_ano;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = getArguments().getString("USUARIO", "Nulo");
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.frg_atleta_perfil, container, false);
        nombre = (TextView)vista.findViewById(R.id.tv_atleta_visualizar);
        direccion = (TextView)vista.findViewById(R.id.tv_direccion);
        deporte = (TextView)vista.findViewById(R.id.tv_deporte_visualizar);
        modalidad = (TextView)vista.findViewById(R.id.tv_modalidad);
        fecha = (TextView)vista.findViewById(R.id.tv_nacimiento);
        entrenador = (TextView)vista.findViewById(R.id.tv_entrenador);
        edad = (TextView)vista.findViewById(R.id.tv_edad);

        Rq = Volley.newRequestQueue(getContext());
        perfiles();
        return vista;
    }

    public void perfiles(){
        String url ="https://qr-pay.000webhostapp.com/ceatm/Perfil.php?usuario="+usuario;
        Jrq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Perfil perfiles = new Perfil();
                JSONArray jsonArray = response.optJSONArray("perfil");
                JSONObject jsonObject = null;

                try {
                    jsonObject = jsonArray.getJSONObject(0);
                    perfiles.setUsuario(jsonObject.optString("usuario"));
                    perfiles.setNombres(jsonObject.optString("nombres"));
                    perfiles.setApellidoPaterno(jsonObject.optString("apellido_paterno"));
                    perfiles.setApellidoMaterno(jsonObject.optString("apellido_materno"));
                    perfiles.setFechaNacimiento(jsonObject.optString("fecha_nacimiento"));
                    //perfiles.setImagen(jsonObject.optString("imagen"));
                    perfiles.setDeporte(jsonObject.optString("descripcion_deporte"));
                    perfiles.setModalidad(jsonObject.optString("descripcion_modalidad"));
                    perfiles.setEstado(jsonObject.optString("abreviatura"));
                    perfiles.setMunicipio(jsonObject.optString("municipio"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                nombre.setText(perfiles.getNombres()+" "+perfiles.getApellidoPaterno()+" "+perfiles.getApellidoMaterno());
                direccion.setText(perfiles.getMunicipio()+", "+perfiles.getEstado());
                deporte.setText(perfiles.getDeporte());
                modalidad.setText(perfiles.getModalidad());
                fecha.setText(perfiles.getFechaNacimiento());

                edad_actual = getAge(1995,05,24);//modificar
                edad.setText(String.format("%d",edad_actual));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Rq.add(Jrq);
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
