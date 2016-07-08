package com.ulima.sw.Asesorias.asebeans;


/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Asesoria {
    private String dia;
    private String hora;
    private String lugar;

    public Asesoria() {
    }

    public Asesoria(String dia, String hora, String lugar) {
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
