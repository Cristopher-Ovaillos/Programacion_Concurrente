package ExamenQueDesaprobe.Ejercicio2Lock;

public class Extractores extends Thread  {
    private Cola a;

    public Extractores(Cola a,int i){
        super(i+"");
        this.a=a;
    }

    public void run(){
       

        while(true){
            try {
                String cad=a.extraer();
                System.out.println(cad);
                

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
  
        }


    }

}
