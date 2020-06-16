/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.annotations.SerializedName;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.xml.internal.ws.developer.Serialization;

/**
 *
 * @author LordLalo
 */
public class Cartas {

//    @SerializedName("nombreCarta")
//    String nombreCarta;
    @SerializedName("tipoCarta")
    String tipoCarta;
//    @SerializedName("numeroCarta")
//    int numeroCarta;
    @SerializedName("color")
    String color;

    public Cartas(String tipo, String color) {
        this.tipoCarta = tipo;
        this.color = color;
    }

//    public Cartas(String nombreCarta, String tipoCarta, int numeroCarta, int Carta) {
//        this.nombreCarta = nombreCarta;
//        this.tipoCarta = tipoCarta;
//        this.numeroCarta = numeroCarta;
//    }

//    public void setNombre(String nombre) {//Corazon
//        this.nombreCarta = nombre;
//
//    }
//
//    public void setNumeroDeCarta(int numeroCarta) {
//        this.numeroCarta = numeroCarta;
//    }
//
//    public int getNumeroCarta() {
//        return this.numeroCarta;
//    }

    public void setTipo(String tipoCarta) {//Organo=1/virus=2/medicina=3/tratamiento=4
        this.tipoCarta = tipoCarta;

    }

//    public String getNombreCarta() {
//        return nombreCarta;
//    }

    public String getTipo() {
        return tipoCarta;
    }

    public String getColor() {
        return color;
    }

}
