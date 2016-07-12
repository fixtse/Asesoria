package com.ulima.sw.Asesorias.asebeans;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
@IgnoreExtraProperties
public class Alumno {

    private String nombres;
    //private String apellidos;
    private String facultad;
    private List<Long> idcursos;
    private List<Long> idMensaje;

    public Alumno() {
    }

    public Alumno(String nombres, String facultad, List<Long> idcursos, List<Long> idMensaje) {
        this.nombres = nombres;
        this.facultad = facultad;
        this.idcursos = idcursos;
        this.idMensaje = idMensaje;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public List<Long> getIdcursos() {
        return idcursos;
    }

    public void setIdcursos(List<Long> idcursos) {
        this.idcursos = idcursos;
    }

    public List<Long> getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(List<Long> idMensaje) {
        this.idMensaje = idMensaje;
    }
}
