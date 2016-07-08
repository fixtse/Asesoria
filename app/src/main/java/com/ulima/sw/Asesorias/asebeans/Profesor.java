package com.ulima.sw.Asesorias.asebeans;

import java.util.List;

/**
 * Created by Diego Torres on 7/07/2016.
 */
public class Profesor {

    private String nombres;
    private String apellidos;
    private Usuario usuario;
    private List<Asesoria> asesorias;

    public Profesor() {
    }

    public Profesor(String apellidos, List<Asesoria> asesorias, String nombres, Usuario usuario) {
        this.apellidos = apellidos;
        this.asesorias = asesorias;
        this.nombres = nombres;
        this.usuario = usuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Asesoria> getAsesorias() {
        return asesorias;
    }

    public void setAsesorias(List<Asesoria> asesorias) {
        this.asesorias = asesorias;
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
}
