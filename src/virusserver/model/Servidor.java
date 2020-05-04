/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import virusserver.util.Escuchador;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import virusserver.util.Actualizador;
import virusserver.util.Responderdor;
import virusserver.util.Respuesta;

/**
 *
 * @author Roberth :)
 */
public class Servidor {        //TOOD  --> Falta guardar IP y puerto de escucha de los jugadores para enviarles actualizaciones.

    private final Queue<Peticion> peticiones;            //Cola de peticiones por atender en caso de que halla saturación.
    private Escuchador bahiaDeConexion;             //Espera a los jugadores que se quieran unir y los conecta de ser posible.
    private final List<Jugador> jugadoresConectados;      // Máximo soportará 6 jugadores
    private int etapaJuego;                        // 0 si no hay partida organizada, 1 si está en espera, 2 si ya comenzó.
    private Thread hiloRespondedor;                 //Es el que se encarga de enviar las respuestas.

    public Servidor() {
        peticiones = new LinkedList<>();
        jugadoresConectados = new ArrayList();
    }

    /**
     * Inicia el funcionamiento del servidor.
     */
    public void start() {
        etapaJuego = 0;
        bahiaDeConexion = new Escuchador(7777);
        System.out.println("Servidor: --> Corriendo\n");
        hiloRespondedor = new Thread(() -> ejecutarPeticiones());
        hiloRespondedor.start();                                                //Arranca ejecución de segundo hilo.
        while (true) {
            Peticion pet = bahiaDeConexion.escuchar();
            synchronized (this.peticiones) {
                this.peticiones.add(pet);
            }
        }
    }

    public void ejecutarPeticiones() {
        while (true) {
            boolean vacia;
            synchronized (this.peticiones) {
                vacia = peticiones.isEmpty();
            }
            if (!vacia) {
                Peticion pet = peticiones.poll();
                Method metodo = getSeverMethod(pet.getMetodo());
                try {
                    metodo.invoke(this, pet);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.err.print("ALGO RARO PASÓ EN LA LINEA 59 DEL SERVIDOR :V");
                }
            }
        }
    }

    public void iniciarPartida(Peticion pet) {
        Respuesta resp;
        if (etapaJuego != 0) {
            resp = new Respuesta(false, "Ya el juego comenzó, no puedes iniciar partidas nuevas hasta que la actual termine");
        } else if (!jugadoresConectados.isEmpty()) {
            resp = new Respuesta(false, "Ya se está armando una partida en este momento.");
        } else {
            resp = new Respuesta(true, "");
            jugadoresConectados.add(new Jugador(pet.getNombreJugador(), pet.getNombreAvatar(), pet.getPuerto(), pet.getIp()));
            etapaJuego = 1;
        }

        new Responderdor().responder(resp, pet);
    }

    public void unirsePertida(Peticion pet) {
        Respuesta resp;
        if (etapaJuego != 2) {
            resp = new Respuesta(false, "Ya el juego comenzó, te podrás unir en la próxima ronda");
        } else if (etapaJuego == 0) {
            resp = new Respuesta(false, "Aun no hay una partida organizada");
        } else if (jugadoresConectados.size() >= 6) {
            resp = new Respuesta(false, "Ya esta partida está llena");
        } else {
            resp = new Respuesta(true, "");
        }
        jugadoresConectados.add(new Jugador(pet.getNombreJugador(), pet.getNombreAvatar(), pet.getPuerto(), pet.getIp()));
        new Responderdor().responder(resp, pet);
        new Actualizador().actualizarSalasDeEspera(jugadoresConectados);
    }
    
    private Method getSeverMethod(String nombre) {
        try {
            return this.getClass().getDeclaredMethod(nombre, Peticion.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
