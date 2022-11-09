public class Canibal extends Thread {

    private Olla compartido;
    private String nombre;

    public Canibal(Olla olla, int i) {
        nombre=""+i;
        compartido = olla;
    }

    public void run(){
        while(true){

            try {
                compartido.Comer(nombre);
            } catch (InterruptedException e) {
  }

        }
    }
}
