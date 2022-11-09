import java.net.SocketTimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Olla {

    private Lock mutex = new ReentrantLock();
    private Condition canibal, cocinero;
    private int racionesQueHay, totalOlla;
    private boolean avisar, despertarCocinero;
    private String registrar;

    public Olla() {
        racionesQueHay = 0;
        totalOlla = 10;// raciones que tiene ya la olla
        this.canibal = mutex.newCondition();
        this.cocinero = mutex.newCondition();
        avisar = true;

        registrar = "";
        despertarCocinero = false;
    }

    public void Comer(String name) throws InterruptedException {
        mutex.lock();

        while (racionesQueHay == 0) {// itera si la olla esta vacia
            if (avisar) {
                System.out.println("EL canibal: " + name + "avisa al cocinero.");
                cocinero.signal();
                avisar = false;
                registrar = name;
                despertarCocinero = true;

            }
            canibal.await();// cuando es vacia la olla los canibales esperan al cocinero

        }
        while (!avisar) {// va a iterar hasta que el que aviso llegue y cambia la variable avisar
            if (name == registrar) {
                canibal.signalAll();// supongo que si avisa los otros hilos van a tener que seguir esperando a que
                                    // este hilo termine de ejecutar
                avisar = true;
            } else {
                canibal.await();
            }
        }

        racionesQueHay--;// comio
        System.out.println("EL canibal: " + name + " comio.");
        mutex.unlock();
    }

    public void generarRaciones() throws InterruptedException {
        mutex.lock();
        while (!despertarCocinero) {//este hilo tiene que estar dormido hasta que le haya avisado algun canibal
            cocinero.await();
        }

        if (racionesQueHay != totalOlla) {
            racionesQueHay++;
        } else {
            System.out.println(" HAY AHORA " + racionesQueHay + "  RACIONES.");
            canibal.signalAll();
            cocinero.await();
        }

        mutex.unlock();
    }

}
