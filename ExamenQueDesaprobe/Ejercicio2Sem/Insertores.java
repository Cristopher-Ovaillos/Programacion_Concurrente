
package ExamenQueDesaprobe.Ejercicio2Sem;

public class Insertores extends Thread {
    private Buffer a;

    public Insertores(Buffer a,int i){
        super(i+"");
        this.a=a;
    }

    public void run(){

        while(true){
          
           
            
            try {
                a.inserta("Dato del insertor " +Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }


    
}