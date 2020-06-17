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
    String tipoCarta;
    @SerializedName("color")
    String color;

    public Carta(String tipo, String color) {
        this.tipoCarta = tipo;
        this.color = color;
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

}
