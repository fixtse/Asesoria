package com.ulima.sw.Asesorias.Mensajes;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulima.sw.Asesorias.asebeans.Mensaje;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Diego Torres on 15/05/2016.
 */
public class MensajesPresenterImp implements MensajesPresenter {

    private MensajesView mensajesViewTT;
    private FirebaseDatabase database;

    public MensajesPresenterImp(MensajesView view){
        this.mensajesViewTT =view;
    }


    @Override
    public void obtenerMensajes() {

        List<String> contenidos = new ArrayList<>();
        contenidos.add("Este es un lindo mensajito :3 :3 :3");
        contenidos.add("Este es un lindo mensajito :3 :3 pero mas largo o oOO oo :C :) ^^ ¬°¬  :3 asdasdasdasdas");

        Mensaje mensaje = new Mensaje(contenidos,"1","D.I.S.P",0,"Diego","prof",1L);

        Mensaje mensaje2 = new Mensaje(contenidos,"1","D.I.S.P",0,"Diego","prof",1L);
        List<Mensaje> mensajes = new ArrayList<>();

        mensajes.add(mensaje);
        mensajes.add(mensaje2);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference loginReference = database.getReference().child("mensajes");
        loginReference.setValue(mensajes);

        mensajesViewTT.mostrarMensajes(mensajes);



    }
}
