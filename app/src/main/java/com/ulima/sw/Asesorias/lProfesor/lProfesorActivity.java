package com.ulima.sw.Asesorias.lProfesor;

import android.app.ProgressDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.adapter.ListadoProfesorAdapter;
import com.ulima.sw.Asesorias.asebeans.Curso;
import com.ulima.sw.Asesorias.asebeans.Sesion;



import java.util.HashMap;
import java.util.List;

public class lProfesorActivity extends AppCompatActivity implements lProfesorView, ObservableScrollViewCallbacks {

    private lProfesorPresenter lPresenter;
    private ObservableListView lstCursos;
    private ProgressDialog dialog;
    private int pos;
    private ActionBar supportActionBar;
    private Sesion ses;
    private DrawerLayout drawerLayout;
    private View headerView;
    private TextView txtUsuario;
    private ImageView imgUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Listado Cursos");
        setContentView(R.layout.activity_lprofesor);

        NavigationView navigationView
                = (NavigationView) findViewById(R.id.nav_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        lstCursos = (ObservableListView)findViewById(R.id.lstCursos);

        headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        txtUsuario =  (TextView)headerView.findViewById(R.id.navNom);
        imgUsuario = (ImageView)headerView.findViewById(R.id.navImg) ;

        ses = new Sesion(getApplicationContext());
        ses.checkLogin();

        // get user data from session
        HashMap<String, String> user = ses.getUserDetails();

        // name
        String name = user.get(ses.KEY_NAME);

        txtUsuario.setText("Profesor: " + name);
        imgUsuario.setImageResource(R.drawable.profe);

        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.toright);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }




        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();



        lstCursos.setScrollViewCallbacks(this);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();

                        /*if (item.getItemId() == R.id.men_fragment_uno){
                            transaction.replace(R.id.flaContenido, new UnoFragment());
                        }else if(item.getItemId() == R.id.men_fragment_dos){
                            transaction.replace(R.id.flaContenido, new DosFragment());
                        }else if(item.getItemId() == R.id.men_fragment_tres){
                            transaction.replace(R.id.flaContenido, new TresFragment());
                        }*/

                        transaction.commit();

                        drawerLayout.closeDrawers();
                        return true;
                    }
                });


        setPresenter(new lProfesorPresenterImp(this));
        lPresenter.obtenerCursos();


    }

    @Override
    public void setPresenter(lProfesorPresenter presenter) {
        this.lPresenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void mostrarCursos(final List<Curso> cursos) {
        ListadoProfesorAdapter adapter = new ListadoProfesorAdapter(cursos,this);
        lstCursos.setAdapter(adapter);

        dialog.dismiss();
        /*lstPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoCursosActivity.this, lProfesorActivity.class);
                intent.putExtra("ingredientes",(Serializable)Pizzas.get(position).getIng());
                startActivity(intent);

            }

        });*/
    }


    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }


                break;
            case R.id.men_op1:
                // lPresenter.actualizarEstado(pos,usuario);
                break;
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
