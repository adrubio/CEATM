package com.itt.ceatm;

public class datos_admin_competencias {

    private String nombre;
    private String deporte;
    private String modalidad;

    public datos_admin_competencias(String nombre, String deporte, String modalidad) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.modalidad = modalidad;
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

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
}
