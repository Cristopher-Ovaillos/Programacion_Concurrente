package ExamenQueDesaprobe.Ejercicio1Lock;

import java.util.Random;

public class Generador extends Thread {
    private Personas persona;
    private Parque p;
    public Generador(Parque p){
        this.p=p;
    }
    public void run(){
        Random x= new Random();
        int i=0;
        while(i!=100){
            int r=x.nextInt(2);
            if(r==0){
                persona= new Personas("RESIDENTE "+i, p, true);
                persona.start();
            }else{
                persona= new Personas("no RESIDENTE "+i, p, false);
                persona.start();
            }
            i++;
        }

    }
    
}
