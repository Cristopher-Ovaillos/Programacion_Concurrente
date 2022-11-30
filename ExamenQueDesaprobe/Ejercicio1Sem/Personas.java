package ExamenQueDesaprobe.Ejercicio1Sem;

import java.util.Random;

public class Personas extends Thread {
    private Parque p;
    private boolean tipo;

    public Personas(String n,Parque p,boolean tipo){
        super(n);
        this.p=p;
        this.tipo=tipo;
    }

    public void run(){

        try {
            p.entrar(tipo);
            hacerActividad();
            p.salir();
        } catch (InterruptedException e) {}

    }

    private void hacerActividad() throws InterruptedException{

        Random x=new Random();
        int i=x.nextInt(5)+1;
        Thread.sleep(i*1000);
    }
    
}
