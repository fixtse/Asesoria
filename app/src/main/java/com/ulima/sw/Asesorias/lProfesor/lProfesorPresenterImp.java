package com.ulima.sw.Asesorias.lProfesor;

import com.ulima.sw.Asesorias.asebeans.Curso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fixt on 15/06/16.
 */
public class lProfesorPresenterImp implements lProfesorPresenter {

    private lProfesorView lview;

    public lProfesorPresenterImp(lProfesorView lview) {
        this.lview = lview;
    }

    @Override
    public void obtenerCursos() {

        List<Curso> cursos = new ArrayList<>();
        Curso c1 = new Curso(1,"ING. SOFT. II", 801);
        Curso c2 = new Curso(2,"PROG. INTERNET", 602);
        Curso c3 = new Curso(3,"PROG.DISP.MÓVILES", 801);


        cursos.add(c1);
        cursos.add(c2);
        cursos.add(c3);


        lview.mostrarCursos(cursos);

    }
}
