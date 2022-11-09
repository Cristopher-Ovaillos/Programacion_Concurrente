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
                despertarCocinero = true;
                /*
                 * porque??? porque si el cocinero por algun motivo recibe el signal sin que
                 * este este dormido
                 * esta señal se pierde y el cocinero va a estar en espera siempre (deadlocke)
                 * por lo tanto lo que hago es un while en generarRaciones que va a estar en
                 * white solo si este no fue avisado
                 * con ayuda de un booleano "despertarCocinero" que esta en false si no fue
                 * despertado
                 * entonces al entrar aca este pasa a true lo que indica que puede ser
                 * despertado.
                 * 
                 * 
                 * 
                 */
                cocinero.signal();
                avisar = false;
                registrar = name;

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
        while (!despertarCocinero) {// este hilo tiene que estar dormido hasta que le haya avisado algun canibal
            cocinero.await();
            /*
             * DespertarCocinero si esta false es que no le aviso el canibal para que cocine.
             * DespertarCocinero si esta true es que le aviso el canibal para que cocine
             * entonces no vuelve a entrar al while.
             * 
             */
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
