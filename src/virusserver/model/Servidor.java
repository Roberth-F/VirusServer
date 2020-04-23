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
public class Servidor {        //TOOD  --> Falta guardar IP y puerto de escucha de los jugadores para enviarles actualizaciones.

    private Queue<Peticion> Peticiones;             //Cola de peticiones por atender en caso de que halla saturación.
    private EsperadorLatente vahiaDeConexion;       //Espera a los jugadores que se quieran unir y los conecta de ser posible.
    private int JugadoresConectados;                // Máximo soportará 5 jugadores
    private boolean EnJuego;                        // True si ya el juego comenzó.

    public Servidor() {

    }

    public void start() {
        Peticiones = new LinkedList<>();
        EnJuego = false;
        vahiaDeConexion = new EsperadorLatente(7777, "");
        while (true) {
            Peticion pet = vahiaDeConexion.escuchar();
            String s = (String) pet.getContenido();
            this.Peticiones.add(pet);
            System.out.println(s);
        }
    }
}
