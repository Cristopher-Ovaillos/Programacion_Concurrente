public class MainCanibal {

    public static void main(String[] args) {

        int total = 12;
        Olla rec = new Olla();
        Cocinero coc = new Cocinero(rec);
        Canibal[] can = new Canibal[total];
        crearCanibales(can, rec);
        coc.start();
        activarHilos(can);

    }

    private static void activarHilos(Thread[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].start();
        }
    }

    private static void crearCanibales(Thread[] arr, Olla o) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Canibal(o, i);
        }
    }

}
