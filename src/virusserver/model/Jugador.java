/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author Roberth
 */
public class Jugador {

    @SerializedName("nombreJug")
    private String nombreJugador;
    @SerializedName("nombreAvt")
    private String nombreAvatar;
    private int puerto;
    private String direccionIP;
    @SerializedName("host:")
    private boolean host;
    private boolean listo;
    private final ArrayList<Carta> cartasLogicasActuales = new ArrayList<>();
    private final ArrayList<Carta> cartasLogicasJugadas = new ArrayList<>();

    public Jugador(String nombreJugador, String nombreAvatar, int puerto, String direccionIP, boolean host) {
        this.nombreJugador = nombreJugador;
        this.nombreAvatar = nombreAvatar;
        this.puerto = puerto;
        this.direccionIP = direccionIP;
        this.listo = false;
        this.host = host;
    }

    public void setNombre(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getNombre() {
        return nombreJugador;
    }

    public void setNombreAvatar(String nombreAvatar) {
        this.nombreAvatar = nombreAvatar;
    }

    String getNombreAvatar() {
        return this.nombreAvatar;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setIP(String ip) {
        this.direccionIP = ip;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public String getIP() {
        return this.direccionIP;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public void misCartas(Carta cartas) {
        cartasLogicasActuales.add(cartas);
    }

    public ArrayList<Carta> verLista() {

        return cartasLogicasActuales;
    }

    public void CartasTablero(Carta cartas) {
        cartasLogicasJugadas.add(cartas);
    }

    public ArrayList<Carta> verCartasTablero() {

        return cartasLogicasJugadas;
    }
}
