package com.ulima.sw.Asesorias.asebeans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Curso implements Serializable {
    private int id;
    private String nombre;
    private int seccion;
    private List<Asesoria> asesorias;

    public Curso() {
    }

    public Curso(int id,String nombre, int seccion) {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public Curso(int id, String nombre, int seccion, List<Asesoria> asesorias) {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
        this.asesorias = asesorias;
    }

    public List<Asesoria> getAsesorias() {
        return asesorias;
    }

    public void setAsesorias(List<Asesoria> asesorias) {
        this.asesorias = asesorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }


}

