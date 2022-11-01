

public class Camion extends Thread{
    private Caja cj;

    public Camion(Caja cj) {
        this.cj = cj;

    }
@Override
public void run() {
    while(true){
        try {
            cj.almacenLleno();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}


}
