package com.ulima.sw.Asesorias.asebeans;


import java.io.Serializable;


public class Usuario implements Serializable {
    private String usuario;
    private String password;
    private String correo;
    private int tipo;
    private Profesor prof;
    private Alumno alm;

    public Usuario() {
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario(String usuario, String password, String correo, int tipo, Profesor prof, Alumno alm) {
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.tipo = tipo;
        this.prof = prof;
        this.alm = alm;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Profesor getProf() {
        return prof;
    }

    public void setProf(Profesor prof) {
        this.prof = prof;
    }

    public Alumno getAlm() {
        return alm;
    }

    public void setAlm(Alumno alm) {
        this.alm = alm;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
