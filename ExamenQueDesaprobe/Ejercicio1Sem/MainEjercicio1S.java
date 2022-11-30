package ExamenQueDesaprobe.Ejercicio1Sem;




public class MainEjercicio1S {

    public static void main(String[] args) {


        Parque p = new Parque();

        Generador a = new Generador(p);
        a.start();
    }

}