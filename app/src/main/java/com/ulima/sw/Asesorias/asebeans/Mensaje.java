package com.ulima.sw.Asesorias.asebeans;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fixt on 09/07/16.
 */
@IgnoreExtraProperties
public class Mensaje implements Serializable{

    private List<String> contenidos;

    private String curso;
    private long idProf;
    private long id;
    private String usuarioAL, usuarioPROF;
    private long idAlumno;
    private long vib;

    public Mensaje() {
    }

    public Mensaje(List<String> contenidos,Long id, String curso, long idProf, String usuarioAL, String usuarioPROF, long idAlumno) {
        this.contenidos = contenidos;
        this.id = id;
        this.curso = curso;
        this.idProf = idProf;
        this.usuarioAL = usuarioAL;
        this.usuarioPROF = usuarioPROF;
        this.idAlumno = idAlumno;
        vib = 0l;
    }

    public List<String> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<String> contenidos) {
        this.contenidos = contenidos;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public long getIdProf() {
        return idProf;
    }

    public void setIdProf(long idProf) {
        this.idProf = idProf;
    }

    public String getUsuarioAL() {
        return usuarioAL;
    }

    public void setUsuarioAL(String usuarioAL) {
        this.usuarioAL = usuarioAL;
    }

    public String getUsuarioPROF() {
        return usuarioPROF;
    }

    public void setUsuarioPROF(String usuarioPROF) {
        this.usuarioPROF = usuarioPROF;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVib() {
        return vib;
    }

    public void setVib(long vib) {
        this.vib = vib;
    }
}
