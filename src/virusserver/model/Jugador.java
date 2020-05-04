/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.annotations.SerializedName;

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

    public Jugador(String nombreJugador, String nombreAvatar, int puerto, String direccionIP) {
        this.nombreJugador = nombreJugador;
        this.nombreAvatar = nombreAvatar;
        this.puerto = puerto;
        this.direccionIP = direccionIP;
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

    public String getIP() {
        return this.direccionIP;
    }

}
