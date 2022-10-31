package Tp6;

import java.util.concurrent.Semaphore;

public class PuenteEstrecho {

    private Semaphore puedePasar, crearHilo;

    // constructor
    public PuenteEstrecho() {
        puedePasar = new Semaphore(0);
        crearHilo = new Semaphore(1);
    }

    // metodo que usa los autos
    public void quieroPasar() {

        try {
            puedePasar.acquire();
            System.out.println("El " + Thread.currentThread().getName() + " pudo tomar el puente");

        } catch (InterruptedException e) {
        }
    }
// metodo que usa los autos luego de pasar el puente
    public void puedePasar() {
        System.out.println(Thread.currentThread().getName() + " Termino de pasar");
       
        crearHilo.release();
     
    }

    /**
     * 
     */
    // metodo que usa el generador para crear hilos
    public void puedoCrearHilo() {
      
        try {
            crearHilo.acquire();
            puedePasar.release();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
