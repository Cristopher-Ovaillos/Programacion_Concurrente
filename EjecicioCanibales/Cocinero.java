public class Cocinero extends Thread {

    private Olla compartido;

    public Cocinero(Olla olla) {
        compartido = olla;
    }

    public void run() {

        while (true) {

            try {
                compartido.generarRaciones();
                System.out.println("Genera una racion");
                cocinando();

            } catch (InterruptedException e) {
            }

        }

    }

    private void cocinando() {
        try {
            System.out.println("");
            for (int i = 0; i <= 3; i++) {
                System.out.print(".");
                Thread.sleep(100);

            }
            System.out.println("");

        } catch (InterruptedException e) {
        }
    }

}
