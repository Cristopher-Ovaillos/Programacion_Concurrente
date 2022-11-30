package ExamenQueDesaprobe.Ejercicio2Sem;

public class main {

    public static void main(String[] args) {
        Buffer a= new Buffer();
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
