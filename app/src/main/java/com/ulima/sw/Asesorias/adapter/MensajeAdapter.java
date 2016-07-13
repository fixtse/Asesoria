package com.ulima.sw.Asesorias.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;

import java.util.List;

/**
 * Created by fixt on 12/07/16.
 */
public class MensajeAdapter extends BaseAdapter {

    private Context _context;
    private List<String> lcontenido;
    private LayoutInflater mInflater;


    public MensajeAdapter(Context context, List<String> lcontenido) {
        this.lcontenido = lcontenido;
        this._context = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return lcontenido.size();
    }

    @Override
    public Object getItem(int position) {
        return lcontenido.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(_context).inflate(R.layout.contenido_itm,parent,false);
        }
        TextView nameTxt= (TextView) convertView.findViewById(R.id.txtCont);
        final String s= (String) this.getItem(position);
        nameTxt.setText(Html.fromHtml(s));

        return convertView;
    }
}
