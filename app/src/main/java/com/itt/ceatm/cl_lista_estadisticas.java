package com.itt.ceatm;

public class cl_lista_estadisticas {

    private String competencia_Nombre;
    private float competencia_Index;
    private float puntuacion;

    //Constructor
    public cl_lista_estadisticas(String competencia_Nombre, float competencia_Index, float puntuacion) {
        this.competencia_Nombre = competencia_Nombre;
        this.competencia_Index = competencia_Index;
        this.puntuacion = puntuacion;
    }

    public String getCompetencia_Nombre() {
        return competencia_Nombre;
    }
    public void setCompetencia_Nombre(String competencia_Nombre) {
        this.competencia_Nombre = competencia_Nombre;
    }

    public float getCompetencia_Index() {
        return competencia_Index;
    }
    public void setCompetencia_Index(float competencia_Index) {
        this.competencia_Index = competencia_Index;
    }

    public float getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }
}
