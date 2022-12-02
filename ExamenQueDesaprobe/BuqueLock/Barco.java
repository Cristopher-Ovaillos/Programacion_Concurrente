package ExamenQueDesaprobe.BuqueLock;

public class Barco extends Thread {

    private buque b;

    public Barco(buque b){
        this.b=b;
    }

    public void run(){

        while(true){
            try {
                b.esperarAutos();
                simulacion();
                b.desembarcarAutos();
                b.volver();
                simulacion();
                b.avisarQueYaVolvio();
            } catch (InterruptedException e) {}

        }

    }
    private void simulacion() throws InterruptedException{

        Thread.sleep(2000);
    }
    
}