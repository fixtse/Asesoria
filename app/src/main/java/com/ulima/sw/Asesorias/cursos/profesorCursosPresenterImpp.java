package com.ulima.sw.Asesorias.cursos;

import com.ulima.sw.Asesorias.asebeans.Asesoria;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.cursos.cursosView;
import com.ulima.sw.Asesorias.cursos.cursosPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fixt on 15/06/16.
 */
public class profesorCursosPresenterImpp implements cursosPresenter {

    private cursosView lview;

    public profesorCursosPresenterImpp(cursosView lview) {
        this.lview = lview;
    }

    @Override
    public void obtenerCursos() {

        List<Asesoria> asesorias = new ArrayList<>();
        Asesoria a1 = new Asesoria("Lunes","18:00","S-160");
        Asesoria a2 = new Asesoria("Jueves","09:00","G-102");
        Asesoria a3 = new Asesoria("Viernes","14:00","W-502");

        asesorias.add(a1);
        asesorias.add(a2);
        asesorias.add(a3);

        List<Curso> cursos = new ArrayList<>();
        Curso c1 = new Curso(1,"ING. SOFT. II", 801,asesorias);
        Curso c2 = new Curso(2,"PROG. INTERNET", 602,asesorias);
        Curso c3 = new Curso(3,"PROG.DISP.MÓVILES", 801,asesorias);


        cursos.add(c1);
        cursos.add(c2);
        cursos.add(c3);


        lview.mostrarCursos(cursos);

    }
}