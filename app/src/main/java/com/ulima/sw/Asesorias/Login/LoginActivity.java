package com.ulima.sw.Asesorias.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Usuario;
import com.ulima.sw.Asesorias.asebeans.Sesion;
import com.ulima.sw.Asesorias.listado.ListadoCursosActivity;


public class LoginActivity extends AppCompatActivity implements LoginView{

    private LoginPresenter lPresenter;
    private EditText eteUsuario, etePassword;
    private ProgressDialog dialog;
    private String usuario;
    private Sesion ses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.PichangersTheme);
        //setTheme(android.R.style.Theme_Holo);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        eteUsuario = (EditText) findViewById(R.id.txtUsuario);
        etePassword = (EditText) findViewById(R.id.txtContra);
        ses = new Sesion(getApplicationContext());


    }

    public void onLoginClicked(View view){
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        usuario = eteUsuario.getText().toString().trim();
        String password ="";

        ses.createLoginSession(usuario);

        password = etePassword.getText().toString();
        Usuario user= new Usuario(usuario, password);
        setPresenter(new LoginPresenterImp(this));
        lPresenter.obtenerLogin(user);

    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.lPresenter=presenter;
    }

    @Override
    public void callActiviy(String resp) {

        if (resp.equalsIgnoreCase("1")){
            Intent intent = new Intent(this, ListadoCursosActivity.class);
            intent.putExtra("usuario",usuario);
            eteUsuario.setText(null);
            etePassword.setText(null);

            startActivity(intent);
        }else{
            Toast.makeText(this, "Credenciales Erradas", Toast.LENGTH_SHORT).show();

        }
        dialog.dismiss();
    }



}
