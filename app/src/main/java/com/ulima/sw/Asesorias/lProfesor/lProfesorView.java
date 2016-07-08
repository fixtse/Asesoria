package com.ulima.sw.Asesorias.lProfesor;

import com.ulima.sw.Asesorias.asebeans.Curso;


import java.util.List;

/**
 * Created by fixt on 15/06/16.
 */
public interface lProfesorView {
    public void setPresenter(lProfesorPresenter presenter);
    public void mostrarCursos(List<Curso> cursos);
}
