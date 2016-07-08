package com.ulima.sw.Asesorias.asebeans;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Alumno {

    private String nombres;
    private String apellidos;
    private String facultad;
    private Usuario usuario;
    private List<Curso> cursos;

    public Alumno() {
    }

    public Alumno(String apellidos, List<Curso> cursos, String nombres, Usuario usuario, String facultad) {
        this.apellidos = apellidos;
        this.cursos = cursos;
        this.nombres = nombres;
        this.usuario = usuario;
        this.facultad = facultad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
