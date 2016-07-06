package com.ulima.sw.Asesorias.Informacion;


import com.ulima.sw.Asesorias.beans.Pedido;

/**
 * Created by fixt on 14/05/16.
 */
public interface InformView {
    public void setPresenter(InformPresenter presenter);
    public void mostrarPedido(Pedido pedido);
}
