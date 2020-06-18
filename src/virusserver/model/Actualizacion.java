/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Una instancia de esta clase permite crear y enviar una actualizació al los
 * clientes.
 *
 * @author Roberth 😊
 */
public class Actualizacion {

    @SerializedName("method")
    private String action;
    @SerializedName("class")
    private String modulo;
    @SerializedName("nuevosJugadores")
    private String listaJugadores;
    @SerializedName("refresher")
    private List<Jugador> refresherList;

    /**
     * Prepara una actualización de lista de jugadores.
     *
     * @param json Lista de jugadores convertida al formato <b>Json</b>
     */
    public void actualizarListaJugadores(String json) {
        action = "nuevosJugadores";
        listaJugadores = json;
    }

    public void cargarDatosNuevos(String json) {
        this.modulo = "SalaDeJuego";
        action = "cargarDatosInicioJuego";
        listaJugadores = json;
    }

    public void toRefreshGame(List<Jugador> jugList) {
        this.modulo = "SalaDeJuego";
        this.action = "refrescarDatosDeJuego";
        this.refresherList = jugList;
    }

    /**
     *
     * Prepara actalización que vuelve host al jugador que la reciba.
     */
    public void volverHost() {
        action = "volverHost";
    }

    public void vistaJuego() {
        action = "modoJuego";
    }

    public void turnoParaJugador() {
        action = "turnoDeJugar";
        modulo = "SalaDeJuego";
    }

    public List<Jugador> getRefresherList() {
        return refresherList;
    }

    public void setRefresherList(List<Jugador> refresherList) {
        this.refresherList = refresherList;
    }

}
