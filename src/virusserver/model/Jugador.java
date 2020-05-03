/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

/**
 *
 * @author Roberth
 */
public class Jugador {

    private String nombreJugador;
    private String nombreAvatar;
    private int puerto;
    private String direccionIP;

    public Jugador(String nombreJugador, String nombreAvatar, int puerto, String direccionIP) {
        this.nombreJugador = nombreJugador;
        this.nombreAvatar = nombreAvatar;
        this.puerto = puerto;
        this.direccionIP = direccionIP;
    }

    void setNombre(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    String getNombre() {
        return nombreJugador;
    }

    void setNombreAvatar(String nombreAvatar) {
        this.nombreAvatar = nombreAvatar;
    }

    String getNombreAvatar() {
        return this.nombreAvatar;
    }

    void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    int getPuerto() {
        return puerto;
    }

    void setIP(String ip) {
        this.direccionIP = ip;
    }

    String getIP() {
        return this.direccionIP;
    }

}
