/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import virusserver.model.Actualizacion;
import virusserver.model.Jugador;

/**
 *
 * @author Roberth 😊
 */
public class Actualizador {

    /**
     * Envia actualización de sala de espera a todos los jugadores
     *
     * @param jugList Lista de jugadores
     */
    public void actualizarSalasDeEspera(List<Jugador> jugList) {
        Type typeListJug = new TypeToken<List<Jugador>>() {
        }.getType();
        String jsonList = new Gson().toJson(jugList, typeListJug);
        Actualizacion act = new Actualizacion();
        act.actualizarListaJugadores(jsonList);
        jugList.forEach((actual) -> {
            Thread enviador = new Thread(() -> {
                try {
                    Socket sock = new Socket(actual.getIP(), actual.getPuerto());
                    DataOutputStream datos = new DataOutputStream(sock.getOutputStream());
                    String json = new Gson().toJson(act);
                    datos.writeUTF(json);
                    sock.getOutputStream().close();
                    datos.close();
                    sock.close();
                } catch (UnknownHostException UHE) {
                    Logger.getLogger(Responderdor.class.getName()).log(Level.SEVERE, null, UHE);
                } catch (IOException IO) {
                    Logger.getLogger(Responderdor.class.getName()).log(Level.SEVERE, null, IO);
                }
            });
            enviador.start();
        });
    }
}