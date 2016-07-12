package com.ulima.sw.Asesorias.Mensajes;

import android.util.Log;

import java.util.List;


/**
 * Created by Diego Torres on 15/05/2016.
 */
public class MensajesPresenterImp implements MensajesPresenter {

    private MensajesView mensajesViewTT;

    public MensajesPresenterImp(MensajesView view){
        this.mensajesViewTT =view;
    }


    @Override
    public void obtenerMensajes() {

    }
}
