package Main;

import Tp6.PuenteEstrecho;
import Tp6.generadorDeAutos;

public class ejercicio1TP6 {
    public static void main(String[] args) {
        PuenteEstrecho p = new PuenteEstrecho();
        generadorDeAutos a = new generadorDeAutos(p);
        a.start();

    }
}
