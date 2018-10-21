package com.itt.ceatm;

public class lista_atletas {

    private String nombre;
    private String deporte;

    //Constructor
    public lista_atletas(String nombre, String deporte) {
        this.nombre = nombre;
        this.deporte = deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }
}
