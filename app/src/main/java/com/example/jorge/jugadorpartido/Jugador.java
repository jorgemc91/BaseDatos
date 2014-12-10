package com.example.jorge.jugadorpartido;

import java.io.Serializable;

/**
 * Created by Jorge on 08/12/2014.
 */
public class Jugador implements Serializable,Comparable<Jugador>{


    private long id;
    private String nombre;
    private String telefono;
    //private int valoracion;
    private String fnac;

    public Jugador() {
    }

    public Jugador(String nombre, String telefono, /*String valoracion,*/ String fnac) {
        this.nombre = nombre;
        this.telefono = telefono;
        //this.valoracion = Integer.parseInt(valoracion);
        this.fnac = fnac;
    }

    public Jugador(String nombre, long id, String telefono, /*int valoracion,*/ String fnac) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
        //this.valoracion = valoracion;
        this.fnac = fnac;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }
/*
    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;

        if (id != jugador.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                //", valoracion=" + valoracion +
                ", fnac='" + fnac + '\'' +
                '}';
    }

    @Override
    public int compareTo(Jugador jugador) {
        //jugadores -> this, jugador
/*        if(this.valoracion != jugador.valoracion){
            return this.valoracion- jugador.valoracion;
        }*/
        if(this.nombre.compareTo(jugador.nombre)!=0){
            return this.nombre.compareTo(jugador.nombre);
        }else{
            return this.fnac.compareTo(jugador.fnac);
        }

    }
}
