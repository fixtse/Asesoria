package com.ulima.sw.Asesorias.Mensajes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


import com.google.firebase.database.DatabaseReference;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.MensajeAdapter;
import com.ulima.sw.Asesorias.asebeans.Mensaje;
import com.ulima.sw.Asesorias.asebeans.Sesion;

public class NewMensajeActivity extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    MensajeAdapter adapter;
    private Sesion ses;
    private Intent intentPasado;
    private Mensaje mensaje;
    private ProgressDialog dialog;
    ListView lcontenidos;
    EditText nameEditTxt,propTxt,descTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mensaje);

        ses = new Sesion(this);
        ses.checkLogin();

        intentPasado = getIntent();



        setContentView(R.layout.activity_informacion);

        lcontenidos = (ListView) findViewById(R.id.lstContenido);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        mensaje = (Mensaje) intentPasado.getSerializableExtra("mensaje");
        if (mensaje != null){
            mostrarContenido();
        }else{
            obtenerContenido();
        }



    }

    public void obtenerContenido(){

    }

    public void mostrarContenido(){

    }

    public void onMensaje(View view){

    }
}
