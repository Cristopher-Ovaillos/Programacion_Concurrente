package ExamenQueDesaprobe.BuqueLock;

public class Auto extends Thread{
    private buque b;

    public Auto(buque b){
        this.b=b;
    }

    public void run(){

        
            try {
                b.subirBuque();
                b.bajarBuque();
            } catch (InterruptedException e) {}

        

    }
   
    
}
