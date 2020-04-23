/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import virusserver.util.clasePrueba;

/**
 *
 * @author Roberth :)
 */
public class EsperadorLatente {

    private final int Port;
    private final String IpClient;
    private ServerSocket serverSocket;
    private Socket canalComunicacion;
    private DataInputStream informacion;   //Información que recibe el esperador de parte del cliente.

    public EsperadorLatente(int port, String ipClient) {
        this.Port = port;
        this.IpClient = ipClient;
        try {
            this.serverSocket = new ServerSocket(7777);
        } catch (IOException IO) {
            System.err.println("NO SE PUDO HABILITAR PUERTO INICIAL DE ESCUCHA");
            Logger.getLogger(EsperadorLatente.class.getName()).log(Level.SEVERE, IO.getMessage(), IO);
        }
    }

    public Peticion escuchar() {
        try {
            System.out.println("Esperando...");
            canalComunicacion = serverSocket.accept();
            informacion = new DataInputStream(canalComunicacion.getInputStream());
            String str = informacion.readUTF();
            clasePrueba obj = new Gson().fromJson(str, clasePrueba.class);
            canalComunicacion.getInputStream().close();
            informacion.close();
            canalComunicacion.close();
            return new Peticion(str);
        } catch (IOException IO) {
            System.err.println("ERROR AL RECIBIR INFORMACIÓN");
            Logger.getLogger(EsperadorLatente.class.getName()).log(Level.SEVERE, IO.getMessage(), IO);
        }
        return null;
    }

}
