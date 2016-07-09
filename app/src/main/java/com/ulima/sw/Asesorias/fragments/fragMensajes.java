package com.ulima.sw.Asesorias.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulima.sw.Asesorias.R;

/**
 * Created by fixt on 08/07/16.
 */
public class fragMensajes extends Fragment {

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
    }

    @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);

            inflater.inflate(R.menu.menu_main, menu);

        }


}
