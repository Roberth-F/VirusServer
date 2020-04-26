/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.util;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import virusserver.model.Peticion;

/**
 * Sus instancias son capaces de mantenerse a la escucha en un puerto
 * específico.
 *
 * @author Roberth :)
 */
public class Escuchador {

    private final int port;               //puerto de escucha.
    private ServerSocket serverSocket;    //Socket que utiliza para la comunicación.
    private DataInputStream informacion;  //Información que recibe el esperador de parte del cliente.

    /**
     * Inicializa la instancia tomando en cuente el puerto en que se le
     * especifica que debe escuchar.
     *
     * @param port Puerto en el que se dea que el Escuchador esté atento.
     */
    public Escuchador(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(7777);
        } catch (IOException IO) {
            System.err.println("NO SE PUDO HABILITAR PUERTO INICIAL DE ESCUCHA");
            Logger.getLogger(Escuchador.class.getName()).log(Level.SEVERE, IO.getMessage(), IO);
        }
    }

    /**
     * Pone al Escuchador en modo escucha, cuando recibe una conexión
     * reconstrulle la petición recibida y la retorna.
     *
     * @return Petición recibida.
     */
    public Peticion escuchar() {
        try {
            Socket canalComunicacion = serverSocket.accept();
            informacion = new DataInputStream(canalComunicacion.getInputStream());
            String Json = informacion.readUTF();
            Peticion pet = new Gson().fromJson(Json, Peticion.class);
            System.out.println("Recibida petición -->" + pet.getMetodo() + "<--");
            canalComunicacion.getInputStream().close();
            informacion.close();
            canalComunicacion.close();
            return pet;
        } catch (IOException IO) {
            System.err.println("ERROR AL RECIBIR INFORMACIÓN");
            Logger.getLogger(Escuchador.class.getName()).log(Level.SEVERE, IO.getMessage(), IO);
            return null;
        }
    }

}
