package com.ulima.sw.Asesorias.asebeans;


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Usuario implements Serializable {
    private String usuario;
    private String password;
    private String tipo;
    private long profid, almid;

    public Usuario()  {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getProfid() {
        return profid;
    }

    public void setProfid(long profid) {
        this.profid = profid;
    }

    public long getAlmid() {
        return almid;
    }

    public void setAlmid(long almid) {
        this.almid = almid;
    }
}
