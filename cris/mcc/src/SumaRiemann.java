import java.util.Scanner;
public class SumaRiemann {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a, b, c, d, acumulador = 0, area, m, n, particionX, particionY;
        System.out.println("INGRESE M");
        m = sc.nextDouble();
        System.out.println("INGRESE N");
        n = sc.nextDouble();
        System.out.println("Ingrese R=[a,b]x[c,d]");
        a = sc.nextDouble();
        System.out.println("Ingrese R=["+a+",b]x[c,d]");
        b = sc.nextDouble();
        System.out.println("Ingrese R=["+a+","+b+"]x[c,d]");
        c = sc.nextDouble();
        System.out.println("Ingrese R=["+a+","+b+"]x["+c+",d]");
        d = sc.nextDouble();
        System.out.println("Ingrese R=["+a+","+b+"]x["+c+","+d+"]");
        area = ((b - a) * (d - c)) / (m * n);
        System.out.println("Area:  " + area);
        particionX = (b - a) / m;
        particionY = (d - a) / n;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.println("x:" + ((double) i * particionX) + " y: " + ((double) j * particionY));
                acumulador = acumulador + funcion((double) i * particionX, (double) i * particionX);

            }
        }
        System.out.println("Volumen es de " + (acumulador * area));

    }

    private static double funcion(double x, double y) {
        // return 4-((Math.pow(x,2))/2)-((Math.pow(y, 2))/2);
        return 16 - ((Math.pow(x, 2))) - (2 * (Math.pow(y, 2)));
    }
}
