package com.ulima.sw.Asesorias.asebeans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.ulima.sw.Asesorias.Login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by W3222 on 06/07/2016.
 */
// http://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/
public class Sesion {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "MyPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "Logueado";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "nombre";
    public static final String KEY_TIPO = "tipo";
    public static final String KEY_ID = "id";
    public static final String KEY_estado = "estado";

    // Email address (make variable public to access from outside)


    // Constructor
    public Sesion(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String tipo, Long id){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_TIPO, tipo);
        editor.putLong(KEY_ID, id);
        editor.putLong(KEY_estado, -1L);



        // commit changes
        editor.commit();
    }



    public void actEstado(Long estado){
        editor.putLong(KEY_estado,estado);

        editor.commit();
    }

    public Long getEstado(){
        return pref.getLong(KEY_estado,0l);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_TIPO, pref.getString(KEY_TIPO, null));

        // return user
        return user;
    }

    public Long getID(){
        return pref.getLong(KEY_ID, 0L);
    }

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(_context,"Por favor, inicie sesión",
                    Toast.LENGTH_SHORT).show();

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
