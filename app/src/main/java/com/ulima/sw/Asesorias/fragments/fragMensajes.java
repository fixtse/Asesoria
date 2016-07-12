package com.ulima.sw.Asesorias.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ulima.sw.Asesorias.Mensajes.MensajesPresenter;
import com.ulima.sw.Asesorias.Mensajes.MensajesPresenterImp;
import com.ulima.sw.Asesorias.Mensajes.MensajesView;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoMensajesAdapter;
import com.ulima.sw.Asesorias.asebeans.Mensaje;
import com.ulima.sw.Asesorias.asebeans.Sesion;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fixt on 08/07/16.
 */
public class fragMensajes extends Fragment implements MensajesView {

    private ListadoMensajesAdapter listAdapter;
    private MensajesPresenter Lpresenter;
    private ListView Lview;
    private List<Mensaje> Mensajes;
    private ProgressDialog dialog;
    private Sesion ses;

    /**
     * A simple {@link Fragment} subclass.
     */



    public fragMensajes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Mensajes");
        //setHasOptionsMenu(true); // Seteo que el fragment va a tener su propio menu de opciones
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_mensajes, container, false);
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
        Lview = (ListView) getView().findViewById(R.id.lstMensajes);

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        setPresenter(new MensajesPresenterImp(this));

        Lpresenter.obtenerMensajes();

    }

    @Override
    public void setPresenter(MensajesPresenter presenter) {
        this.Lpresenter = presenter;
    }

    @Override
    public void mostrarMensajes(List<Mensaje> mensajes) {

        this.Mensajes = mensajes;

        listAdapter = new ListadoMensajesAdapter(getContext(), mensajes);
        // setting list adapter
        Lview.setAdapter(listAdapter);

        dialog.dismiss();

    }

    /*@Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);

            inflater.inflate(R.menu.menu_main, menu);

        }*/


}
