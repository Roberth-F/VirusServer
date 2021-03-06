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
import virusserver.util.ManejadorCartas;
import virusserver.util.Respondedor;
import virusserver.util.Respuesta;

/**
 *
 * @author No tiene,esto está más manoseado que botón de semáforo XD
 */
public class Servidor {

    private int turnoActual;
    private final Queue<Peticion> peticiones;                       //Cola de peticiones por atender en caso de que halla saturación.
    private Escuchador bahiaDeConexion;                             //Espera a los jugadores que se quieran unir y los conecta de ser posible.
    private final List<Jugador> jugadoresConectados;                // Máximo soportará 6 jugadores
    private int etapaJuego;                                         // 0 si no hay partida organizada, 1 si está en espera, 2 si ya comenzó.
    private Thread hiloRespondedor;                                 //Es el que se encarga de enviar las respuestas.
    private int votosDeInicio;                                      // Votos de inicio de partida que se han recibido.
    private final ManejadorCartas manejadorCartas = new ManejadorCartas();
    private boolean hayGanador;
    private List<ChatGlobal> chatGlobal = new ArrayList<ChatGlobal>();

    public Servidor() {
        peticiones = new LinkedList<>();
        jugadoresConectados = new ArrayList();
    }

    /**
     * Inicia el funcionamiento del servidor.
     */
    public void start() {
        turnoActual = -1;
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

    private void reset() {
        peticiones.clear();
        turnoActual = -1;
        etapaJuego = 0;
        jugadoresConectados.clear();
        votosDeInicio = 0;
        hayGanador = false;
        chatGlobal.clear();
    }

    @SuppressWarnings("SleepWhileInLoop")
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
                    System.err.print("ALGO RARO PASÓ AL QUERER PAUSAR EL SERVIDOR :V");
                }
            }
        }
    }

    public void iniciarPartida(Peticion pet) {
        Respuesta resp;
        if (etapaJuego != 0) {
            resp = new Respuesta(false, "Ya el juego comenzó, no puedes iniciar partidas nuevas hasta que la actual termine");
        } else {
            resp = new Respuesta(true, "");
            jugadoresConectados.add(new Jugador(pet.getNombreJugador(), pet.getNombreAvatar(), pet.getPuerto(), pet.getIp(), true));
            etapaJuego = 1;
        }

        new Respondedor().responder(resp, pet);
    }

    public void unirsePertida(Peticion pet) {
        Respuesta resp;
        if (etapaJuego == 0) {
            resp = new Respuesta(false, "Aun no hay una partida organizada");
        } else if (etapaJuego == 2) {
            resp = new Respuesta(false, "Ya el juego comenzó, te podrás unir en la próxima ronda");
        } else if (jugadoresConectados.size() >= 6) {
            resp = new Respuesta(false, "Ya esta partida está llena");
        } else if (jugadoresConectados.stream().anyMatch(jug -> jug.getNombre().equals(pet.getNombreJugador()))) {
            resp = new Respuesta(false, "Este nombre ya está en uso.");
        } else {
            resp = new Respuesta(true, "");
            jugadoresConectados.add(new Jugador(pet.getNombreJugador(), pet.getNombreAvatar(), pet.getPuerto(), pet.getIp(), false));

        }
        new Respondedor().responder(resp, pet);
        if (resp.getEstado()) {
            new Actualizador().actualizarSalasDeEspera(jugadoresConectados);
        }
    }

    public void actualizarContrincantes(Peticion pet) {
        jugadoresConectados.forEach((jugador) -> {
            pet.getJugadores().forEach(jug -> {
                jugador.copyCarts(jug);
            });
        });
        buscarGanador();
        if (!hayGanador) {
            Actualizador act = new Actualizador();
            jugadoresConectados.forEach(jugador -> jugador.fixEmptyLists());
            act.refrescarSalasDeJuego(jugadoresConectados, pet.getNombreJugador());
        }
    }

    public void actualizarMensaje(Peticion pet) {
        chatGlobal.clear();
        pet.getChat().forEach(datos -> {
            chatGlobal.add(new ChatGlobal(datos.emisor, datos.mensaje));

        });
        Actualizador act = new Actualizador();
        chatGlobal.size();
        act.actualizarCHAT(jugadoresConectados, chatGlobal);
    }

    public void nuevoJugadorListo(Peticion pet) {
        jugadoresConectados.forEach(act -> {
            if (act.getNombre().equals(pet.getNombreJugador()) && !act.isListo()) {
                act.setListo(true);
                votosDeInicio = Math.toIntExact(jugadoresConectados.stream().filter(jug -> jug.isListo()).count()); //Recuenta jugadores listos
            }
        });
        System.out.println("Nuevo voto de inicio. Votos de inicio: " + String.valueOf(votosDeInicio) + " Jugadores conectados: " + jugadoresConectados.size() + "\n");
    }

    public void desconectarJugador(Peticion pet) {
        Jugador jugador = null;
        for (Jugador act : jugadoresConectados) {
            if (act.getIP().equals(pet.getIp()) && act.getPuerto() == pet.getPuerto()) {       //Se busca en la lista el jugador que va a salir
                jugador = act;
                break;
            }
        }
        if (!jugador.isHost()) {                       //No va a llegar nulo, esta cosa está loca, no le hagan mente :v
            if (etapaJuego == 1) {
                jugadoresConectados.remove(jugador);
                votosDeInicio = Math.toIntExact(jugadoresConectados.stream().filter(jug -> jug.isListo()).count());//Recalcula los votos de inicio
                new Actualizador().actualizarSalasDeEspera(jugadoresConectados);
            } else if (etapaJuego == 2) {
                //TODO ----> Que hacer en caso de que ya halla iniciado el juego.
            }
        } else {
            jugadoresConectados.remove(jugador);
            if (jugadoresConectados.isEmpty()) {     //En caso de ser el único jugador
                votosDeInicio = 0;
                etapaJuego = 0;
                peticiones.clear();
            } else {
                if (etapaJuego == 2) {
                    //TODO ----> Que hacer en caso de que ya halla iniciado el juego.
                } else if (etapaJuego == 1) {
                    votosDeInicio = Math.toIntExact(jugadoresConectados.stream().filter(jug -> jug.isListo()).count());//Recalcula los votos de inicio
                    new Actualizador().actualizarSalasDeEspera(jugadoresConectados);
                }
                Actualizacion act = new Actualizacion();
                act.volverHost();
                jugadoresConectados.get(0).setHost(true);
                new Actualizador().volverHostAJUgador(jugadoresConectados.get(0), act);
            }
        }
    }

    public void startGame(Peticion pet) {
        Respuesta resp;
        if (jugadoresConectados.size() < 3) {
            resp = new Respuesta(false, "No se puede inicir el juego debido a que hay menos de 3 jugadores conectados.");
        } else if (votosDeInicio < jugadoresConectados.size() - 1) {
            resp = new Respuesta(false, "No se puede inicir el juego debido a que no todos los jugadores han indicado que están listos para comenzar.");
        } else {
            resp = new Respuesta(true, "");
        }
        new Respondedor().responder(resp, pet);
    }

    public void forzarInicio(Peticion pet) {
        hayGanador = false;
        Actualizador act = new Actualizador();
        act.cambiarAVistaJuego(jugadoresConectados);
        try {
            Thread.sleep(2000);
            etapaJuego = 2;
            manejadorCartas.ListarCartas();
            manejadorCartas.CargarCartasJugador(jugadoresConectados);
            act = new Actualizador();
            act.cargarDatosInicio(jugadoresConectados);
            Thread.sleep(500);
            pasarTurno(pet);
        } catch (InterruptedException ex) {
            System.err.print("ALGO RARO PASÓ AL QUERER PAUSAR EL SERVIDOR :V");
        }
    }

    public void solicitarCarta(Peticion pet) {
        new Respondedor().ResponderConCarta(manejadorCartas.SolicitarUnaCarta(), pet);
    }

    public void desecharCartas(Peticion pet) {
        manejadorCartas.desecharCarta(pet.getCastasDesecho());
    }

    public void pasarTurno(Peticion pet) {
        turnoActual = (turnoActual == jugadoresConectados.size() - 1) ? 0 : turnoActual + 1;
        new Actualizador().cederTurnoA(jugadoresConectados.get(turnoActual));
    }

    public void buscarGanador() {
        jugadoresConectados.forEach((jugador) -> {
            int organosSanos = 0;
            for (List<Carta> puño : jugador.getCartasLogicasJugadas()) {
                long numeroVirus = puño.stream().filter(carta -> "Virus".equals(carta.getTipo())).count();
                if (numeroVirus == 0) {
                    organosSanos++;
                }
            }
            if (organosSanos > 3) {
                declararUnGanador(jugador);
                etapaJuego = 4;
            }
        });
        if (etapaJuego == 4) {                                        //Si ha habido un ganador.
            reset();
        }
    }

    public void declararUnGanador(Jugador ganador) {
        new Actualizador().enviarMensajeDeGanador(ganador, jugadoresConectados);
    }

    public void forzarChat(Peticion pet) {
        System.out.print("HOLA SOY TU PADRE:" + pet.getMetodo());
        Actualizador act = new Actualizador();
        act.actualizarCHAT(jugadoresConectados, chatGlobal);
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
