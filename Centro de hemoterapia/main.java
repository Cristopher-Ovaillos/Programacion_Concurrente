package CentroHomoterapia;
public class main {


    public static void main(String[] args) {
        
        Revistas rev = new Revistas();
        SalaEspera salaEspera= new SalaEspera(rev);
        Donante[] d= new Donante[100];
        for (int i = 0; i < 100; i++) {
            d[i]= new Donante(salaEspera, i);

        }
        for (int i = 0; i < 100; i++) {
            d[i].start();

        }
    }

 
    
}
