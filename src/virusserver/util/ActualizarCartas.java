/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.util;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import virusserver.model.Cartas;
import virusserver.model.Jugador;

/**
 *
 * @author LordLalo
 */
public class ActualizarCartas {

    ArrayList<Cartas> MazoCompleto = new ArrayList<>();
    // ArrayList<Cartas>MazoCartasJugadores=new ArrayList<>() ;

    public void ListaCartas() {

        Cartas carta;
        for (int x = 0; x < 5; x++) {
            carta = new Cartas("Cerebro", "Azul");
            //carta=new Cartas("corazon",1,x+1);
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Cartas("Estomago", "Verde");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Cartas("Corazon", "Rojo");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 5; x++) {
            carta = new Cartas("Hueso", "Amarillo");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Cartas("Organo", "Multicolor");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Cartas("Virus", "Rojo");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Cartas("Virus", "Verde");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Cartas("Virus", "Amarillo");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
            carta = new Cartas("Virus", "Azul");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Cartas("Virus", "Multicolor");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
                  carta=new Cartas("Medicina","Amarilla");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
                  carta=new Cartas("Medicina","Azul");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
                  carta=new Cartas("Medicina","Roja");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
                carta=new Cartas("Medicina","Verde");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 4; x++) {
                  carta=new Cartas("Medicina","Multicolor");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 2; x++) {
            carta = new Cartas("Transplante","Sincolor");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Cartas("Ladron","Sincolor");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 3; x++) {
            carta = new Cartas("Contagio","Sincolor");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Cartas("Guante","Sincolor");
            MazoCompleto.add(carta);
        }
        for (int x = 0; x < 1; x++) {
            carta = new Cartas("ErrorMedico","Sincolor");
            MazoCompleto.add(carta);
        }
        Collections.shuffle(MazoCompleto);

//        MazoDeCartas.forEach(datos->{
//           
//         System.out.println(datos.getNombreCarta()+" "+datos.getTipo()+" "+datos.getNumeroCarta());
//   
//        });
    }

    public void ElimarCarta() {
        MazoCompleto.remove(0);
    }

    public Cartas SolicitarUnaCarta() {
        return MazoCompleto.get(0);

    }

    public void CargarCartasJugador(List<Jugador> listaJu) {
        for (int x = 0; x < 3; x++) {
            listaJu.forEach(jugador -> {
                Cartas carta = new Cartas(MazoCompleto.get(0).getTipo(),MazoCompleto.get(0).getColor());
                jugador.misCartas(carta);
                MazoCompleto.remove(0);
            });
        }
    }

}
