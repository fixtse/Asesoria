package com.ulima.sw.Asesorias.asebeans;

/**
 * Created by fixt on 09/07/16.
 */
public class Mensaje {

    private String contenido;
    private int curso;
    private int idProf;
    private int idAlumno;

    public Mensaje() {
    }

    public Mensaje(String contenido, int curso, int idProf, int idAlumno) {
        this.contenido = contenido;
        this.curso = curso;
        this.idProf = idProf;
        this.idAlumno = idAlumno;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
}
