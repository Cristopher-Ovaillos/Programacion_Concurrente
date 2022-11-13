package TrenTuristico;

public class Pasajeros extends Thread {

    private Tren trenTurismo;

    public Pasajeros(int n, Tren t) {
        super("" + n);
        trenTurismo = t;
    }

    public void run() {

        try {
            trenTurismo.subirseTren();
        } catch (InterruptedException e) {
        }
    }

}
