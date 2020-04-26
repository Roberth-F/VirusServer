/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.util;

import com.google.gson.Gson;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import virusserver.model.Peticion;

/**
 * Envia una respuesta a un cliente específico.
 *
 * @author Roberth
 */
public class Responderdor {

    /**
     * Envía una respuesta a único cliente.
     *
     * @param respuesta Respuesta que va ser envida
     * @param pet Petición a que va ser respondida de manera imediata.
     */
    public void responder(Respuesta respuesta, Peticion pet) {
        try {
            Socket sock = new Socket(pet.getIp(), pet.getPuertoImadiato());
            DataOutputStream datos = new DataOutputStream(sock.getOutputStream());
            String Json = new Gson().toJson(respuesta);
            datos.writeUTF(Json);
            sock.getOutputStream().close();
            datos.close();
            sock.close();
            System.out.println("Envida respuesta con estado " + respuesta.getEstado() + "\n");
        } catch (UnknownHostException UHE) {
            Logger.getLogger(Responderdor.class.getName()).log(Level.SEVERE, null, UHE);
        } catch (IOException IO) {
            Logger.getLogger(Responderdor.class.getName()).log(Level.SEVERE, null, IO);
        }
    }
}
