package com.ulima.sw.Asesorias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Curso;

import java.util.List;



/**
 * Created by fixt on 05/05/16.
 */
public class ListadoCursosAdapter extends BaseAdapter{

    private List<Curso> lCursos;
    private LayoutInflater mInflater;
    private Context mContext;

    public ListadoCursosAdapter(List<Curso> cursos,
                                Context context){
        mContext = context;
        lCursos = cursos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lCursos.size();
    }

    @Override
    public Object getItem(int position) {
        return lCursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lCursos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = mInflater.inflate(R.layout.cursoitm, null);
            viewHolder = new ViewHolder();
            viewHolder.tviCurso =  (TextView)view.findViewById(R.id.txtCurso);
            viewHolder.tviSeccion = (TextView)view.findViewById(R.id.txtSeccion);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Curso curso = lCursos.get(position);
        viewHolder.tviCurso.setText( curso.getNombre());
        viewHolder.tviSeccion.setText("Sección: " + curso.getSeccion());


        return view;
    }

    class ViewHolder{
        TextView tviCurso;
        TextView tviSeccion;
    }
}
