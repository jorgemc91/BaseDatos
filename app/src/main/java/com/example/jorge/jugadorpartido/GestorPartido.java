package com.example.jorge.jugadorpartido;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GestorPartido {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorPartido(Context c){
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public static Partido getRow(Cursor c) {
        Partido objeto = new Partido();
        objeto.setIdpartido(c.getLong(0));
        objeto.setIdjugador(c.getLong(1));
        objeto.setContrincante(c.getString(2));
        objeto.setValoracion(c.getInt(3));
        return objeto;
    }

    public Cursor getCursor(String condicion,String[] parametros, String orden) {
        Cursor cursor= bd.query(Contrato.TablaPartido.TABLA, null, condicion, parametros,null, null,orden);
        return cursor;
    }

    public long valoracionMedia(String id) {
        String[] parametros = new String[] { id };
        Cursor c = bd.rawQuery("select avg(valoracion) from "+
                Contrato.TablaPartido.TABLA
                + " where " + Contrato.TablaPartido.ID_JUGADOR + " = ?", parametros);
        c.moveToFirst();
        long media = c.getLong(0);
        c.close();
        return media;
    }
}
