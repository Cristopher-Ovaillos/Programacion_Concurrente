package TrenTuristico;

import java.util.concurrent.Semaphore;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Tren {
    /*
     * Recurso compartido
     * Es una atraccion que hace un recorrido por todo el lugar.
     * Los pasajeros esperan tomar el lugar en el tren.
     * El tren solo puede salir a hacer su recorrido si esta lleno.
     * HILOS
     * ControlTren
     * 
     * Voy a hacer el ejercicio de forma que el tren ya se encuentre esperando a los
     * pasajeros
     * 
     * 
     * 
     * 
     * 
     */
    private final int TOTAL_ASIENTOS = 10;
    private int asientosUtilizados;
    private Semaphore esperarTrenLleno, subirseTren, salirTren, subirseDeAUno, puedeAvisar;

    public Tren() {
        asientosUtilizados = 0;
        esperarTrenLleno = new Semaphore(0);
        subirseTren = new Semaphore(TOTAL_ASIENTOS);
        salirTren = new Semaphore(0);
        subirseDeAUno = new Semaphore(1);
        puedeAvisar = new Semaphore(0);

    }

    // metodo para pasajero
    public void subirseTren() throws InterruptedException {
        // tomo un permiso para sentarme
        subirseTren.acquire();
        subirseDeAUno.acquire();// funciona como synchronized
        asientosUtilizados++;
        System.out.println("El pasajero " + Thread.currentThread().getName() + " tomo (" + asientosUtilizados + "/"
                + TOTAL_ASIENTOS + ") asiento.");
        if (asientosUtilizados == TOTAL_ASIENTOS) {
            System.out.println("    El tren se lleno");
            esperarTrenLleno.release();

        }
        subirseDeAUno.release();// funciona como synchronized
        salirTren.acquire();
        System.out.println("El pasajero " + Thread.currentThread().getName() + " bajo del tren");
        asientosUtilizados--;
        puedeAvisar.release();// esto ayuda para que se bajen de uno a uno y no a la vez
    }

    // metodo controlTren
    public void esperarPasajero() throws InterruptedException {
        esperarTrenLleno.acquire();
        System.out.println("    EL TREN COMIENZA ANDAR POR ALREDEDOR DEL PARQUE");

    }

    // metodo controlTren
    public void terminoRecorrido() throws InterruptedException {
        
        while (asientosUtilizados != 0) {
            salirTren.release();
            puedeAvisar.acquire();
        }
 
        /*
         * porque use un semaforo que lo utiliza el hilo pasajero?
         * Esto porque asi este primero muestra el mensaje este primero antes de que se
         * puedan subir los nuevos pasajeros
         */
        subirseTren.release(TOTAL_ASIENTOS);
        System.out.println("    Se vacio el tren...pueden entrar otras personas.");
    
    }
    // acordarse de usar un semaforo si quiero que sea de a uno para que no ocurra
    // que puedan tocar una variable mas de un hilo a la vez
    // error DE semaforos generales
}
