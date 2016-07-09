package com.ulima.sw.Asesorias.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Mensaje;

import java.util.List;

/**
 * Created by fixt on 09/07/16.
 */
public class ListadoMensajesAdapter extends BaseAdapter {

    private List<Mensaje> lMensajes;
    private LayoutInflater mInflater;
    private Context _context;

    public ListadoMensajesAdapter(List<Mensaje> lMensajes, Context context) {
        this.lMensajes = lMensajes;
        this._context = context;
        mInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        Mensaje mensaje = (Mensaje)getItem(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.frag_mensajes, null);
        }

        TextView tviCurso =  (TextView)convertView.findViewById(R.id.txtCurso);
        TextView tviAlumno = (TextView)convertView.findViewById(R.id.txtAlumno);
        TextView tviCont = (TextView)convertView.findViewById(R.id.txtCont);
        tviCurso.setTypeface(null, Typeface.BOLD);
        tviCurso.setText(mensaje.getCurso());
        tviAlumno.setText(mensaje.getIdAlumno());
        tviCont.setText(mensaje.getContenido());
       // tviSeccion.setText("Sección: " + curso.getSeccion());

        return convertView;
    }
}
