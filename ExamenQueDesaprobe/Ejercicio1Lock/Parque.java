package ExamenQueDesaprobe.Ejercicio1Lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parque {

    private int max = 50;
    private int genteEnParque, genteR;

    private Lock gente = new ReentrantLock(true);
    private Condition genteEsperando, prioridadResidente;
    public Parque() {
        genteEnParque = 0;
        genteR = 0;
        genteEsperando = gente.newCondition();
        prioridadResidente = gente.newCondition();
    }

    public void entrarParque(boolean residente) throws InterruptedException {
        gente.lock();
        while (genteEnParque == max) {
        
            // si supera entra a este while

            if (residente) {
                genteR++;
                prioridadResidente.await();
                genteR--;
            } else {
                genteEsperando.await();
            }
            //si fue avisado es porque un weon salio del parque
            genteEnParque--;
        }
        genteEnParque++;
        System.out.println(Thread.currentThread().getName() + " entra al parque " + genteEnParque + "/50");
        gente.unlock();
    }

    public void salirParque() {
        gente.lock();

        System.out.println(Thread.currentThread().getName() + " sale del parque ");
      
        if (genteR != 0) {
            // osea hay algun residente
            prioridadResidente.signal();
        } else {
            genteEsperando.signal();
        }
        gente.unlock();
    }

}
