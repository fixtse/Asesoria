package com.ulima.sw.Asesorias.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Mensaje;

import java.util.List;

/**
 * Created by fixt on 09/07/16.
 */
public class ListadoMensajesAdapter extends BaseSwipeAdapter {

    private List<Mensaje> lMensajes;
    private LayoutInflater mInflater;
    private Context _context;
    private String tipo;
    private FirebaseDatabase database;

    public ListadoMensajesAdapter(Context context,List<Mensaje> lMensajes,String tipo) {
        this.lMensajes = lMensajes;
        this._context = context;
        mInflater = LayoutInflater.from(context);
        this.tipo = tipo;
    }

    @Override
    public int getCount() {
        return lMensajes.size();
    }

    @Override
    public Object getItem(int position) {
        return lMensajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return mInflater.inflate(R.layout.mensaje_itm, null);
    }

    @Override
    public void fillValues(final int position, final View convertView) {
        final Mensaje mensaje = (Mensaje)getItem(position);

        TextView tviCurso =  (TextView)convertView.findViewById(R.id.txtCurso);
        TextView tviCont = (TextView)convertView.findViewById(R.id.txtCont);
        ImageView imgDelete = (ImageView)convertView.findViewById(R.id.imgDelete);

        if (tipo.equals("1")){
            tviCurso.setText(Html.fromHtml("<b>Profesor:</b> " +mensaje.getUsuarioPROF().toUpperCase() + " <b>("+mensaje.getCurso()+")</b>"));
        }else{
            tviCurso.setText(Html.fromHtml("<b>Alumno:</b> " +mensaje.getUsuarioAL().toUpperCase() + " <b>("+mensaje.getCurso()+")</b>"));
        }


        int posC;
        if (mensaje.getContenidos().size()-1 == 0){
            posC = 0;

        }else{
            posC = mensaje.getContenidos().size()-1;
        }

        tviCont.setText(Html.fromHtml(mensaje.getContenidos().get(posC)));

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                DatabaseReference mensajesref = database.getReference().child("mensajes");
                mensajesref.orderByChild("id").equalTo(mensaje.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            DatabaseReference mensajeElimred = database.getReference().child("mensajes").child(ds.getKey());
                            mensajeElimred.removeValue();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(_context, "Mensaje Eliminado", Toast.LENGTH_SHORT).show();
            }
        });

    }

   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mensaje mensaje = (Mensaje)getItem(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.mensaje_itm, null);
        }

        TextView tviCurso =  (TextView)convertView.findViewById(R.id.txtCurso);
        TextView tviCont = (TextView)convertView.findViewById(R.id.txtCont);

        if (tipo.equals("1")){
            tviCurso.setText(Html.fromHtml("<b>Profesor:</b> " +mensaje.getUsuarioPROF().toUpperCase() + " <b>("+mensaje.getCurso()+")</b>"));
        }else{
            tviCurso.setText(Html.fromHtml("<b>Alumno:</b> " +mensaje.getUsuarioAL().toUpperCase() + " <b>("+mensaje.getCurso()+")</b>"));
        }


        int posC;
        if (mensaje.getContenidos().size()-1 == 0){
            posC = 0;

        }else{
            posC = mensaje.getContenidos().size()-1;
        }

        tviCont.setText(Html.fromHtml(mensaje.getContenidos().get(posC)));


        return convertView;
    }*/
}
