package Tp6;

public class AutoS extends Thread {
    private PuenteEstrecho p;

    public AutoS(String a, PuenteEstrecho p) {
        super(a);
        this.p = p;
    }

    public void run() {
        p.quieroPasar();
        System.out.print("\n Pasando por el puente desde el sur");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.print("Termino");
        
        System.out.println();
p.puedePasar();
    }

}
