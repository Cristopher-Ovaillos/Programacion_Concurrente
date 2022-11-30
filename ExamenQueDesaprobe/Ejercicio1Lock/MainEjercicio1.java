package ExamenQueDesaprobe.Ejercicio1Lock;

import java.security.cert.PolicyQualifierInfo;
import java.util.Scanner;

public class MainEjercicio1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Parque p = new Parque();

        Generador a = new Generador(p);
        a.start();
    }

}
