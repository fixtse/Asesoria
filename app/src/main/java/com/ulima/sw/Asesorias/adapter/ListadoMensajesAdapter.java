package com.ulima.sw.Asesorias.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
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
    private String tipo;

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
            tviCurso.setText(Html.fromHtml("<b>Alumno:</b> " +mensaje.getUsuarioAL() + " <b>("+mensaje.getCurso()+")</b>"));
        }


        int posC;
        if (mensaje.getContenidos().size()-1 == 0){
            posC = 0;

        }else{
            posC = mensaje.getContenidos().size()-1;
        }

        tviCont.setText(Html.fromHtml("<b>Mensaje:</b> "+mensaje.getContenidos().get(posC)));


        return convertView;
    }
}
