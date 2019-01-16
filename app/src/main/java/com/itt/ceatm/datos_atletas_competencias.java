package com.itt.ceatm;

public class datos_atletas_competencias {

    private String competencia;
    private String puntaje1;
    private String puntaje2;

    public datos_atletas_competencias(String competencia, String puntaje1, String puntaje2) {
        this.competencia = competencia;
        this.puntaje1 = puntaje1;
        this.puntaje2 = puntaje2;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getPuntaje1() {
        return puntaje1;
    }

    public void setPuntaje1(String puntaje1) {
        this.puntaje1 = puntaje1;
    }

    public String getPuntaje2() {
        return puntaje2;
    }

    public void setPuntaje2(String puntaje2) {
        this.puntaje2 = puntaje2;
    }
}
