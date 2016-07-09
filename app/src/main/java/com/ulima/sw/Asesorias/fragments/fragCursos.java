package com.ulima.sw.Asesorias.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoExpansibleCursosAdapter;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Sesion;
import com.ulima.sw.Asesorias.cursos.alumnoCursosPresenterImp;
import com.ulima.sw.Asesorias.cursos.cursosPresenter;
import com.ulima.sw.Asesorias.cursos.cursosView;
import com.ulima.sw.Asesorias.Informacion.InformActivity;
import com.ulima.sw.Asesorias.cursos.profesorCursosPresenterImpp;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fixt on 08/07/16.
 */
public class fragCursos extends Fragment implements cursosView {

    private ListadoExpansibleCursosAdapter listAdapter;
    private ExpandableListView expListView;
    private cursosPresenter lPresenter;
    private Sesion ses;
    private ProgressDialog dialog;
    private List<Curso>
            Tcursos;
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
        return inflater.inflate(R.layout.frag_cursos, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ses = new Sesion(getContext());
        ses.checkLogin();

        // get user data from session
        HashMap<String, String> user = ses.getUserDetails();

        String tipo = user.get(ses.KEY_TIPO);

        // get the listview
        expListView = (ExpandableListView) getView().findViewById(R.id.lvExp);

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
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getActivity(), InformActivity.class);
                intent.putExtra("curso",Tcursos.get(groupPosition));
                intent.putExtra("child",childPosition);
                getActivity().startActivity(intent);
                return false;
            }
        });
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public void setPresenter(cursosPresenter presenter) {
        this.lPresenter = presenter;
    }

    @Override
    public void mostrarCursos(List<Curso> cursos) {
        Tcursos = cursos;
        listAdapter = new ListadoExpansibleCursosAdapter(getContext(), cursos);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        dialog.dismiss();

    }


}
