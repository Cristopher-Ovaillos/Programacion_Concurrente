package CentroHomoterapia;

import java.util.Random;

public class Donante extends Thread {

    private SalaEspera se;

    public Donante(SalaEspera se, int i) {
        super("" + i);
        this.se = se;

    }

    public void run() {

        try {
            se.pedirTurno();
            se.realizaActividad();
           this.sleep();
            se.terminarActividad();

        } catch (InterruptedException e) {
        }

    }

    private void sleep() {
        Random x= new Random();
        int i=x.nextInt(6)+1;
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
        }
    }

}
