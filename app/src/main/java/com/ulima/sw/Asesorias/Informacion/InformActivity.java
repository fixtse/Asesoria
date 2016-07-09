package com.ulima.sw.Asesorias.Informacion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Sesion;


public class InformActivity extends AppCompatActivity implements InformView {
    private ImageView imgE;
    private InformView iView;
    private TextView txtEstado,txtLugar,txtHora,txtCal;
    private ProgressDialog dialog;
    private Curso curso;
    private int pos;
    private Sesion ses;
    private Intent intentPasado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ses = new Sesion(this);
        ses.checkLogin();

        intentPasado = getIntent();
        curso = (Curso)intentPasado.getSerializableExtra("curso");
        pos = intentPasado.getIntExtra("child",0);

        setTitle(curso.getNombre());





        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_informacion);
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        mostrarAsesoria();

        //setPresenter(new InformPresenterImp(this));

        //Ipresenter.obtenerEquipo(intentPasado.getIntExtra("id",0));
    }

    /*@Override
    public void setPresenter(InformPresenter presenter) {
        Ipresenter = presenter;
    }

    @Override*/
    public void mostrarAsesoria() {

        imgE = (ImageView)findViewById(R.id.iEstado);
        txtEstado = (TextView)findViewById(R.id.txtEstado);
        txtLugar = (TextView)findViewById(R.id.tLugar);
        txtHora = (TextView)findViewById(R.id.tHora);
        txtCal = (TextView)findViewById(R.id.tCal);

        //imgE.setImageResource(android.R.drawable.ic_media_play);

        txtEstado.setText(curso.getAsesorias().get(pos).getEstado().getEstado());

       txtLugar.setText("Salón: "+curso.getAsesorias().get(pos).getLugar());
        txtHora.setText("Hora: " +curso.getAsesorias().get(pos).getHora());
        txtCal.setText("Calificación: "+curso.getAsesorias().get(pos).getCalific());

        dialog.dismiss();

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
                notification1(1,R.drawable.notif,curso.getNombre(),curso.getAsesorias().get(pos).getLugar()+" - "+curso.getAsesorias().get(pos).getEstado().getEstado());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onMensaje(View view){

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
                        .setColor(getResources().getColor(R.color.colorAccent));


        NotificationManager notifyMgr = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);


        // Construir la notificación y emitirla
        notifyMgr.notify(id, builder.build());
    }
}
