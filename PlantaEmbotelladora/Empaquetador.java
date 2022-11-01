public class Empaquetador extends Thread {
    private Caja cj;

    public Empaquetador(Caja cj) {
        this.cj = cj;
    }

    public void run() {
       try {
            
              cj.empaquetar();
            
       } catch (InterruptedException e) {
       }

    }

}
