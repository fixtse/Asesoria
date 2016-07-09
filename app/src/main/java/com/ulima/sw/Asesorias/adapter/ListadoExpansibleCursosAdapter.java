package com.ulima.sw.Asesorias.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.ulima.sw.Asesorias.R;
import com.ulima.sw.Asesorias.asebeans.Asesoria;
import com.ulima.sw.Asesorias.asebeans.Curso;

/**
 * Created by fixt on 08/07/16.
 */
public class ListadoExpansibleCursosAdapter extends BaseExpandableListAdapter {
    private List<Curso> lCursos;
    private LayoutInflater mInflater;
    private Context _context;


    public ListadoExpansibleCursosAdapter(Context context, List<Curso> cursos) {
        this._context = context;
        mInflater = LayoutInflater.from(context);
        lCursos = cursos;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return lCursos.get(groupPosition).getAsesorias()
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {



        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item, null);

        }

        Asesoria asesoria = lCursos.get(groupPosition).getAsesorias().get(childPosition);
        TextView txtDia =(TextView)convertView.findViewById(R.id.txtDia);
        TextView txtHora =(TextView)convertView.findViewById(R.id.txtHora);
        TextView txtLugar =(TextView)convertView.findViewById(R.id.txtLugar);

        txtDia.setText(asesoria.getDia());
        txtHora.setText(asesoria.getHora());
        txtLugar.setText(asesoria.getLugar());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return lCursos.get(groupPosition).getAsesorias()
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lCursos.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return lCursos.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        Curso curso = lCursos.get(groupPosition);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_group, null);
        }

        TextView tviCurso =  (TextView)convertView.findViewById(R.id.txtCurso);
        TextView tviSeccion = (TextView)convertView.findViewById(R.id.txtSeccion);
        tviCurso.setTypeface(null, Typeface.BOLD);
        tviCurso.setText(curso.getNombre());
        tviSeccion.setText("Sección: " + curso.getSeccion());

        return convertView;

    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
