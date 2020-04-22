/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver;

import virusserver.model.Servidor;

/**
 *
 * @author Roberth
 */
public class VirusServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servidor server = new Servidor();
        server.start();
    }
    
}
