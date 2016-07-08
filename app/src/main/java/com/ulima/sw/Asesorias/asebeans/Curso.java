package com.ulima.sw.Asesorias.asebeans;

/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Curso {
    private int id;
    private String nombre;
    private int seccion;

    public Curso() {
    }

    public Curso(int id,String nombre, int seccion) {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
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

