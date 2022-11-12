package CentroHomoterapia;

public class Revistas {
    private final int CANTIDAD = 10;
    private int revistasUtilizadas;

    public Revistas() {
        revistasUtilizadas = 1;
    }

    public boolean tomarRevista() {
        boolean tomo = true;
        if (revistasUtilizadas == CANTIDAD) {
            tomo = false;
        } else {
            System.out.println("    El donante "+Thread.currentThread().getName() + " toma (" + revistasUtilizadas + "/9) de revista.");
            revistasUtilizadas++;
        }

        return tomo;
    }

    public void dejoRevista() {
        System.out.println("    El donante "+Thread.currentThread().getName() + " Deja la revista.");
        revistasUtilizadas--;
    }

}
