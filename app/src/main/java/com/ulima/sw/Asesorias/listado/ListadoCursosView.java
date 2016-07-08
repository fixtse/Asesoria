package com.ulima.sw.Asesorias.listado;

import com.ulima.sw.Asesorias.asebeans.Curso;

import java.util.List;

/**
 * Created by Admin on 6/05/2016.
 */
public interface ListadoCursosView {
    public void setPresenter(ListadoCursosPresenter presenter);
    public void mostrarCursos(List<Curso> cursos);
}
