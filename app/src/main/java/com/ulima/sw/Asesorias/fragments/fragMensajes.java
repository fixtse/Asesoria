package com.ulima.sw.Asesorias.fragments;

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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulima.sw.Asesorias.Mensajes.MensajesPresenter;
import com.ulima.sw.Asesorias.Mensajes.MensajesPresenterImp;
import com.ulima.sw.Asesorias.Mensajes.MensajesView;
import com.ulima.sw.Asesorias.Mensajes.NewMensajeActivity;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoMensajesAdapter;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Mensaje;
import com.ulima.sw.Asesorias.asebeans.Sesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private FirebaseDatabase database;
    private String tipo;

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

        tipo = user.get(ses.KEY_TIPO);


        // get the listview
        Lview = (ListView) getView().findViewById(R.id.lstMensajes);

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        /*setPresenter(new MensajesPresenterImp(this));

        Lpresenter.obtenerMensajes();*/
        obtenerMensajes(tipo);

    }

    @Override
    public void setPresenter(MensajesPresenter presenter) {
        this.Lpresenter = presenter;
    }

    public void obtenerMensajes(String tipo){
        database = FirebaseDatabase.getInstance();
        String ref,ref2;
        if (tipo.equals("1")){
            ref = "alumnos";
        }else{
            ref = "profesores";
        }

        DatabaseReference CursosRef = database.getReference().child("mensajes");

        if (tipo.equals("1")){
            ref2 = "idAlumno";
        }else{
            ref2 = "idProf";
        }

        CursosRef.orderByChild(ref2).equalTo(ses.getID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Mensajes = new ArrayList<Mensaje>();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Mensaje men = ds.getValue(Mensaje.class);
                    Mensajes.add(men);
                }
                if (Mensajes != null) {
                    mostrarMensajes(Mensajes);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    @Override
    public void mostrarMensajes(final List<Mensaje> mensajes) {

        this.Mensajes = mensajes;
        if (getActivity() != null) {
            // Code goes here.
            listAdapter = new ListadoMensajesAdapter(getContext(), mensajes,tipo);
            // setting list adapter
            Lview.setAdapter(listAdapter);
            Lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(getActivity(),NewMensajeActivity.class);
                    intent.putExtra("id",Mensajes.get(i).getId());



                    getActivity().startActivity(intent);

                }
            });
        }


        dialog.dismiss();



    }



    /*@Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);

            inflater.inflate(R.menu.menu_main, menu);

        }*/


}
