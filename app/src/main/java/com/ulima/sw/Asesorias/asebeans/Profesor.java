package com.ulima.sw.Asesorias.asebeans;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
@IgnoreExtraProperties
public class Profesor {

    private String nombre;
    private Long id;
    //private String apellidos;


    private int idCursos;

    public Profesor() {
    }

    public Profesor(String nombre, int idCursos) {
        this.nombre = nombre;

        this.idCursos = idCursos;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
