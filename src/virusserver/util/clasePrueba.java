/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.util;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Roberth
 */
public class clasePrueba {

    @SerializedName("nombre")
    private String Nombre;
    @SerializedName("edad")
    private Integer Edad;
    
    
    public clasePrueba() {
        this.Edad = 22;
        this.Nombre = "Roberth";
    }
    
}
