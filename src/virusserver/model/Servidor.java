/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Roberth :)
 */
public class Servidor {

    private Queue<Peticion> Peticiones;             //Podría ser inesesaria si funciona la lista de hilos.
    private EsperadorLatente vahiaDeConexion;       //Espera a los jugadores que se quieran unir y los conecta de ser posible.
    private int JugadoresConectados;                // Máximo soportará 5 jugadores
    private boolean EnJuego;                        // True si ya el juego comenzó.

    public Servidor() {

    }

    public void start() {
        Peticiones = new LinkedList<>();
        EnJuego = false;
        while(true){
            vahiaDeConexion = new EsperadorLatente(7777, "");
            vahiaDeConexion.escuchar(); //Salir y revisar si hay espcio aun;
        }
    }
}
