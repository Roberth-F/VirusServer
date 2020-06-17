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

    private final ArrayList<Carta> masoCompleto = new ArrayList();
    private final ArrayList<Carta> desecho = new ArrayList();

    public void ListarCartas() {

        Carta carta;
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Cerebro", "Azul");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Estomago", "Verde");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Corazon", "Rojo");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Carta("Hueso", "Amarillo");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Organo", "Multicolor");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Rojo");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Verde");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Amarillo");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Virus", "Azul");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Virus", "Multicolor");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Amarilla");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Azul");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Roja");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Verde");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Carta("Medicina", "Multicolor");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 2; x++) {
            carta = new Carta("Transplante", "Sincolor");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Carta("Ladron", "Sincolor");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Carta("Contagio", "Sincolor");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("Guante", "Sincolor");
            masoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Carta("ErrorMedico", "Sincolor");
            masoCompleto.add(carta);
        }
        Collections.shuffle(masoCompleto);
    }

    public Carta SolicitarUnaCarta() {
        return masoCompleto.remove(0);                //Remove retorna el objeto que se elimino
    }

    public void CargarCartasJugador(List<Jugador> listaJu) {
        for (int x = 0; x < 3; x++) {
            listaJu.forEach(jugador -> {
                jugador.misCartas(masoCompleto.get(0));
                masoCompleto.remove(0);
            });
        }
    }

    public void desecharCarta(Carta carta) {
        desecho.add(carta);
    }

    public void moverDesechoAMaso() {
        if (!desecho.isEmpty()) {
            desecho.forEach(cartaAct -> masoCompleto.add(cartaAct));
            desecho.clear();
        }
    }
}
