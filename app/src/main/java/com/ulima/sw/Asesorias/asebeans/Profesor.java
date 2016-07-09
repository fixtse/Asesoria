package com.ulima.sw.Asesorias.asebeans;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Profesor {

    private String nombres;
    private String apellidos;

    private List<Curso> Cursos;

    public Profesor() {
    }

    public Profesor(String nombres, String apellidos, List<Curso> cursos) {
        this.nombres = nombres;
        this.apellidos = apellidos;

        Cursos = cursos;
    }

    public List<Curso> getCursos() {
        return Cursos;
    }

    public void setCursos(List<Curso> cursos) {
        Cursos = cursos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }


}
