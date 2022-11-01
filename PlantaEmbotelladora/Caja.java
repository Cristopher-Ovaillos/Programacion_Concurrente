import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caja {

    // para exclusión mutua y tener las condiciones asociadas
    private Lock mutex = new ReentrantLock();
    // para sincronizar los embotelladores sobre mutex
    private Condition embotellador;
    // para sincronizar el empaquetador sobre mutex
    private Condition empaquetador;
    // para sincronizar el camion sobre mutex
    private Condition camion;
    // cantidad botellas de vino y de agua
    private int botellaVino, botellaAgua;
    // maximo de botellas en las cajas
    private final int max = 10;
    // cantidad que hay de cajas en el almacen
    private int almacen, capacidadAlmacen = 100;
    // tipo
    private String tipo;

    public Caja() {

        botellaVino = 0;
        botellaAgua = 0;
        tipo = "";
        almacen = 0;
        this.embotellador = mutex.newCondition();
        this.empaquetador = mutex.newCondition();
        this.camion = mutex.newCondition();
       
    }

    // metodo para embotellador
    public void colocarBotellaAgua() throws InterruptedException {
        mutex.lock();

        while (max == botellaAgua) {
            // esta lleno la caja
            empaquetador.signal();
            tipo = "AGUA";
            embotellador.await();

        }
        botellaAgua++;
        System.out.println("botella de agua :" + botellaAgua);
        mutex.unlock();
    }

    // metodo que utiliza embotellador
    public void colocarBotellaVino() throws InterruptedException {
        mutex.lock();

        while (max == botellaVino) {
            // esta lleno la caja
            empaquetador.signal();
            tipo = "VINO";
            embotellador.await();
        }

        botellaVino++;
        System.out.println("botella de vino :" + botellaVino);
        mutex.unlock();
    }

    // ANOTACION NO PUEDE HABER USUALMENTE UN AWAIT / WAIT SIN UN WHILE();

    // metodo que utiliza empaquetador
    public void empaquetar() throws InterruptedException {

        while (true) {
            mutex.lock();

            // se toma el lock y lo pongo en espera (cuandoe esta en espera pierde el lock)
            // va iterar mientras
            empaquetador.await();

            // el codigo sigue cuando la caja este llena
            System.out.println("");
            System.out.println("El empaquetador toma la caja llena de " + tipo + ".");
            System.out.println("Lo guarda en el almacen");
            almacen++;
            System.out.println("El empaquetador luego le da una nueva caja");
            // para simular una nueva caja solo reinicio el contador

            if (tipo.equals("AGUA")) {
                botellaAgua = 0;

            } else {
                botellaVino = 0;
            }
            // luego de vaciar aviso a todos los embotellador debido a que mientras esto
            // ocurrio puede ocurrir que otra caja ya este lleno de otro tipo de botella
            embotellador.signalAll();   
            //le aviso al camion que se agrego una caja al almacen que espere a que este lleno
            camion.signal();
            System.out.println("");
            mutex.unlock();
        }
    }

    public void almacenLleno() throws InterruptedException {
        mutex.lock();
        while (almacen != capacidadAlmacen) {
            camion.await();
        }
        System.out.println("");
        System.out.println("Almacen lleno");

        System.out.println("el camion se lleva todas las cajas que son " + almacen + " en total.");
        System.out.println("Se vacia el almacen.");
        almacen = 0;
        System.out.println("");
        mutex.unlock();
    }
}
