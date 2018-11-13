package com.ulima.sw.Asesorias.Informacion;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.melnykov.fab.FloatingActionButton;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Mensaje;
import com.ulima.sw.Asesorias.asebeans.Sesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class InformActivity extends AppCompatActivity implements InformView{

    private ImageView imgE;
    private TextView txtEstado,txtLugar,txtHora,txtCal;
    private ProgressDialog dialog;
    private Curso curso;
    private int pos;
    private Sesion ses;
    private FloatingActionButton fab;
    private Intent intentPasado;
    private FirebaseDatabase database;
    private DatabaseReference CursosRef;
    private Menu menu;

    private int idmen;
    private TextView mText;
    private ImageView imageView;
    private int precantAlm;
    private Toolbar toolbar;
    private int estadoA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ses = new Sesion(this);
        ses.checkLogin();

        intentPasado = getIntent();
        int id = intentPasado.getIntExtra("curso",0);


        pos = intentPasado.getIntExtra("child",0);
        idmen = intentPasado.getIntExtra("siguiendo",0);

        precantAlm = -1;
        estadoA = -1;

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#273e57"));



        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        obtenerCurso(id);

    }



    public void onFav(View view){

        displayInputDialog(curso.getAsesorias().get(pos).getEstado().getId());

    }

    private void displayInputDialog(final int estado)
    {
        if (estado == 0){
            Toast.makeText(InformActivity.this, "El profesor no se encuentra disponible", Toast.LENGTH_SHORT).show();
        }else{
            final Dialog d=new Dialog(this);
            d.setTitle("Para: "+curso.getAsesorias().get(pos).getProfesor());
            d.setContentView(R.layout.input_dialog);
            final EditText ContenidoTxt= (EditText) d.findViewById(R.id.Contenido);
            Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

            //Instancia de Firebase
            database = FirebaseDatabase.getInstance();
            //SAVE
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //GET DATA
                    String contenido=ContenidoTxt.getText().toString();

                    if(contenido != null && contenido.trim().length()>0)
                    {
                        final List<String> contenidos = new ArrayList<String>();
                        HashMap<String, String> user = ses.getUserDetails();

                        // name
                        final String name = user.get(ses.KEY_NAME);
                        contenido = "<b>"+name.toUpperCase()+":</b> "+contenido;
                        contenidos.add(contenido);

                        final DatabaseReference mensajesref = database.getReference().child("mensajes");
                        mensajesref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Long id = dataSnapshot.getChildrenCount();

                                Mensaje mje= new Mensaje(contenidos,id,curso.getNombre(),curso.getAsesorias().get(pos).getIdProf(),name,curso.getAsesorias().get(pos).getProfesor(),ses.getID());

                                mensajesref.push().setValue(mje);
                                ContenidoTxt.setText("");
                                if (d.isShowing()){
                                    d.dismiss();
                                }
                                if (estado == 1){
                                    Toast.makeText(InformActivity.this, "El mensaje ha sido enviado", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(InformActivity.this, "Mensaje enviado, El profesor se encuentra ocupado", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }else
                    {
                        Toast.makeText(InformActivity.this, "El mensaje no puede estar vacío", Toast.LENGTH_SHORT).show();
                    }
                }

            });

            d.show();

        }

    }


    public void estadoA(View view, int est){
        if (est == 0){
            imgE.setImageResource(R.drawable.rojo);
        }else if (est == 1){
            imgE.setImageResource(R.drawable.verde);
        }else{
            imgE.setImageResource(R.drawable.amarillo);
        }

    }

    public void mostrarAsesoria() {

        imgE = (ImageView)findViewById(R.id.iEstado);
        txtEstado = (TextView)findViewById(R.id.txtEstado);
        txtLugar = (TextView)findViewById(R.id.tLugar);
        txtHora = (TextView)findViewById(R.id.tHora);
        txtCal = (TextView)findViewById(R.id.tCal);
        switch (curso.getAsesorias().get(pos).getEstado().getId()){
            case 0:
                if (estadoA != 0){
                     estadoA(imgE,0);
                }
                estadoA=0;
                break;
            case 1:
                if (estadoA != 1){
                    estadoA(imgE,1);
                }
                estadoA=1;
                break;
            case 2:
                if (estadoA != 2){
                    estadoA(imgE,2);
                }
                estadoA=2;
                break;
        }

        HashMap<String, String> user = ses.getUserDetails();

        String tipo = user.get(ses.KEY_TIPO);
        if (tipo != null){
            if (!tipo.equals("1")){

                fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.hide();

            }
        }

        List <String> alms = curso.getAsesorias().get(pos).getAlumnos();
        mText = (TextView)findViewById(R.id.CantAlm);
        if (alms != null){
            if (alms.contains("")){
                alms.remove("");
            }
            mText.setText(String.valueOf(alms.size()));
            if (precantAlm != alms.size()){
                redText(findViewById(R.id.CantAlm));
                precantAlm=alms.size();
            }

        }else{
            mText.setText("0");
            if (precantAlm != 0){
                redText(findViewById(R.id.CantAlm));
                precantAlm=0;
            }
        }
        txtEstado.setText(curso.getAsesorias().get(pos).getEstado().getEstado());
        txtLugar.setText("Salón: "+curso.getAsesorias().get(pos).getLugar());
        txtHora.setText("Hora: " +curso.getAsesorias().get(pos).getHora());
        txtCal.setText("Calificación: "+curso.getAsesorias().get(pos).getCalific());

        dialog.dismiss();

    }

    //Mostrar animación nuevos alumnos
    public void redText(View view){
        mText.setTextColor(0xff00ffff);


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        HashMap<String, String> user = ses.getUserDetails();

        String tipo = user.get(ses.KEY_TIPO);
        if (tipo != null){
            if (tipo.equals("1")) {
                if (idmen == 1) {
                    MenuItem men = menu.findItem(R.id.men_op1);
                    if ( men != null) {
                        men.setIcon(android.R.drawable.checkbox_on_background);
                    }
                }
            }
        }

        return super.onPrepareOptionsMenu(menu);
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

            case R.id.men_op1:
                if (curso.getAsesorias().get(pos).getAlumnos() != null) {
                    List<String> alm = curso.getAsesorias().get(pos).getAlumnos();
                    if (!alm.contains(ses.getID().toString())){
                        alm.add(ses.getID().toString());
                        curso.getAsesorias().get(pos).setAlumnos(alm);
                        CursosRef.setValue(curso);
                        if (alm.contains("")){
                            alm.remove("");
                        }
                        menu.findItem(R.id.men_op1).setIcon(android.R.drawable.checkbox_on_background);
                        Toast.makeText(this, "Siguiendo Asesoria", Toast.LENGTH_SHORT).show();

                    }else{
                        alm.remove(ses.getID().toString());
                        curso.getAsesorias().get(pos).setAlumnos(alm);
                        CursosRef.setValue(curso);
                        menu.findItem(R.id.men_op1).setIcon(android.R.drawable.checkbox_off_background);
                        Toast.makeText(this, "Dejaste de seguir la Asesoria", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    List<String> alm = new ArrayList<>();
                    alm.add(ses.getID().toString());
                    curso.getAsesorias().get(pos).setAlumnos(alm);
                    CursosRef.setValue(curso);
                    menu.findItem(R.id.men_op1).setIcon(android.R.drawable.checkbox_on_background);
                    Toast.makeText(this, "Siguiendo Asesoria", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.men_op2:
                Float a = curso.getAsesorias().get(pos).getCalific();
                a = a +1;
                //a= a/curso.getAsesorias().get(pos).getAlumnos().size();
                curso.getAsesorias().get(pos).setCalific(a);
                CursosRef.setValue(curso);
                break;
            case R.id.men_p1:
                curso.getAsesorias().get(pos).getEstado().setId(1);
                curso.getAsesorias().get(pos).getEstado().setEstado("Libre");
                CursosRef.setValue(curso);
                break;

            case R.id.men_p2:
                curso.getAsesorias().get(pos).getEstado().setId(2);
                curso.getAsesorias().get(pos).getEstado().setEstado("Ocupado");
                CursosRef.setValue(curso);
                break;

            case R.id.men_p3:
                List<String> alm1 = new ArrayList<>();
                alm1.add("");
                curso.getAsesorias().get(pos).setAlumnos(alm1);
                curso.getAsesorias().get(pos).getEstado().setId(0);
                curso.getAsesorias().get(pos).getEstado().setEstado("No Disponible");
                curso.getAsesorias().get(pos).setCalific(0);
                precantAlm = 0;
                CursosRef.setValue(curso);
                break;



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        HashMap<String, String> user = ses.getUserDetails();

        String tipo = user.get(ses.KEY_TIPO);
        if (tipo != null){
            if (tipo.equals("1")){
                getMenuInflater().inflate(R.menu.menu, menu);
                MenuItem item = menu.getItem(0);
                imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.heart_off);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Float a = curso.getAsesorias().get(pos).getCalific();
                        a = a +1;
                        //a= a/curso.getAsesorias().get(pos).getAlumnos().size();
                        curso.getAsesorias().get(pos).setCalific(a);
                        CursosRef.setValue(curso);
                        like(v);
                    }
                });
                item.setActionView(imageView);
            }else{
                getMenuInflater().inflate(R.menu.menu2, menu);
            }
        }else{
            getMenuInflater().inflate(R.menu.menu, menu);
        }


        this.menu = menu;
        return true;
    }

    public void like(View view){
        imageView.setImageResource(R.drawable.heart_on);

    }



    public void notification1(int id, int iconId, String titulo, String contenido) {

        //Intent intent = new Intent(this, NotificationReceiverActivity.class);
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

    public void obtenerCurso(int id){
        database = FirebaseDatabase.getInstance();
        CursosRef = database.getReference().child("cursillos").child(Integer.toString(id));


        CursosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                curso = dataSnapshot.getValue(Curso.class);
                setTitle(curso.getNombre());
                mostrarAsesoria();
                HashMap<String, String> user = ses.getUserDetails();

                String tipo = user.get(ses.KEY_TIPO);
                if (tipo != null) {
                    if (tipo.equals("1")) {
                        Long idEstado = ses.getEstado();
                        int drt = intentPasado.getIntExtra("child", 0);
                        if (curso.getAsesorias().get(drt).getAlumnos() != null) {
                            if (curso.getAsesorias().get(drt).getAlumnos().contains(ses.getID().toString())) {
                                if (curso.getAsesorias().get(drt).getEstado().getId() != idEstado) {
                                    if (idEstado != -1L) {
                                        notification1(1, R.drawable.notif, curso.getNombre(), curso.getAsesorias().get(drt).getLugar() + " - " + curso.getAsesorias().get(drt).getEstado().getEstado());
                                    }
                                    ses.actEstado(Long.valueOf(curso.getAsesorias().get(drt).getEstado().getId()));
                                }
                            }
                        }


                    }


                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }


}
