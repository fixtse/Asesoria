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
    private String id;
    private String curso;
    private long idProf;
    private String usuarioAL, usuarioPROF;
    private long idAlumno;

    public Mensaje() {
    }

    public Mensaje(List<String> contenidos, String id, String curso, long idProf, String usuarioAL, String usuarioPROF, long idAlumno) {
        this.contenidos = contenidos;
        this.id = id;
        this.curso = curso;
        this.idProf = idProf;
        this.usuarioAL = usuarioAL;
        this.usuarioPROF = usuarioPROF;
        this.idAlumno = idAlumno;
    }

    public List<String> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<String> contenidos) {
        this.contenidos = contenidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
