package ExamenQueDesaprobe.Ejercicio2Lock;

public class Insertores extends Thread {
    private Cola a;

    public Insertores(Cola a,int i){
        super(i+"");
        this.a=a;
    }

    public void run(){

        while(true){
            a.insertar("Dato del insertor "+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }


    
}
