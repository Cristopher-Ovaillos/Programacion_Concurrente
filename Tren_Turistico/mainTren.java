package TrenTuristico;

import java.util.concurrent.Semaphore;

public class mainTren {

    public static void main(String[] args) {
        Tren t = new Tren();
        int total = 46;
        Pasajeros[] p = new Pasajeros[total];
        ControlTren c = new ControlTren(t);
        inicializarHilos(p,t);
        iniciarHilos(p);
        c.start();
    }

    private static void inicializarHilos(Thread[] p,Tren t) {
        int total = p.length;
        for (int i = 0; i < total; i++) {
            p[i]= new Pasajeros(i, t);
        }

    }
    private static void iniciarHilos(Thread[] p) {
        int total = p.length;
        for (int i = 0; i < total; i++) {
            p[i].start();
        }

    }

}
