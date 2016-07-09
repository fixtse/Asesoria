package com.ulima.sw.Asesorias.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoCursosAdapter;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Sesion;
import com.ulima.sw.Asesorias.cursos.cursosPresenter;
import com.ulima.sw.Asesorias.cursos.profesorCursosPresenterImpp;
import com.ulima.sw.Asesorias.cursos.cursosView;
import com.ulima.sw.Asesorias.cursos.alumnoCursosPresenterImp;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fixt on 08/07/16.
 */
public class fragCursos extends ListFragment implements cursosView {


    private cursosPresenter lPresenter;
    private ListView lstCursos;
    private ProgressDialog dialog;
    private Sesion ses;



    public fragCursos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Listado Cursos");
        //setHasOptionsMenu(true); // Seteo que el fragment va a tener su propio menu de opciones


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ;


        return inflater.inflate(R.layout.frag_cursos, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ses = new Sesion(getContext());
        ses.checkLogin();

        // get user data from session
        HashMap<String, String> user = ses.getUserDetails();

        String tipo = user.get(ses.KEY_TIPO);

        lstCursos = (ListView)getView().findViewById(android.R.id.list);

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        if (tipo.equals("1")){
            setPresenter(new alumnoCursosPresenterImp(this));
        }else if(tipo.equals("2")){
            setPresenter(new profesorCursosPresenterImpp(this));
        }

        lPresenter.obtenerCursos();
    }

    @Override
    public void setPresenter(cursosPresenter presenter) {
        this.lPresenter = presenter;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/



    @Override
    public void mostrarCursos(final List<Curso> cursos) {

        ListadoCursosAdapter adapter = new ListadoCursosAdapter(cursos,getContext());
        lstCursos.setAdapter(adapter);

        dialog.dismiss();
        /*lstPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoCursosActivity.this, cursosActivity.class);
                intent.putExtra("ingredientes",(Serializable)Pizzas.get(position).getIng());
                startActivity(intent);

            }

        });*/
    }




}