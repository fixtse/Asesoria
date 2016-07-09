package com.ulima.sw.Asesorias.cursos;

import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.cursos.cursosView;
import com.ulima.sw.Asesorias.cursos.cursosPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/05/2016.
 */
public class alumnoCursosPresenterImp implements cursosPresenter {

    private cursosView lview;

    public alumnoCursosPresenterImp(cursosView lview) {
        this.lview = lview;
    }


    @Override
    public void obtenerCursos() {

        /*Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://pizzaplanetac.mybluemix.net/webresources/generic/")
                .baseUrl("http://pizzaplanetac.mybluemix.net/webresources/generic/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PizzaPService service = retrofit.create(PizzaPService.class);
        service.obtenerPizzas(1).enqueue(new Callback<List<Pizza>>() {
            @Override
            public void onResponse(Call<List<Pizza>> call, Response<List<Pizza>> response) {
                lview.mostrarPizzas(response.body());
            }

            @Override
            public void onFailure(Call<List<Pizza>>  call, Throwable t) {

            }
        });*/

        List<Curso> cursos = new ArrayList<>();
        Curso c1 = new Curso(1,"ING. SOFT. II", 801);
        Curso c2 = new Curso(2,"PLAN.RED.TELE.", 802);
        Curso c3 = new Curso(3,"PROG.DISP.MÓVILES", 801);
        Curso c4 = new Curso(4,"PROY.SIS.INFO", 1001);
        Curso c5 = new Curso(5,"SIST.INTEL.EMP.", 802);

        cursos.add(c1);
        cursos.add(c2);
        cursos.add(c3);
        cursos.add(c4);
        cursos.add(c5);

        lview.mostrarCursos(cursos);

    }


}
