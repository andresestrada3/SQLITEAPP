package com.andresnet.sqliteapp;

public class Peluchito {
    //Propiedades
    private int idd, cantidad;
    private String nombre;
    private double precio;
      //Constructor

    public Peluchito(int idd ,String nombre,int cantidad, double precio) {
        this.idd = idd;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre3) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    // Metodo que devuelve los datos
    public String datos(){

        return "\n\nID: "+String.valueOf(idd)+ "\nNombre: "+String.format(nombre)+ "\nCantidad: "+String.valueOf(cantidad)+ "\nPrecio: "+String.valueOf(precio);
    }
}

