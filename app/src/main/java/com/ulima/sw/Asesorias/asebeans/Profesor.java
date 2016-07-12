package com.ulima.sw.Asesorias.asebeans;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
@IgnoreExtraProperties
public class Profesor {

    private String nombre;
    //private String apellidos;
    private List<Long> idMensaje;

    private int idCursos;

    public Profesor() {
    }

    public Profesor(String nombre, List<Long> idMensaje, int idCursos) {
        this.nombre = nombre;
        this.idMensaje = idMensaje;
        this.idCursos = idCursos;
    }

    public List<Long> getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(List<Long> idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCursos() {
        return idCursos;
    }

    public void setIdCursos(int idCursos) {
        this.idCursos = idCursos;
    }
}
