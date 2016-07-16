package com.ulima.sw.Asesorias.cursos;


import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Sesion;
import com.ulima.sw.Asesorias.fragments.fragCursos;
import com.ulima.sw.Asesorias.fragments.fragMensajes;



import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class mainActivity extends AppCompatActivity {

    private Sesion ses;
    private View headerView;
    private TextView txtUsuario;
    private CircleImageView imgUsuario;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Listado Cursos");
        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.nav_view);

        setupDrawerContent(nvDrawer);


        headerView = nvDrawer.inflateHeaderView(R.layout.nav_header);

        drawerToggle = setupDrawerToggle();

        mDrawer.addDrawerListener(drawerToggle);


        txtUsuario = (TextView) headerView.findViewById(R.id.navNom);
        imgUsuario = (CircleImageView) headerView.findViewById(R.id.navImg);

        ses = new Sesion(getApplicationContext());
        ses.checkLogin();

        // get user data from session
        HashMap<String, String> user = ses.getUserDetails();

        // name
        String name = user.get(ses.KEY_NAME);
        String tipo = user.get(ses.KEY_TIPO);
        if (tipo != null) {
            if (tipo.equals("1")) {
                nvDrawer.inflateMenu(R.menu.menu_alm);
                txtUsuario.setText("Usuario: " + name.toUpperCase());
                if (name.equals("diego")) {
                    imgUsuario.setImageResource(R.drawable.d1);
                } else if (name.equals("sergio")) {
                    imgUsuario.setImageResource(R.drawable.s1);
                } else {
                    imgUsuario.setImageResource(R.drawable.diego);
                }

            } else if (tipo.equals("2")) {
                nvDrawer.inflateMenu(R.menu.menu_prof);
                txtUsuario.setText("Profesor: " + name.toUpperCase());

                if (name.equals("hquintan")) {
                    imgUsuario.setImageResource(R.drawable.profe);
                } else if (name.equals("scastro")) {
                    imgUsuario.setImageResource(R.drawable.c1);
                } else {
                    imgUsuario.setImageResource(R.drawable.diego);
                }
            }

        } else {
            nvDrawer.inflateMenu(R.menu.menu_alm);
            txtUsuario.setText("Usuario: " + name.toUpperCase());
            imgUsuario.setImageResource(R.drawable.diego);
        }

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flaContenido, new fragCursos());
        tx.commit();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass =null;
        switch(menuItem.getItemId()) {
            case R.id.mcursos:
                  fragmentClass = fragCursos.class;
                break;
            case R.id.mmensajes:
                fragmentClass = fragMensajes.class;
                break;
            case R.id.CSesion:
                ses.logoutUser();
                break;
            default:
                fragmentClass = fragCursos.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flaContenido,fragment).commit();

        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }




    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


}
