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

        Carta carta;
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Cerebro", "Azul");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Estomago", "Verde");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Corazon", "Rojo");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Hueso", "Amarillo");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Organo", "Multicolor");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Rojo");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Verde");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Amarillo");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Azul");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Virus", "Multicolor");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Amarillo");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Azul");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Rojo");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Verde");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Multicolor");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 2; x++) {
            carta = new Carta("Transplante", "Sincolor");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Carta("Ladron", "Sincolor");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Carta("Contagio", "Sincolor");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Guante", "Sincolor");
            masoCartas.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("ErrorMedico", "Sincolor");
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
        desecho.addAll(cartas);
    }

    public void moverDesechoAMaso() {
        masoCartas.addAll(desecho);
        System.out.println("Mazo vacio " + desecho.size()+ " cartas movidas del desecho al mazo");
        desecho.clear();
        masoCartas.forEach(carta -> carta.setCantidad(0));
    }
}
