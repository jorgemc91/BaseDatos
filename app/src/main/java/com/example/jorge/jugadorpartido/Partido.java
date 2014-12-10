package com.example.jorge.jugadorpartido;

import java.io.Serializable;

/**
 * Created by Jorge on 08/12/2014.
 */
public class Partido implements Serializable,Comparable<Partido>{

    private long idpartido;
    private long idjugador;
    private String contrincante;
    private int valoracion;

    public Partido(){}

    public Partido(long idpartido, long idjugador, String contrincante, int valoracion) {
        this.idpartido = idpartido;
        this.idjugador = idjugador;
        this.contrincante = contrincante;
        this.valoracion = valoracion;
    }

    public long getIdpartido() {
        return idpartido;
    }

    public void setIdpartido(long idpartido) {
        this.idpartido = idpartido;
    }

    public long getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(long idjugador) {
        this.idjugador = idjugador;
    }

    public String getContrincante() {
        return contrincante;
    }

    public void setContrincante(String contrincante) {
        this.contrincante = contrincante;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partido partido = (Partido) o;

        if (idjugador != partido.idjugador) return false;
        if (idpartido != partido.idpartido) return false;
        if (valoracion != partido.valoracion) return false;
        if (contrincante != null ? !contrincante.equals(partido.contrincante) : partido.contrincante != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idpartido ^ (idpartido >>> 32));
        result = 31 * result + (int) (idjugador ^ (idjugador >>> 32));
        result = 31 * result + (contrincante != null ? contrincante.hashCode() : 0);
        result = 31 * result + valoracion;
        return result;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "idpartido=" + idpartido +
                ", idjugador=" + idjugador +
                ", contrincante='" + contrincante + '\'' +
                ", valoracion=" + valoracion +
                '}';
    }

    @Override
    public int compareTo(Partido partido) {
        return 0;
    }
}
