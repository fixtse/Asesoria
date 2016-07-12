package com.ulima.sw.Asesorias.cursos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulima.sw.Asesorias.asebeans.Asesoria;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Estado;
import com.ulima.sw.Asesorias.cursos.cursosView;
import com.ulima.sw.Asesorias.cursos.cursosPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/05/2016.
 */
public class alumnoCursosPresenterImp implements cursosPresenter {

    private cursosView lview;
    private FirebaseDatabase database;

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
        Estado e1 = new Estado("",1,"Libre");
        Estado e2 = new Estado("",2,"Ocupado");
        Estado e3 = new Estado("",0,"No disponible");
        List<Asesoria> asesorias = new ArrayList<>();
        Asesoria a1 = new Asesoria("Lunes","18:00","S-160",e1);
        Asesoria a2 = new Asesoria("Jueves","09:00","G-102",e2);
        Asesoria a3 = new Asesoria("Viernes","14:00","W-502",e3);

        List<String> alum = new ArrayList<>();
        String idAl1 = "0";
        String idAl2 = "1";

        alum.add(idAl1);
        alum.add(idAl2);

        a1.setAlumnos(alum);
        a2.setAlumnos(alum);
        a3.setAlumnos(alum);

        asesorias.add(a1);
        asesorias.add(a2);
        asesorias.add(a3);

        List<Curso> cursos = new ArrayList<>();
        Curso c1 = new Curso(0,"ING. SOFT. II", 801,asesorias);
        Curso c2 = new Curso(1,"PLAN.RED.TELE.", 802,asesorias);
        Curso c3 = new Curso(2,"PROG.DISP.MÓVILES", 801,asesorias);
        Curso c4 = new Curso(3,"PROY.SIS.INFO", 1001,asesorias);
        Curso c5 = new Curso(4,"SIST.INTEL.EMP.", 802,asesorias);

        cursos.add(c1);
        cursos.add(c2);
        cursos.add(c3);
        cursos.add(c4);
        cursos.add(c5);

       /* database = FirebaseDatabase.getInstance();
        final DatabaseReference loginReference = database.getReference().child("cursillos");
        loginReference.setValue(cursos);*/

        lview.mostrarCursos(cursos);

    }


}
