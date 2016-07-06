package com.ulima.sw.Asesorias.ingredientes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoIngredientesAdapter;
import com.ulima.sw.Asesorias.beans.Ingrediente;

import java.util.List;

public class IngredientesActivity extends AppCompatActivity implements IngredientesView, ObservableScrollViewCallbacks {

    private IngredientesPresenter lPresenter;
    private ObservableListView lstIngredientes;
    private ProgressDialog dialog;
    private ActionBar supportActionBar;
    private List<Ingrediente> ingred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ingredientes");
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_ingredientes);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Intent intentPasado = getIntent();
        ingred = (List<Ingrediente>)intentPasado.getSerializableExtra("ingredientes");


        lstIngredientes = (ObservableListView)findViewById(R.id.lstIngredientes);
        lstIngredientes.setScrollViewCallbacks(this);

        setPresenter(new IngredientesPresenterImp(this));

        //lPresenter.obtenerIngredientes();
        mostrarIngredientes(ingred);
    }

    @Override
    public void setPresenter(IngredientesPresenter presenter) {
        this.lPresenter = presenter;
    }

    @Override
    public void mostrarIngredientes(List<Ingrediente> Ingredientes) {
        ListadoIngredientesAdapter adapter = new ListadoIngredientesAdapter(Ingredientes,this);
        lstIngredientes.setAdapter(adapter);
        dialog.dismiss();
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
        }
        return super.onOptionsItemSelected(item);
    }
}
