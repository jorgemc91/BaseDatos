package com.example.jorge.jugadorpartido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 08/12/2014.
 */
public class GestorJugador {

    private Ayudante abd;
    private SQLiteDatabase bd;


    public GestorJugador(Context c) {
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

    public long insert(Jugador objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaJugador.FNAC, objeto.getFnac());
        valores.put(Contrato.TablaJugador.NOMBRE,objeto.getNombre());
        valores.put(Contrato.TablaJugador.TELEFONO,objeto.getTelefono());
        //valores.put(Contrato.TablaJugador.VALORACION,objeto.getValoracion());
        long id = bd.insert(Contrato.TablaJugador.TABLA,null, valores);
        return id;
    }
    public int delete(Jugador objeto) {
        String condicion = Contrato.TablaJugador._ID + " = ? ";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.delete(
                Contrato.TablaJugador.TABLA, condicion,argumentos);
        return cuenta;
    }

    public List<Jugador> select(String condicion,String[] parametros, String orden) {
        List<Jugador> alj = new ArrayList<Jugador>();

        Cursor cursor = bd.query(Contrato.TablaJugador.TABLA, null,
                condicion, parametros, null, null, orden);
        cursor.moveToFirst();
        Jugador objeto;
        while (!cursor.isAfterLast()) {
            objeto = getRow(cursor);
            alj.add(objeto);
            cursor.moveToNext();
        }
        cursor.close();
        return alj;
    }

    public static Jugador getRow(Cursor c) {
        Jugador objeto = new Jugador();
        objeto.setId(c.getLong(0));
        objeto.setNombre(c.getString(1));
        objeto.setTelefono(c.getString(2));
        //objeto.setValoracion(c.getInt(3));
        objeto.setFnac(c.getString(3));
        return objeto;
    }

    public Cursor getCursor(String condicion,String[] parametros, String orden) {
        Cursor cursor= bd.query(Contrato.TablaJugador.TABLA, null, condicion, parametros,null, null,orden);
        return cursor;
    }
}
