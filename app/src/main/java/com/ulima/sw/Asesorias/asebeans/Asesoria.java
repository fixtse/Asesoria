package com.ulima.sw.Asesorias.asebeans;


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
@IgnoreExtraProperties
public class Asesoria implements Serializable {
    private String dia;
    private String hora;
    private String lugar;
    private Estado estado;
    private List<String> alumnos;
    private String profesor;
    private float calific = 0;

    public List<String> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<String> alumnos) {
        this.alumnos = alumnos;
    }

    public float getCalific() {
        return calific;
    }

    public void setCalific(float calific) {
        this.calific = calific;
    }

    public Asesoria() {
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Asesoria(String dia, String hora, String lugar, Estado estado) {
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.estado = estado;

    }

    public Asesoria(String dia, String hora, String lugar, Estado estado, List<String> alumnos, float calific) {
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.estado = estado;
        this.alumnos = alumnos;
        this.calific = calific;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
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
