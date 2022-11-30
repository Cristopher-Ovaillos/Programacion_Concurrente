package ExamenQueDesaprobe.Ejercicio2Sem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer{

private Semaphore insertar;
private Semaphore extraer;
private Semaphore hayElementos;
private Queue<Object> cola1= new LinkedList();
private Queue<Object> cola2= new LinkedList();
private boolean etiqueta;

public Buffer(){
    insertar= new Semaphore(1);
    extraer= new Semaphore(1);
    hayElementos= new Semaphore(0);
    etiqueta=true;

}
public void inserta(Object elem) throws InterruptedException{
    insertar.acquire();

    if(etiqueta){//etiqueta=true cola 1 inserta y cola 2 extrae
        cola1.add(elem);
        System.out.println("se inserto en la cola 1");
    }else{
        cola2.add(elem);
        System.out.println("se inserto en la cola 2");
    }
    insertar.release();
    hayElementos.release();
}


public Object extraer() throws InterruptedException{
    Object retornar;
    hayElementos.acquire();
    extraer.acquire();
    if(etiqueta){

        if(cola2.isEmpty()){//esta vacia
            this.oscilar(2);
        }

    }else{
        if(cola1.isEmpty()){//esta vacia
            this.oscilar(1);
        }
    }

    if(etiqueta){
        retornar=cola2.poll();
        System.out.println("Se elimino un elemento de la cola 2");
    }else{
        retornar=cola1.poll();
        System.out.println("Se elimino un elemento de la cola 1");
    }


    extraer.release();
    return retornar;
}

private void oscilar(int i) throws InterruptedException{
    insertar.acquire();
System.out.println("    se oscilo porque la cola "+i+" estaba vacia.");
    etiqueta=!etiqueta;
    insertar.release();
}
}