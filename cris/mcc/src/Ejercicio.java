public class Ejercicio {
    public static void main(String[] args) {
        int cant = 20;
        Caja cj = new Caja();
        Embotellador[] emb = new Embotellador[cant];
        Empaquetador emp = new Empaquetador(cj);
        Camion c= new Camion(cj);
        for (int i = 0; i < cant; i++) {
            emb[i] = new Embotellador(cj);

        }
        emp.start();
        c.start();
        
       


    }

}
