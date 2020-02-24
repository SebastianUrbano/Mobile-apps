package com.example.webclient.model;

public class Carta {

    private String comentario;
    private String de;
    private String para;

    public Carta( String c, String d, String p){

        comentario = c;
        de = d;
        para = p;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    @Override
    public String toString() {
        return "Esta es el comentario: " + getComentario();
    }
}
