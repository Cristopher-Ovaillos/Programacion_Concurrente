package ExamenQueDesaprobe.BuqueSem;
public class main {

    public static void main(String[] args) {
        buque b= new buque();
        Auto[] autos= new  Auto[100];
        Barco barco= new Barco(b);
        barco.start();
        for (int i = 0; i < autos.length; i++) {
            autos[i]= new Auto(b);
        }
        for (int i = 0; i < autos.length; i++) {
            autos[i].start();;
        }

    }
    
}
