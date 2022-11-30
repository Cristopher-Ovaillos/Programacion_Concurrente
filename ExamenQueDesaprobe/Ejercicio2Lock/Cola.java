package ExamenQueDesaprobe.Ejercicio2Lock;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cola {

    private Lock extraer = new ReentrantLock();
    private Lock insertar = new ReentrantLock();
    private Lock etiqueta = new ReentrantLock();
    private Queue<String> cola1 = new LinkedList<>();
    private Queue<String> cola2 = new LinkedList<>();
    private boolean etiqueta1;
    private Condition c;
    // se cambia la etiqueta solo cuando este esta vacia osea la cola
    // correspondiente despues de extraer

    public Cola() {

        etiqueta1 = false;// false para extraer cola 1  entonces cola 2 inserta //true para insertar cola 1 entonces extrae cola2
        c= insertar.newCondition();
      
    }

    public  String extraer() throws InterruptedException {
        String dato="";
        extraer.lock();
      
        if (!etiqueta1) {
            // la cola 1 es la extrae
            if (cola1.isEmpty()) {
                // si esta vacia se hace el cambio
                cambiarEtiqueta();
             
            }else{
                dato=cola1.poll();
                System.out.println("Se elimino un elemento de la cola 1");
            }
        } else {
            // la cola 2 es la extrae
            if (cola2.isEmpty()) {
                // si esta vacia se hace el cambio
                cambiarEtiqueta();
                //cambia la etiqueta y como esta vacia no va a  poder extraer hasta que se inserte un elemento en la cola 2
            
            }else{
                dato=cola2.poll();
            System.out.println("Se elimino un elemento de la cola 2");
            }
        }
        extraer.unlock();
        return dato;
    }

    public void insertar(String dato) {
        insertar.lock();

        if (etiqueta1) {
            // cola 1
            cola1.add(dato);
            System.out.println("se inserto en la cola 1");
        
        } else {
            // cola 2
            cola2.add(dato);
            System.out.println("se inserto en la cola 2");
         
        }
  
        
        //ya se inserto un elemento en alguna cola
    

        insertar.unlock();
    }

    public void cambiarEtiqueta() {
        etiqueta.lock();
        if (etiqueta1 == true) {
            // si la etiqueta 1 esta extrae ahora debe insertar
            etiqueta1 = false;

        } else {
            etiqueta1 = true;
        }
        etiqueta.unlock();
    }

}
