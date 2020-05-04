/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.annotations.SerializedName;

/**
 * Una instancia de esta clase permite crear y enviar una actualizació al los
 * clientes.
 *
 * @author Roberth 😊
 */
public class Actualizacion {

    @SerializedName("action")
    private String action;
    @SerializedName("nuevosJugadores")
    private String listaJugadores;

    /**
     * Prepara una actualización de lista de jugadores.
     *
     * @param json Lista de jugadores convertida al formato <b>Json</b>
     */
    public void actualizarListaJugadores(String json) {
        action = "nuevosJugadores";
        listaJugadores = json;
    }
}
