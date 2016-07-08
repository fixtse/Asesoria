package com.ulima.sw.Asesorias.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoProfesorAdapter;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.lProfesor.lProfesorPresenter;
import com.ulima.sw.Asesorias.lProfesor.lProfesorPresenterImp;
import com.ulima.sw.Asesorias.lProfesor.lProfesorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fixt on 08/07/16.
 */
public class fragCursos extends ListFragment implements lProfesorView {


    private lProfesorPresenter lPresenter;
    private ListView lstCursos;
    private ProgressDialog dialog;



    public fragCursos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Cursos");
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

        lstCursos = (ListView)getView().findViewById(android.R.id.list);

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();



        setPresenter(new lProfesorPresenterImp(this));
        lPresenter.obtenerCursos();
    }

    @Override
    public void setPresenter(lProfesorPresenter presenter) {
        this.lPresenter = presenter;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/

    @Override
    public void mostrarCursos(final List<Curso> cursos) {
        //ListFragment.setListAdapter;
        ListadoProfesorAdapter adapter = new ListadoProfesorAdapter(cursos,getContext());
        lstCursos.setAdapter(adapter);

        dialog.dismiss();
        /*lstPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoCursosActivity.this, lProfesorActivity.class);
                intent.putExtra("ingredientes",(Serializable)Pizzas.get(position).getIng());
                startActivity(intent);

            }

        });*/
    }




}