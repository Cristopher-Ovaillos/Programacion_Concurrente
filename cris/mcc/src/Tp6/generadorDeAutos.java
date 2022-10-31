package Tp6;

import java.util.Random;

public class generadorDeAutos extends Thread {

    /**
     *
     */
    private static final int _2 = 2;
    private int norte = 1, sur = 1;
    private Thread a;
    private PuenteEstrecho p;

    public generadorDeAutos(PuenteEstrecho p) {
        this.p = p;
    }

    @Override
    public void run() {
        Random x = new Random();
        while (true) {
            p.puedoCrearHilo();
            int i = x.nextInt(2);
            if (i == 0) {
                Thread a = new AutoN("Auto N " + norte, p);
                norte++;
                a.start();
            } else {
                Thread a = new AutoS("Auto S " + sur, p);
                sur++;
                a.start();
            }
            System.out.println("__________________________________________________");

        }

    }

}
