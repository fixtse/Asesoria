package com.ulima.sw.Asesorias.Mensajes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.MensajeAdapter;
import com.ulima.sw.Asesorias.asebeans.Mensaje;
import com.ulima.sw.Asesorias.asebeans.Sesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewMensajeActivity extends AppCompatActivity {


    private MensajeAdapter adapter;
    private Sesion ses;
    private Intent intentPasado;
    private Mensaje mensaje;
    private ProgressDialog dialog;
    private FirebaseDatabase database;
    private ListView lcontenidos;
    private EditText Econtenido;
    private Long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ses = new Sesion(this);
        ses.checkLogin();

        intentPasado = getIntent();

        id = intentPasado.getLongExtra("id",0);
        System.out.println(id);


        setContentView(R.layout.activity_new_mensaje);

        lcontenidos = (ListView) findViewById(R.id.lstContenido);
        Econtenido = (EditText)findViewById(R.id.ediMensaje);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        obtenerContenido(id);




    }

    public void obtenerContenido(Long id){


        database = FirebaseDatabase.getInstance();
        final DatabaseReference mensajesref = database.getReference().child("mensajes");

        mensajesref.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Mensaje mensaje = ds.getValue(Mensaje.class);
                    mostrarContenido(mensaje);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    public void mostrarContenido(Mensaje mensaje){

        this.mensaje = mensaje;
        setTitle(mensaje.getCurso());
        adapter = new MensajeAdapter(NewMensajeActivity.this,mensaje.getContenidos());
        lcontenidos.setAdapter(adapter);
        dialog.dismiss();
    }

    public void onMensaje(View view){
        HashMap<String, String> user = ses.getUserDetails();

        // name
        final String name = user.get(ses.KEY_NAME);

        final DatabaseReference contenidoref = database.getReference().child("mensajes");
        contenidoref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Mensaje> mensajes = new ArrayList<Mensaje>();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Mensaje me = ds.getValue(Mensaje.class);
                    mensajes.add(me);

                }

                String contenido = "<b>"+name.toUpperCase()+":</b> "+Econtenido.getText();

                List<String> conte = mensajes.get(id.intValue()).getContenidos();
                conte.add(contenido);
                mensajes.get(id.intValue()).setContenidos(conte);
                contenidoref.setValue(mensajes);
                Econtenido.setText("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    Log.i("TAG", "You have forgotten to specify the parentActivityName in the AndroidManifest!");
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
