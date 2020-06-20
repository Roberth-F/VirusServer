/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.util;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import virusserver.model.Carta;
import virusserver.model.Jugador;

/**
 *
 * @author LordLalo
 */
public class ManejadorCartas {

    private final ArrayList<Carta> masoCartas = new ArrayList();
    private final ArrayList<Carta> desecho = new ArrayList();

    public void ListarCartas() {
        masoCartas.clear();
        desecho.clear();
        Carta carta;
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Cerebro", "Azul", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Estomago", "Verde", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Corazon", "Rojo", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Hueso", "Amarillo", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Organo", "Multicolor", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Rojo", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Verde", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Amarillo", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Azul", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Virus", "Multicolor", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Amarillo", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Azul", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Rojo", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Verde", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Multicolor", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 2; x++) {
            carta = new Carta("Transplante", "Sincolor", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Carta("Ladron", "Sincolor", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Carta("Contagio", "Sincolor", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Guante", "Sincolor", 0);
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("ErrorMedico", "Sincolor", 0);
            masoCartas.add(carta);
        }
        Collections.shuffle(masoCartas);
    }

    public Carta SolicitarUnaCarta() {
        if (masoCartas.isEmpty()) {
            moverDesechoAMaso();
        }
        Collections.shuffle(masoCartas);
        return masoCartas.remove(0);                //Remove retorna el objeto que se elimino
    }

    public void CargarCartasJugador(List<Jugador> listaJu) {
        for (int x = 0; x < 3; x++) {
            listaJu.forEach(jugador -> {
                jugador.getCartasLogicasActuales().add(masoCartas.remove(0));     //Remove retorna el objeto que se elimino
            });
        }
    }

    public void desecharCarta(List<Carta> cartas) {
        System.out.println(cartas.size() + " cartas puestas en pila de desecho");
        cartas.forEach(carta -> carta.setPosicion(0));
        desecho.addAll(cartas);
    }

    public void moverDesechoAMaso() {
        masoCartas.addAll(desecho);
        System.out.println("Mazo vacio " + desecho.size() + " cartas movidas del desecho al mazo");
        desecho.clear();
        masoCartas.forEach(carta -> carta.setCantidad(0));
    }
}
