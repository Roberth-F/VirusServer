/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusserver.model;

/**
 *
 * @author LordLalo
 */
public class Cartas {
String nombreCarta;
int tipoCarta;
int numeroCarta;
String colorCarta;
String duenno;
    public Cartas(String nombreCarta, int tipoCarta,int numeroCarta) {
        this.nombreCarta = nombreCarta;
        this.tipoCarta = tipoCarta;
        this.numeroCarta=numeroCarta;
    }
    public Cartas(String nombreCarta, int tipoCarta,int numeroCarta,String duenno) {
        this.nombreCarta = nombreCarta;
        this.tipoCarta = tipoCarta;
        this.numeroCarta=numeroCarta;
        this.duenno=duenno;
                
    }
   public void setNombre(String nombre){//Corazon
   this.nombreCarta=nombre;
   
   }
   public  void setNumeroDeCarta(int numeroCarta){
   this.numeroCarta=numeroCarta;
   }
   public   int getNumeroCarta(){
   return this.numeroCarta;
   }
   public void setTipo(int tipoCarta){//Organo=1/virus=2/medicina=3/tratamiento=4
    this.tipoCarta=tipoCarta;
   
   }
   public String  getNombreCarta(){
    return nombreCarta;
   }
   public int getTipo(){
    return tipoCarta;
   }
   public void setColor(String color){
     colorCarta=color;
   }
   public String getColor(){
   return colorCarta;
   }
   public void setDuenno(String duenno){
   this.duenno=duenno;
   }
   public  String getDueñoCarta(){
     return duenno;
   }
   
   }



