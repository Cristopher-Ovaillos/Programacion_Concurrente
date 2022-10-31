package Tp6;

public class AutoN extends Thread {
    private PuenteEstrecho p;

    public AutoN(String a, PuenteEstrecho p) {
        super(a);
        this.p = p;
    }

    public void run() {
        p.quieroPasar();
        System.out.print("\n Pasando por el puente desde el norte");
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
