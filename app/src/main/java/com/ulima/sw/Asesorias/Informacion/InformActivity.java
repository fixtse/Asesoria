package com.ulima.sw.Asesorias.Informacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.beans.Pedido;


public class InformActivity extends AppCompatActivity implements InformView {
    ImageView imgE;
    InformView iView;
    TextView txtEquipo,txtPG,txtPP;
    InformPresenter Ipresenter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Información");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_informacion);
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        setPresenter(new InformPresenterImp(this));
        Intent intentPasado = getIntent();
        Ipresenter.obtenerEquipo(intentPasado.getIntExtra("id",0));
    }

    @Override
    public void setPresenter(InformPresenter presenter) {
        Ipresenter = presenter;
    }

    @Override
    public void mostrarPedido(Pedido equipo) {

       /* imgE = (ImageView)findViewById(R.id.imgE);
        txtEquipo = (TextView)findViewById(R.id.txtOrden);
        txtPG = (TextView)findViewById(R.id.txtPP);
        txtPP = (TextView)findViewById(R.id.txtPG);

        txtEquipo.setText("Equipo " + equipo.getNombre());
        txtPG.setText("Partidos Ganados: "+equipo.getPartidosGanados());
        txtPP.setText("Partidos Perdidos: "+equipo.getPartidosPerdidos());
        Picasso.with(this)
                .load(equipo.getUrlFoto())
                .fit()
                .centerCrop()
                .into(imgE);
        dialog.dismiss();*/

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
