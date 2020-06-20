/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author LordLalo
 */
public class Carta {

    @SerializedName("tipoCarta")
    private String tipoCarta;
    @SerializedName("numeroCarta")
    private int cantidad;
    @SerializedName("color")
    private String color;
    @SerializedName("posicion")
    private int posicion;

    public Carta(String tipo, String color, int posicion) {
        this.tipoCarta = tipo;
        this.color = color;
        this.posicion = posicion;
    }

    public void setTipo(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public String getTipo() {
        return tipoCarta;
    }

    public String getColor() {
        return color;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

}
