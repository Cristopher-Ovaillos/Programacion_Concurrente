package Babuinos;

public class BabuinosDerecha extends Thread {
    private Cuerda c;

    public BabuinosDerecha(int i,Cuerda c) {
        super(""+i);
        this.c = c;
    }

    public void run() {
        try {
            c.esperarQuePasenDer();
            this.sleep();
            c.pasaCuerda();


        } catch (InterruptedException e) {
        }
    }
    private void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
