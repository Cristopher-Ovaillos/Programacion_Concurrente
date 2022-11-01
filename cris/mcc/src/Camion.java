public class Camion extends Thread {
    private Caja cj;

    public Camion(Caja cj) {
        this.cj = cj;

    }

    public void run() {

        try {
            System.out.println("entra");
            cj.almacenLleno();
        } catch (InterruptedException e) {

        }

    }

}
