package com.ulima.sw.Asesorias.listado;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoCursosAdapter;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Sesion;

import java.util.HashMap;
import java.util.List;

public class ListadoCursosActivity extends AppCompatActivity implements ListadoCursosView, ObservableScrollViewCallbacks {

    private ListadoCursosPresenter lPresenter;
    private ObservableListView lstPizzas;
    private ProgressDialog dialog;
    private int pos;
    private String usuario;
    private ActionBar supportActionBar;
    private Sesion ses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Listado Cursos");


        ses = new Sesion(getApplicationContext());
        ses.checkLogin();

        // get user data from session
        HashMap<String, String> user = ses.getUserDetails();

        // name
        String name = user.get(ses.KEY_NAME);

        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intentPasado = getIntent();

        setContentView(R.layout.activity_listado_cursos);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        lstPizzas = (ObservableListView)findViewById(R.id.lstCursos);
        lstPizzas.setScrollViewCallbacks(this);

        setPresenter(new ListadoCursosPresenterImp(this));

        usuario = intentPasado.getStringExtra("usuario");
        lPresenter.obtenerCursos(usuario);


    }

    @Override
    public void setPresenter(ListadoCursosPresenter presenter) {
        this.lPresenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void mostrarCursos(final List<Curso> cursos) {
        ListadoCursosAdapter adapter = new ListadoCursosAdapter(cursos,this);
        lstPizzas.setAdapter(adapter);
        dialog.dismiss();
        /*lstPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoCursosActivity.this, IngredientesActivity.class);
                intent.putExtra("ingredientes",(Serializable)Pizzas.get(position).getIng());
                startActivity(intent);

            }

        });*/
    }


    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    Log.i("TAG", "You have forgotten to specify the parentActivityName in the AndroidManifest!");
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            case R.id.men_op1:
               // lPresenter.actualizarEstado(pos,usuario);
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

        if (supportActionBar == null) {
            return;
        }
        if (scrollState == ScrollState.UP) {
            if (supportActionBar.isShowing()) {
                supportActionBar.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!supportActionBar.isShowing()) {
                supportActionBar.show();
            }
        }

    }
}
