package com.ulima.sw.Asesorias.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Usuario;
import com.ulima.sw.Asesorias.asebeans.Sesion;
import com.ulima.sw.Asesorias.cursos.mainActivity;

import java.lang.ref.Reference;


public class LoginActivity extends AppCompatActivity implements LoginView{

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText eteUsuario, etePassword;
    private ProgressDialog dialog;
    private String usuario;
    private Sesion ses;
    private FirebaseDatabase database;
    private String usuarioBD = "usuarios";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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


        usuario = eteUsuario.getText().toString().trim().toLowerCase();
        final String password =etePassword.getText().toString();


        //setPresenter(new LoginPresenterImp(this));
        //lPresenter.obtenerLogin(user);


        database = FirebaseDatabase.getInstance();
        final DatabaseReference loginReference = database.getReference().child(usuarioBD).child(usuario);

        // = new Usuario(usuario, password, 1);

        //loginReference.child("3").setValue(user);


        loginReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario user  = dataSnapshot.getValue(Usuario.class) ;


                if (user.getUsuario() == null || user.getPassword() == null){

                    callActiviy("0","0L");
                }else{
                    if (user.getUsuario().equals(usuario) && user.getPassword().equals(password)){
                        if (user.getTipo().equals("2")){

                            callActiviy(""+user.getTipo(),""+user.getProfid());
                        }else{
                            callActiviy(""+user.getTipo(),""+user.getAlmid());
                        }


                    }

                }






            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });






    }


    @Override
    public void callActiviy(String resp, String id) {



        if (resp.equalsIgnoreCase("0")){
            Toast.makeText(this, "Credenciales Erradas", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, mainActivity.class);
            ses.createLoginSession(usuario,resp,id);
            eteUsuario.setText(null);
            etePassword.setText(null);
            startActivity(intent);

        }
        dialog.dismiss();
    }



}
