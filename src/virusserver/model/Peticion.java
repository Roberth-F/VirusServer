/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

/**
 *
 * @author Roberth
 */
public class Peticion {

    private Object contenido;

    public Peticion(Object dato) {
        this.contenido = dato;
    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }

}
