package ExamenQueDesaprobe.Ejercicio1Sem;

import java.util.concurrent.Semaphore;

public class Parque {
    private Semaphore noResidente;
    private Semaphore residente;
    private Semaphore mutex;
    private int max = 50;
    private int genteEnParque, genteR;

    public Parque() {
        noResidente = new Semaphore(0);
        mutex = new Semaphore(1);
        residente = new Semaphore(0);
        genteEnParque = 0;
        genteR = 0;
    }

    public void entrar(boolean res) throws InterruptedException {
        mutex.acquire();

        while (genteEnParque == max) {
            // libero el permiso
            mutex.release();
            if (res) {
                genteR++;
                residente.acquire();
                genteR--;
            } else {
                noResidente.acquire();
            }
            mutex.acquire();
            genteEnParque--;
        }
       
        genteEnParque++;
        System.out.println(Thread.currentThread().getName() + " entra al parque (" + genteEnParque + "/50)");

        mutex.release();
    }

    public void salir() throws InterruptedException {
        mutex.acquire();
        System.out.println(Thread.currentThread().getName() + " sale del parque ");
        if (genteR != 0) {
            // osea hay residentes
            residente.release();
        } else {
            // no hay residentes
            noResidente.release();
        }
       
        mutex.release();
    }
}
