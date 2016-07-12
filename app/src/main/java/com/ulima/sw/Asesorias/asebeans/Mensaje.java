package com.ulima.sw.Asesorias.asebeans;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by fixt on 09/07/16.
 */
@IgnoreExtraProperties
public class Mensaje {

    private String contenido;
    private int curso;
    private long idProf;
    private long idAlumno;

    public Mensaje() {
    }

    public Mensaje(String contenido, int curso, long idProf, long idAlumno) {
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

    public long getIdProf() {
        return idProf;
    }

    public void setIdProf(long idProf) {
        this.idProf = idProf;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }
}
