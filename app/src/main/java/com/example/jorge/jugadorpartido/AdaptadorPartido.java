package com.example.jorge.jugadorpartido;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Jorge on 09/12/2014.
 */
public class AdaptadorPartido extends CursorAdapter {
    private TextView tv1,tv2;

    public AdaptadorPartido(Context context, Cursor c) {
        super(context, c,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup vg) {
        LayoutInflater i = LayoutInflater.from(vg.getContext());
        View v = i.inflate(R.layout.lista_detalle_partido, vg, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        GestorPartido gp=new GestorPartido(context);
        Partido p;
        p=gp.getRow(cursor);
        tv1=(TextView) view.findViewById(R.id.tvcontrincante);
        tv2=(TextView) view.findViewById(R.id.tvvaloracion);

        tv1.setText(p.getContrincante().toString());
        tv2.setText(p.getValoracion()+"");
    }
}
