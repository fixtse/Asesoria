package com.ulima.sw.Asesorias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.beans.Mensaje;

import java.util.List;

/**
 * Created by fixt on 14/06/16.
 */
public class ListadoPedidosAdapter extends BaseAdapter {

    private List<Mensaje> lPedidos;
    private LayoutInflater mInflater;
    private Context mContext;

    public ListadoPedidosAdapter(List<Mensaje> pedidos, Context context){
        mContext = context;
        lPedidos = pedidos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lPedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return lPedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lPedidos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        int cont;
        ViewHolder viewHolder;
        if (view == null){
            view = mInflater.inflate(R.layout.pedidoitm, null);
            viewHolder = new ViewHolder();
            viewHolder.imgE =(ImageView) view.findViewById(R.id.imgE);
            viewHolder.txtPedido = (TextView) view.findViewById(R.id.txtPedido);
            viewHolder.txtHora=(TextView) view.findViewById(R.id.txtHora);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final Mensaje pedido = lPedidos.get(position);
        cont = position + 1;
        viewHolder.txtPedido.setText("Pedido #"+cont);
        viewHolder.txtHora.setText(pedido.getHora());
        if (pedido.getEstado()== 0) {
            viewHolder.imgE.setImageResource(R.drawable.rojo);
        }else if (pedido.getEstado() == 1) {
            viewHolder.imgE.setImageResource(R.drawable.amarillo);
        }else if (pedido.getEstado() == 2) {
                viewHolder.imgE.setImageResource(R.drawable.verde);
        }

        return view;
    }

    class ViewHolder{
        ImageView imgE;
        TextView txtPedido;
        TextView txtHora;
    }
}

