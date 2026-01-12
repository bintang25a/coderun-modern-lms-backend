import java.util.Scanner;

public class fpbKpk10 {

    static int fpb(int a, int b) {
        while (b != 0) {
            int sisa = a % b;
            a = b;
            b = sisa;
        }
        return a;
    }

    static int kpk(int a, int b) {
        return (a * b) / fpb(a, b);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int a, b;

        System.out.println("Program FPB dan KPK");
        System.out.println("====================");
        System.out.print("Input Bilangan 1 : ");
        a = input.nextInt();
        System.out.print("Input Bilangan 2 : ");
        b = input.nextInt();

        System.out.println("\nHasil Perhitungan");
        System.out.println("-----------------");
        System.out.println("FPB = " + fpb(a, b));
        System.out.println("KPK = " + kpk(a, b));

        input.close();
    }
}
