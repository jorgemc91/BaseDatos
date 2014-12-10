package com.example.jorge.jugadorpartido;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jorge on 08/12/2014.
 */
public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "futbol.sqlite";
    public static final int DATABASE_VERSION = 3;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql;
/*
        sql="create table "+Contrato.TablaJugador.TABLA+
                " (" + Contrato.TablaJugador._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaJugador.NOMBRE + " text, " +
                Contrato.TablaJugador.TELEFONO + " text, " +
                Contrato.TablaJugador.VALORACION + " integer, "+
                Contrato.TablaJugador.FNAC+" text)";*/

            sql="create table "+Contrato.TablaJugador.TABLA+
                    " (" + Contrato.TablaJugador._ID +
                    " integer primary key autoincrement, " +
                    Contrato.TablaJugador.NOMBRE + " text, " +
                    Contrato.TablaJugador.TELEFONO + " text, " +
                    Contrato.TablaJugador.FNAC + " text)";
        db.execSQL(sql);

            sql="create table "+Contrato.TablaPartido.TABLA+
                " (" + Contrato.TablaPartido._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaPartido.ID_JUGADOR + " long, " +
                Contrato.TablaPartido.CONTRINCANTE + " text, " +
                Contrato.TablaPartido.VALORACION + " integer, " +
                "FOREIGN KEY (" + Contrato.TablaPartido.ID_JUGADOR + ") REFERENCES " +
                Contrato.TablaJugador.TABLA + "(" + Contrato.TablaJugador._ID +"))";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //transformar el esquema de la version antigua a la nueva sin que se produzca perdida de datos

        //1º crear tablas de respaldo(identicas)
        String sql="create table respaldo"+Contrato.TablaJugador.TABLA+
                " (" + Contrato.TablaJugador._ID +
                " integer primary key, " +
                Contrato.TablaJugador.NOMBRE + " text, " +
                Contrato.TablaJugador.TELEFONO + " text, " +
                //Contrato.TablaJugador.VALORACION + " integer, "+
                Contrato.TablaJugador.FNAC+" text)";
        db.execSQL(sql);
        //2º copio los datos en las de respaldo
        sql="insert into respaldo"+Contrato.TablaJugador.TABLA+" select * from "+Contrato.TablaJugador.TABLA;
        db.execSQL(sql);
        //3º borro las tablas originales
        sql = "drop table " + Contrato.TablaJugador.TABLA;
        db.execSQL(sql);
        //4º creo las tablas nuevas (onCreate)
        onCreate(db);
        //5º copio en las nuevas tablas los datos de las tablas de respaldo
        sql="insert into "+Contrato.TablaJugador.TABLA+" " +
                "select " +Contrato.TablaJugador._ID+ "," +
                ""+Contrato.TablaJugador.NOMBRE +","+
                ""+Contrato.TablaJugador.TELEFONO +","+
                ""+Contrato.TablaJugador.FNAC +" from respaldo"+Contrato.TablaJugador.TABLA;
        db.execSQL(sql);
        //6º inserto datos en la tabla partido
        sql="insert into "+Contrato.TablaPartido.TABLA+" " +
                "("+Contrato.TablaPartido.ID_JUGADOR+ "," +
                ""+Contrato.TablaPartido.CONTRINCANTE+ ","+
                ""+Contrato.TablaPartido.VALORACION+") values(1,'Granada',6)";

        db.execSQL(sql);

        sql="insert into "+Contrato.TablaPartido.TABLA+" " +
                "("+Contrato.TablaPartido.ID_JUGADOR+ "," +
                ""+Contrato.TablaPartido.CONTRINCANTE+ ","+
                ""+Contrato.TablaPartido.VALORACION+") values(1,'Real madrid',8)";

        db.execSQL(sql);
        //7º borro las tablas de respaldo
        sql = "drop table respaldo"+Contrato.TablaJugador.TABLA;
        db.execSQL(sql);

    }
}
