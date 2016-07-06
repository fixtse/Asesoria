package com.ulima.sw.Asesorias.ingredientes;

import com.ulima.sw.Asesorias.beans.Ingrediente;

import java.util.List;

/**
 * Created by fixt on 15/06/16.
 */
public interface IngredientesView {
    public void setPresenter(IngredientesPresenter presenter);
    public void mostrarIngredientes(List<Ingrediente> Ingredientes);
}
