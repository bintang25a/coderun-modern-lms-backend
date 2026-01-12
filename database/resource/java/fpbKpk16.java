import java.util.Scanner;

public class fpbkpk {

    public static int fpb(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int kpk(int a, int b) {
        return (a * b) / fpb(a, b);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB\n");

        System.out.print("Input Nilai A = ");
        int A = input.nextInt();

        System.out.print("Input Nilai B = ");
        int B = input.nextInt();

        int hasilFPB = fpb(A, B);
        int hasilKPK = kpk(A, B);

        System.out.println("\nCetak Hasil FPB = " + hasilFPB);
        System.out.println("\nCetak Hasil KPK = " + hasilKPK);
    }
}
