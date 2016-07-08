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
import com.ulima.sw.Asesorias.fragments.fragCursos;
import com.ulima.sw.Asesorias.fragments.fragMensajes;


import java.util.HashMap;
import java.util.List;

public class lProfesorActivity extends AppCompatActivity {



    private Sesion ses;
    private DrawerLayout drawerLayout;
    private View headerView;
    private TextView txtUsuario;
    private ImageView imgUsuario;
    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Listado Cursos");
        setContentView(R.layout.activity_lprofesor);

        NavigationView navigationView
                = (NavigationView) findViewById(R.id.nav_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


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



        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flaContenido, new fragCursos());
        tx.commit();




        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();

                        if (item.getItemId() == R.id.mcursos){
                           transaction.replace(R.id.flaContenido, new fragCursos());
                        }else if(item.getItemId() == R.id.mmensajes){
                            transaction.replace(R.id.flaContenido, new fragMensajes());

                        }else if(item.getItemId() == R.id.CSesion){
                            ses.logoutUser();

                        }

                        transaction.commit();

                        drawerLayout.closeDrawers();
                        return true;
                    }
                });





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


}
