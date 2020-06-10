/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.annotations.SerializedName;
import com.sun.xml.internal.ws.developer.Serialization;

/**
 *
 * @author LordLalo
 */
public class Cartas {
    @SerializedName("nombreCarta")
    String nombreCarta;
    @SerializedName("tipoCarta")
    int tipoCarta;
    @SerializedName("numeroCarta")
    int numeroCarta;

    public Cartas(String nombreCarta, int tipoCarta, int numeroCarta) {
        this.nombreCarta = nombreCarta;
        this.tipoCarta = tipoCarta;
        this.numeroCarta = numeroCarta;
    }

    public void setNombre(String nombre) {//Corazon
        this.nombreCarta = nombre;

    }

    public void setNumeroDeCarta(int numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public int getNumeroCarta() {
        return this.numeroCarta;
    }

    public void setTipo(int tipoCarta) {//Organo=1/virus=2/medicina=3/tratamiento=4
        this.tipoCarta = tipoCarta;

    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    public int getTipo() {
        return tipoCarta;
    }

}
