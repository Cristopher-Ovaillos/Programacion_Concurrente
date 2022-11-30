package ExamenQueDesaprobe.Ejercicio2Lock;

public class main {

    public static void main(String[] args) {
        Cola a= new Cola();
        int ext=5,inser=5;

        Extractores[] e= new Extractores[ext];
        Insertores[] i = new Insertores[inser];

        for (int j = 0; j < ext; j++) {
            e[j]= new Extractores(a, j);
            
        }
        for (int j = 0; j < inser; j++) {
            i[j]= new Insertores(a, j);
        }

        for ( int j = 0; j < ext; j++) {
            e[j].start();
        }
        for ( int j = 0; j < inser; j++) {
            i[j].start();
        }
        
    }
    
}
