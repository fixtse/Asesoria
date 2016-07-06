package com.ulima.sw.Asesorias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.beans.Ingrediente;

import java.util.List;

/**
 * Created by fixt on 15/06/16.
 */
public class ListadoIngredientesAdapter extends BaseAdapter {
    private List<Ingrediente> lProductos;
    private LayoutInflater mInflater;
    private Context mContext;

    public ListadoIngredientesAdapter(List<Ingrediente> producto,
                                Context context){
        mContext = context;
        lProductos = producto;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return lProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lProductos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        int acu;
        ViewHolder viewHolder;
        if (view == null){
            view = mInflater.inflate(R.layout.ingredienteitm, null);
            viewHolder = new ViewHolder();
            viewHolder.tviNum =  (TextView)view.findViewById(R.id.edtID);
            viewHolder.tviNombre = (TextView)view.findViewById(R.id.edtNombre);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Ingrediente ingrediente = lProductos.get(position);
        acu = position +1;
        viewHolder.tviNum.setText("N#"+acu);
        viewHolder.tviNombre.setText("Nom: "  + ingrediente.getNombre());

        return view;
    }

    class ViewHolder{
        TextView tviNombre;
        TextView tviNum;
    }
}

