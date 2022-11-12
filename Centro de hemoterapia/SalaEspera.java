package CentroHomoterapia;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaEspera {
    private Queue<String> colaEspera = new LinkedList<>();
    private Lock turno = new ReentrantLock();
    private Lock turnoEspera = new ReentrantLock();
    private Condition pasarEnOrden, usoRevista, usoTelevisor;
    private Revistas rev;
    private boolean hayCamillas = true;
    private int TOTAL_CAMILLAS = 4, usoDeCamillas;

    /*
     * Pasos
     * 1.lo primero que hago seria que los donantes pidan su turno y seran llamados
     * por su NOMBRE(haciendo uso de una cola el cual tendra nombres de aquellos que
     * hayan entrado en orden).
     * 2.entran al metodo "realizaActividad" el cual tiene como proposito ver si hay
     * sillas o no.
     *      -Cuando obtienen el lock este entran al modulo "ordenLlegada" el cual tiene
     *      como proposito que pasen en orden en cual llegaron.
     *      - una variable booleana que permite entren al while cuando ya no haya camillas  (osea este variable cambia cuando esten todas ocupadas).
     *      -dentro del metodo "realizaActividad" se hace uso de un modulo el cual
     *      ayudara para hacer que el donante pueda tomar una revista si hay de lo contrario mira las noticias.
     *      -cuando tome una camilla lo que hago es que pase el siguiente persona en la cola (haciendo una señal).
     * 3. dentro de "esperarEnSalaEspera()" cuando un donante toma la revista este avisa a otro donante para que pase 
     *      y luego pasara a espera hasta que se vaya un donante de la camilla el cual se encargara de avisarle.
     *      ( Porque que hago usoRevista.signal()? porque son los que tienen prioridad
     *        DEBIDO A QUE "Los donantes siempre prefieren leer una revista" entonces
     *        esto indicara que aquellos que llegaron antes son los que estan leyendo esto.)
     *      El donante que estaba leyendo y vio que hay una camilla deja la revista entonces este le avisara a un
     *      donante que mira la tele que ...ehh hay una revista libre.
     *      
     * 
     * 
     */

    public SalaEspera(Revistas rev) {
        this.rev = rev;
        this.pasarEnOrden = turnoEspera.newCondition();
        this.usoRevista = turnoEspera.newCondition();
        this.usoTelevisor = turnoEspera.newCondition();
        usoDeCamillas = 0;
    }

    // metodo 2
    public void realizaActividad() throws InterruptedException {
        // camilla>revista>televisor
        turnoEspera.lock();// elimino el frente de la cola
        this.ordenLlegada();
        /*
         * entra y lo elimino porque como ya obtuvo el lock este no afectara en el orden
         * 
         */
        colaEspera.poll();

        while (!hayCamillas) {
            // si entra al while no hay camillas disponibles
            this.esperarEnSalaEspera();
            // si salgo de este modulo ya deje la sala de espera por lo tanto
            // Al pasar a las camillas este dejara la revista entonces solo le tengo que
            // a los que estan viendo las noticias
        }
        System.out.println("El Donante " + Thread.currentThread().getName() + " hace uso de la camilla.");
        usoDeCamillas++;

        if (usoDeCamillas == TOTAL_CAMILLAS) {
            System.out.println("NO HAY CAMILLAS LIBRES :C");
            hayCamillas = false;
           
        } 
        pasarEnOrden.signal();//hace uso de una camilla pasa otro

        turnoEspera.unlock();
    }

    private void esperarEnSalaEspera() throws InterruptedException {

        if (rev.tomarRevista()) {
            pasarEnOrden.signal();// aviso para que pase otro y pueda entrar a este metodo
            usoRevista.await();// lo pongo en espera hasta que haya una camilla disponible
            rev.dejoRevista();// cuando se despierta o sea ya hay camilla
            usoTelevisor.signal();
            /*
             * solo el que esta en mirando la revista es el que avisa que ya hay uno sin
             * usar
             * 
             */
        } else {
            System.out.println("El Donante " + Thread.currentThread().getName()
                    + " SE ENCUENTRA EN LA SALA DE ESPERA mirando noticias :D.");

            usoTelevisor.await();
        }
    }

    // metodo 3
    public void terminarActividad() {
        turnoEspera.lock();
        System.out.println("El Donante " + Thread.currentThread().getName()
                + " termina de usar la camilla.");
        usoDeCamillas--;// deje de usar una camilla
        hayCamillas = true;
        usoRevista.signal();
        /*
         * Porque que hago usoRevista.signal()? porque son los que tienen prioridad
         * DEBIDO A QUE "Los donantes siempre prefieren leer una revista" entonces
         * esto indicara que aquellos que llegaron antes son los que estan leyendo esto.
         */
        turnoEspera.unlock();
    }

    // metodo 1
    public void pedirTurno() {
        // Diferente lock para que no interfiera con las actividades que se realiza en
        // el centro.
        turno.lock();
        // se añade un elemento
        colaEspera.add(Thread.currentThread().getName());
        turno.unlock();
    }

    private void ordenLlegada() throws InterruptedException {

        while (colaEspera.peek() != Thread.currentThread().getName()) {
            pasarEnOrden.await();// no es su turno duerme
            if (colaEspera.peek() != Thread.currentThread().getName()) {
                // solo hago señales cuando no sean iguales (este if sirve para no despertar sin
                // proposito)
                pasarEnOrden.signal();
            }
        }
        System.out.println("El Donante " + Thread.currentThread().getName() + " puede pasar.");
    }

}