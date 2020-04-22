/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberth :)
 */
public class EsperadorLatente {

    private final int Port;
    private boolean Escuchando;
    private final String IpClient;
    private ServerSocket serverSocket;
    private Socket canalComunicacion;
    private Peticion peticion;
    private DataInputStream informacion;   //Información que recibe el esperador de parte del cliente.

    public EsperadorLatente(int port, String ipClient) {
        this.Port = port;
        this.IpClient = ipClient;
        this.Escuchando = false;
    }

    public boolean escuchar() {
        if (!isEscuchando()) {
            this.Escuchando = true;
            try {
                serverSocket = new ServerSocket(7777);
                canalComunicacion = serverSocket.accept();
                informacion = new DataInputStream(canalComunicacion.getInputStream());
                String str = informacion.readUTF();
                System.out.println(str);
                return true;
            } catch (IOException ex) {
                System.err.println("NO SE PUDO HABILITAR PUERTO INICIAL DE ESCUCHA");
                Logger.getLogger(EsperadorLatente.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
        }
        return false;
    }

    public void ensordecer() {
        if (isEscuchando()) {

        }
    }

    public boolean isEscuchando() {
        return Escuchando;
    }

    public void setEscuchando(boolean escuchando) {
        this.Escuchando = escuchando;
    }
    
}
