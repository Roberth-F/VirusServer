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
     ArrayList<Cartas>MazoCompleto=new ArrayList<>() ;
      ArrayList<Cartas>MazoCartasJugadores=new ArrayList<>() ;
    public void ListaCartas(){
       
        Cartas carta;
        for(int x=0;x<5;x++){
         carta=new Cartas("corazon",1,x+1);
         MazoCompleto.add(carta);
        }
        for(int x=0;x<5;x++){
         carta=new Cartas("estomago",1,x+1);
         MazoCompleto.add(carta);
        }
         for(int x=0;x<5;x++){
         carta=new Cartas("cerebro",1,x+1);
         MazoCompleto.add(carta);
        }
           for(int x=0;x<5;x++){
         carta=new Cartas("hueso",1,x+1);
         MazoCompleto.add(carta);
        }
           for(int x=0;x<1;x++){
         carta=new Cartas("comodin",1,x+1);
         MazoCompleto.add(carta);
        }
         for(int x=0;x<4;x++){
          carta=new Cartas("rojo",2,x+1);
          MazoCompleto.add(carta);
         }  
         for(int x=0;x<4;x++){
          carta=new Cartas("verde",2,x+1);
          MazoCompleto.add(carta);
         }  
          for(int x=0;x<4;x++){
          carta=new Cartas("azul",2,x+1);
          MazoCompleto.add(carta);
         }  
          for(int x=0;x<4;x++){
          carta=new Cartas("amarrillo",2,x+1);
          MazoCompleto.add(carta);
         }
          for(int x=0;x<1;x++){
          carta=new Cartas("comodin",2,x+1);
          MazoCompleto.add(carta);
         }  
            for(int x=0;x<4;x++){
          carta=new Cartas("rojo",3,x+1);
          MazoCompleto.add(carta);
         }  
         for(int x=0;x<4;x++){
          carta=new Cartas("verde",3,x+1);
          MazoCompleto.add(carta);
         }  
          for(int x=0;x<4;x++){
          carta=new Cartas("azul",3,x+1);
          MazoCompleto.add(carta);
         }  
          for(int x=0;x<4;x++){
          carta=new Cartas("amarrillo",3,x+1);
          MazoCompleto.add(carta);
         }
          for(int x=0;x<1;x++){
          carta=new Cartas("comodin",3,x+1);
          MazoCompleto.add(carta);
         }  
           for(int x=0;x<2;x++){
          carta=new Cartas("trasplante",4,x+1);
          MazoCompleto.add(carta);
         }  
          for(int x=0;x<3;x++){
          carta=new Cartas("ladron",4,x+1);
          MazoCompleto.add(carta);
         }  
          for(int x=0;x<3;x++){
          carta=new Cartas("contagio",4,x+1);
          MazoCompleto.add(carta);
         } 
          for(int x=0;x<1;x++){
          carta=new Cartas("guante",4,x+1);
          MazoCompleto.add(carta);
          }
          for(int x=0;x<1;x++){
          carta=new Cartas("guante",4,x+1);
          MazoCompleto.add(carta);
         }
           for(int x=0;x<1;x++){
          carta=new Cartas("error",4,x+1);
          MazoCompleto.add(carta);
         }
         Collections.shuffle(MazoCompleto);
  
//        MazoDeCartas.forEach(datos->{
//           
//         System.out.println(datos.getNombreCarta()+" "+datos.getTipo()+" "+datos.getNumeroCarta());
//   
//        });
        
    }
    public  void distribuirCartas( List<Jugador>listaJu){
      for(int x=0;x<3;x++){
        listaJu.forEach(jugador->{      
               jugador.getNombre();
              Cartas carta=new Cartas(MazoCompleto.get(0).getNombreCarta(),MazoCompleto.get(0).getTipo(),MazoCompleto.get(0).getNumeroCarta(),jugador.getNombre());
              MazoCartasJugadores.add(carta);    
             MazoCompleto.remove(0);
        });
      }
MazoCartasJugadores.forEach(datos->{
           
         System.out.println("Nombre:"+datos.getNombreCarta()+" Tipo:"+datos.getTipo()+" Cantidad:"+datos.getNumeroCarta()+" Dueño:"+datos.getDueñoCarta());
   
        });
    }
    public void CargarCastasJugador(List<Jugador>listaJu){
      for(int x=0;x<3;x++){
       listaJu.forEach(jugador->{      
       Cartas carta=new Cartas(MazoCompleto.get(0).getNombreCarta(),MazoCompleto.get(0).getTipo(),MazoCompleto.get(0).getNumeroCarta());
       jugador.misCartas(carta);
       MazoCompleto.remove(0);
        });
      }
  }
    
}
