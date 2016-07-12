package com.ulima.sw.Asesorias.Mensajes;

import com.ulima.sw.Asesorias.asebeans.Mensaje;

import java.util.List;



/**
 * Created by Diego Torres on 15/05/2016.
 */
public interface MensajesView {
    public void setPresenter(MensajesPresenter presenter);
    public void mostrarMensajes(List<Mensaje> mensajes);
}
