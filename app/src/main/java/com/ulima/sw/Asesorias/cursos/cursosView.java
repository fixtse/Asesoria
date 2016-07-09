package com.ulima.sw.Asesorias.cursos;

import com.ulima.sw.Asesorias.asebeans.Curso;



import java.util.List;

/**
 * Created by fixt on 15/06/16.
 */
public interface cursosView {
    public void setPresenter(cursosPresenter presenter);
    public void mostrarCursos(List<Curso> cursos);
}
