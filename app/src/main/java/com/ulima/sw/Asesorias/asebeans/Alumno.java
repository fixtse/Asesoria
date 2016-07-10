package com.ulima.sw.Asesorias.asebeans;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Alumno {

    private String nombres;
    //private String apellidos;
    private String facultad;
    private int idcursos;

    public Alumno() {
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

    public int getIdcursos() {
        return idcursos;
    }

    public void setIdcursos(int idcursos) {
        this.idcursos = idcursos;
    }
}
