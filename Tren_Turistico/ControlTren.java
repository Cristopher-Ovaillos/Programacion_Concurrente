package TrenTuristico;

public class ControlTren extends Thread {
    private Tren trenTuristico;

    public ControlTren(Tren t) {
        trenTuristico = t;
    }

    public void run() {

        while (true) {

            try {
                trenTuristico.esperarPasajero();
                
                this.trayecto();
               trenTuristico.terminoRecorrido();
     
            } catch (InterruptedException e) {
            }

        }

    }

    private void trayecto() throws InterruptedException {
     
        for (int i = 0; i < 10; i++) {
            System.out.print(".");
            Thread.sleep(100);
        }
        System.out.println("");
        System.out.println("    TERMINO EL RECORRIDO...LOS PASAJEROS PUEDEN BAJAR DE A UNO.");
    }

}
