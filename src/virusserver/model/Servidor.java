/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Roberth :)
 */
public class Servidor {

    private List<EsperadorLatente> Esperadores;     // Esperan las peticiones un cliente específico.
    private Queue<Peticion> Peticiones;             //Podría ser inesesaria si funciona la lista de hilos.
    private EsperadorLatente ConectorInicial;       //Espera a los jugadores que se quieran unir y los conecta de ser posible.
    private List<Thread> Hilos;                     // Hilos de ejecución, uno por cada jugador.
    private int JugadoresConectados;                // Máximo soportará 5 jugadores
    private boolean EnJuego;                        // True si ya el juego comenzó.

    public Servidor() {

    }

    public void start() {
        Esperadores = new ArrayList<>();
        Peticiones = new LinkedList<>();
        Hilos = new ArrayList<>();
        EnJuego = false;
        ConectorInicial = new EsperadorLatente(7777, "");
        ConectorInicial.escuchar(); //Salir y revisar si hay espcio aun;
    }

}
