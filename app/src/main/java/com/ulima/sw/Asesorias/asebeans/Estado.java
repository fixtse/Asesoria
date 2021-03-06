package com.ulima.sw.Asesorias.asebeans;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by fixt on 09/07/16.
 */
@IgnoreExtraProperties
public class Estado implements Serializable {

    private int id;
    private String estado;

    public Estado() {
    }

    public Estado(String hora, int id, String estado) {

        this.id = id;
        this.estado = estado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
