package ExamenQueDesaprobe.BuqueLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class buque {
    private int MAX = 10;
    private int autos;

    private Lock mutex = new ReentrantLock(true);
    private boolean avisado = true;

    private Condition esperarLugar, esperarBlleno, esperarLlegadaBarco, yaBajaron;

    public buque() {
        autos = 0;
        esperarLugar = mutex.newCondition();
        esperarLlegadaBarco = mutex.newCondition();
        esperarBlleno = mutex.newCondition();
        yaBajaron = mutex.newCondition();
    }

    // metodos de auto
    public void subirBuque() throws InterruptedException {
        mutex.lock();

        while (autos == MAX) {
            // osea sobrepaso el limite de capacidad del barco ENTONCES ESPERA
            esperarLugar.await();
        }
        autos++;
        System.out.println("Se subio " + Thread.currentThread().getName() + "  un auto al buque.(" + autos + "/10)");
        if (autos == MAX) {
            // aviso que se lleno
            esperarBlleno.signal();
        }

        mutex.unlock();
    }

    public void bajarBuque() throws InterruptedException {
        mutex.lock();
        // si "avisado" es true esporque no se aviso todavia
        if(avisado){
            esperarLlegadaBarco.await();
        }
       

        autos--;
        if (autos == 0) {
            yaBajaron.signal();
        } else {
            // no se vacio entonces aviso a un auto para que pueda salir
            esperarLlegadaBarco.signal();
        }
        System.out.println("El auto " + Thread.currentThread().getName() + " bajo del buque :D.");
        mutex.unlock();
    }

    // metodos de barco

    public void esperarAutos() throws InterruptedException {
        mutex.lock();
        if (autos != 10) {
            // no esta lleno ENTONCES DEBE ESPERAR
            esperarBlleno.await();
        }
        System.out.println("    YA ESTA LLENO EL BUQUE "+ autos);
        mutex.unlock();
    }

    public void desembarcarAutos() throws InterruptedException {
        mutex.lock();
        System.out.println("    El buque ya llego a su destino");
        // aviso que el barco ya llego entoces debo despertar a un auto
        if (avisado) {
            avisado = false;// osea ya avise
            esperarLlegadaBarco.signal();
        }

        mutex.unlock();
    }

    public void volver() throws InterruptedException {
        mutex.lock();
        if (autos != 0) {
            // osea no bajaron todos espero
            yaBajaron.await();
        }
        System.out.println("PUEDE VOLVER");

        mutex.unlock();
    }

    public void avisarQueYaVolvio() throws InterruptedException {
        mutex.lock();
        avisado=true;
        esperarLugar.signalAll();
        mutex.unlock();
    }

}
