package ExamenQueDesaprobe.Ejercicio2Lock;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cola {

    private Lock extraer = new ReentrantLock();
    private Lock insertar = new ReentrantLock();
    private Queue<Object> cola1 = new LinkedList<>();
    private Queue<Object> cola2 = new LinkedList<>();
    private boolean etiqueta1;
    private Condition esperar;
    private int hayElementos;
    // se cambia la etiqueta solo cuando este esta vacia osea la cola
    // correspondiente despues de extraer

    public Cola() {

        etiqueta1 = true;// false para extraer cola 1  entonces cola 2 inserta //true para insertar cola 1 entonces extrae cola2
        esperar= insertar.newCondition();
        hayElementos=0;
        esperar= extraer.newCondition();
      
    }

    public  Object extraer() throws InterruptedException {
        Object dato;
        
        extraer.lock();
        while(hayElementos==0){
            esperar.await();
        }     
        if(etiqueta1){

            if(cola2.isEmpty()){//esta vacia
                this.cambiarEtiqueta(2);
            }
    
        }else{
            if(cola1.isEmpty()){//esta vacia
                this.cambiarEtiqueta(1);
            }
        }
    
        if(etiqueta1){
            dato=cola2.poll();
            System.out.println("Se elimino un elemento de la cola 2");
        }else{
            dato=cola1.poll();
            System.out.println("Se elimino un elemento de la cola 1");
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
        hayElementos++;
        insertar.unlock();
        extraer.lock();
        esperar.signal();
        extraer.unlock();
    }

    public void cambiarEtiqueta(int i) {
        insertar.lock();
        System.out.println("    se oscilo porque la cola "+i+" estaba vacia.");
        etiqueta1=!etiqueta1;
        insertar.unlock();
    }

}
