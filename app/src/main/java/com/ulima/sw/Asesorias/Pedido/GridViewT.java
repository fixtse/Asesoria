package com.ulima.sw.Asesorias.Pedido;

import com.ulima.sw.Asesorias.beans.Mensaje;

import java.util.List;

/**
 * Created by fixt on 14/06/16.
 */
public interface GridViewT {
    public void setPresenter(GridPresenter presenter);
    public void mostrarPedidos(List<Mensaje> pedidos);
}
