import java.util.Random;

public class Embotellador extends Thread {

    private Caja cj;

    public Embotellador(Caja cj) {
        this.cj = cj;

    }

    public void run() {
        Random x = new Random();
        try {
            while (true) {
                int j = x.nextInt(4) + 1;
                int i = x.nextInt(2);

                if (i == 0) {
                    cj.colocarBotellaAgua();
                } else {
                    cj.colocarBotellaVino();
                }

                Thread.sleep((j * 1000));

            }
        } catch (InterruptedException e) {
        }
    }

}
