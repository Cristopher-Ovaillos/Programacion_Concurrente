package ExamenQueDesaprobe.BuqueSem;
import java.util.concurrent.Semaphore;

public class buque {

    private Semaphore espacio;
    private Semaphore mutexAuto;
    private Semaphore esperar;
    private Semaphore bajar;
    private Semaphore puedeVolver;
  
    private int auto;
    private int MAX=10;


    public buque(){

        espacio= new Semaphore(MAX,true);
        mutexAuto= new Semaphore(1,true);
        esperar= new Semaphore(0,true);
        bajar= new Semaphore(0);
        puedeVolver= new Semaphore(0);
        auto=0;
    }

    /**
     * 
     */
    public void subirBuque()throws InterruptedException{
        espacio.acquire();
        mutexAuto.acquire();
        auto++;
        System.out.println("Se subio "+Thread.currentThread().getName()+"  un auto al buque.("+auto+"/10)");
        if(auto==MAX){
            //se lleno entonces le doy un permiso que comience a andar
            esperar.release();
        }
        mutexAuto.release();
        
    }

    public void bajarBuque()throws InterruptedException{
        bajar.acquire();
        System.out.println("El auto "+Thread.currentThread().getName()+" bajo del buque :D.");
        auto--;
   
        if(auto==0){
            //es porque ya se vacio
            System.out.println("    YA BAJARON TODOS");
            puedeVolver.release();
        }else{
            bajar.release();
        }
    }

    public void esperarAutos()throws InterruptedException{
        esperar.acquire();
        System.out.println("    YA ESTA LLENO EL BUQUE "+ auto);
    }
    public void desembarcarAutos()throws InterruptedException{
        System.out.println("    El buque ya llego a su destino");
        //aviso a un auto para que pueda salir
        bajar.release();     

    }

    public void volver()throws InterruptedException{
        //espera a que se hayan bajado todos
        puedeVolver.acquire();
        System.out.println("PUEDE VOLVER");
    }
    public void avisarQueYaVolvio()throws InterruptedException{
       
        espacio.release(MAX);
    }


    
}
