package com.example.webclient.model;

public class Universidad {

    private String nombre;
    private boolean abet;
    private int rankig;
    private String ubicacion;

    public Universidad(){

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAbet() {
        return abet;
    }

    public void setAbet(boolean abet) {
        this.abet = abet;
    }

    public int getRankig() {
        return rankig;
    }

    public void setRankig(int rankig) {
        this.rankig = rankig;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
