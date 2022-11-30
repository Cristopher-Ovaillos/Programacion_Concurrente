package ExamenQueDesaprobe.Ejercicio2Sem;


public class Extractores extends Thread  {
    private Buffer a;

    public Extractores(Buffer a,int i){
        super(i+"");
        this.a=a;
    }

    public void run(){
       

        while(true){
            try {
                Object cad=a.extraer();
                System.out.println("    "+cad);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
  
        }


    }

}
