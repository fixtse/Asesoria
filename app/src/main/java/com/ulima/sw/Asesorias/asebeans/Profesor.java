package com.ulima.sw.Asesorias.asebeans;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Profesor {

    private String nombre;
    //private String apellidos;

    private int idCursos;

    public Profesor() {
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
