package com.ulima.sw.Asesorias.Mensajes;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by fixt on 12/07/16.
 */
public class FirebaseHelper {

        DatabaseReference db;
        Boolean saved=null;
        ArrayList<String> contenidos=new ArrayList<>();
        public FirebaseHelper(DatabaseReference db) {
            this.db = db;
        }
        //WRITE IF NOT NULL
        public Boolean save(String contenido)
        {
            if(contenido==null)
            {
                saved=false;
            }else
            {
                try
                {
                    db.child("contenidos").push().setValue(contenido);
                    saved=true;
                }catch (DatabaseException e)
                {
                    e.printStackTrace();
                    saved=false;
                }
            }
            return saved;
        }
        //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
        private void fetchData(DataSnapshot dataSnapshot)
        {
            contenidos.clear();
            for (DataSnapshot ds : dataSnapshot.getChildren())
            {
                String contenido = ds.getValue(String.class);
                contenidos.add(contenido);
            }
        }
        //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS
        public ArrayList<String> retrieve() {
            db.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    fetchData(dataSnapshot);
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    fetchData(dataSnapshot);
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            return contenidos;
        }

}
