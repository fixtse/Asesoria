package com.ulima.sw.Asesorias.listado;

import com.ulima.sw.Asesorias.beans.Pizza;

import java.util.List;

/**
 * Created by Admin on 6/05/2016.
 */
public interface ListadoPizzasView {
    public void setPresenter(ListadoPizzasPresenter presenter);
    public void mostrarPizzas(List<Pizza> Pizzas);
    public void toAst(int num);
}
