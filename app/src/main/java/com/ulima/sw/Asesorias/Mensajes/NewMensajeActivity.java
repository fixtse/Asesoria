package com.ulima.sw.Asesorias.Mensajes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.MensajeAdapter;
import com.ulima.sw.Asesorias.asebeans.Mensaje;
import com.ulima.sw.Asesorias.asebeans.Sesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewMensajeActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {


    private MensajeAdapter adapter;
    private Sesion ses;
    private Intent intentPasado;
    private Mensaje mensaje;
    private ProgressDialog dialog;
    private FirebaseDatabase database;
    private ObservableListView lcontenidos;
    private EditText Econtenido;
    private Long id;
    private Vibrator v;
    private DatabaseReference vibref;
    private int sender;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ses = new Sesion(this);
        ses.checkLogin();

        intentPasado = getIntent();

        id = intentPasado.getLongExtra("id",0);
        System.out.println(id);


        setContentView(R.layout.activity_new_mensaje);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lcontenidos = (ObservableListView) findViewById(R.id.lstContenido);
        lcontenidos.setScrollViewCallbacks(this);
        Econtenido = (EditText)findViewById(R.id.ediMensaje);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        sender = 0;
        obtenerContenido(id);

        v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        obtenerContenido(id);

        ShakeDetector.create(getApplicationContext(), new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {


                sender = 1;
                if (vibref != null){
                    vibref.setValue(1);

                }

            }
        });

        ShakeDetector.updateConfiguration(3.24f,2);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#273e57"));




    }

    @Override
    protected void onResume() {
        super.onResume();
        ShakeDetector.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShakeDetector.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShakeDetector.destroy();
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
                    vibref = database.getReference().child("mensajes").child(ds.getKey()).child("vib");
                    mostrarContenido(mensaje);
                    if(mensaje.getVib()==1){
                       // if(sender ==0){
                            v.vibrate(500);
                            vibref.setValue(0);
                       // }
                       // sender = 1;

                    }
                    //notification1(2, R.drawable.mensj, "Nuevo Mensaje", mensaje.getContenidos().get(mensaje.getContenidos().size()-1)));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void scrollMyListViewToBottom() {
        lcontenidos.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                lcontenidos.setSelection(adapter.getCount() - 1);

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

        final DatabaseReference mensajesref = database.getReference().child("mensajes");
        mensajesref.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds : dataSnapshot.getChildren())
                {
                    final DatabaseReference contenidoref = database.getReference().child("mensajes").child(ds.getKey()).child("contenidos");
                    contenidoref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //Mensaje me = dataSnapshot.getValue(Mensaje.class);
                            List<String> conte = new ArrayList<String>();
                            for (DataSnapshot ds : dataSnapshot.getChildren())
                            {
                                String me = ds.getValue(String.class);
                                conte.add(me);


                            }

                            String contenido = "<b>"+name.toUpperCase()+":</b> "+Econtenido.getText();

                            conte.add(contenido);

                            contenidoref.setValue(conte);
                            //contenidoref.updateChildren()
                            Econtenido.setText("");
                            //lcontenidos.smoothScrollToPosition(adapter.getCount() -1);
                            scrollMyListViewToBottom();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void notification1(int id, int iconId, String titulo, String contenido) {


        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intentPasado, 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(iconId)
                        .setLargeIcon(BitmapFactory.decodeResource(
                                getResources(),
                                R.drawable.ulima
                                )
                        )
                        .setContentTitle(titulo)
                        .setContentIntent(pIntent)
                        .setContentText(contenido)
                        .setAutoCancel(true)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setColor(getResources().getColor(R.color.accent));


        NotificationManager notifyMgr = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);


        // Construir la notificación y emitirla
        notifyMgr.notify(id, builder.build());
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

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (ab == null) {
            return;
        }
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
                //fab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
                //fab.show();
            }
        }
    }
}
