package com.example.bntactividades.modelos;

public class Relevamiento {

    //Atributos de la calse Relevamiento
    private int id;
    private String nombre;
    private double latitud, altitud, longitud;
    private int tipoPoste, tipoHerraje, tipoObstruccion, tipoInfraestructura;
    private String observaciones;

    //Construsctor sin argumentos o por defecto

    public Relevamiento(){
    }

    //Constructor con argumentos


    public Relevamiento(int id, String nombre, double latitud, double altitud, double longitud,
                        int tipoPoste, int tipoHerraje, int tipoObstruccion, int tipoInfraestructura,
                        String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.altitud = altitud;
        this.longitud = longitud;
        this.tipoPoste = tipoPoste;
        this.tipoHerraje = tipoHerraje;
        this.tipoObstruccion = tipoObstruccion;
        this.tipoInfraestructura = tipoInfraestructura;
        this.observaciones = observaciones;
    }

    public Relevamiento(int anInt, String string, int anInt1, int anInt2, int anInt3, int anInt4, double aDouble, double aDouble1, double aDouble2, String string1) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getAltitud() {
        return altitud;
    }

    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {this.longitud = longitud;
    }

    public int getTipoPoste() {
        return tipoPoste;
    }

    public void setTipoPoste(int tipoPoste) {
        this.tipoPoste = tipoPoste;
    }

    public int getTipoHerraje() {
        return tipoHerraje;
    }

    public void setTipoHerraje(int tipoHerraje) {
        this.tipoHerraje = tipoHerraje;
    }

    public int getTipoObstruccion() {
        return tipoObstruccion;
    }

    public void setTipoObstruccion(int tipoObstruccion) {
        this.tipoObstruccion = tipoObstruccion;
    }

    public int getTipoInfraestructura() {
        return tipoInfraestructura;
    }

    public void setTipoInfraestructura(int tipoInfraestructura) {
        this.tipoInfraestructura = tipoInfraestructura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Relevamiento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", latitud=" + latitud +
                ", altitud=" + altitud +
                ", longitud=" + longitud +
                ", tipoPoste=" + tipoPoste +
                ", tipoHerraje=" + tipoHerraje +
                ", tipoObstruccion=" + tipoObstruccion +
                ", tipoInfraestructura=" + tipoInfraestructura +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
} //Relevamiento
