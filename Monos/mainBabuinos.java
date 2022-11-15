package Babuinos;

import java.util.Random;

public class mainBabuinos {
    public static void main(String[] args) {

        int derCant=40,izqCant=40;
        Cuerda c= new Cuerda(derCant,izqCant);
        BabuinosDerecha[] der= new BabuinosDerecha[derCant];
        BabuinosIzquierda[] izq= new BabuinosIzquierda[derCant];
    
        inicializarDerecha(der, c);
        inicializarIzquierda(izq, c);
        for(int i=0;i<izqCant;i++){
            izq[i].start();
         }
        for(int i=0;i<derCant;i++){
            der[i].start();
        }
       
        
    }

    private static void inicializarDerecha(Thread[] a, Cuerda c){
        int derCant= a.length;
        for(int i=0;i<derCant;i++){
            a[i]= new BabuinosDerecha(i,c);
        }
    }
    private static void inicializarIzquierda(Thread[] a, Cuerda c){
        int izqCant= a.length;
        for(int i=0;i<izqCant;i++){
            a[i]= new BabuinosIzquierda(i,c);
        }
    }
    
    
}
