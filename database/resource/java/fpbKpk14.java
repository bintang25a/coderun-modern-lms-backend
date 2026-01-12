import java.util.Scanner;

public class fpbdankpk {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB NAMA-NIM\n");

        System.out.print("Input Nilai A = ");
        int a = input.nextInt();

        System.out.print("Input Nilai B = ");
        int b = input.nextInt();

        int fpb = hitungFPB(a, b);

        int kpk = (a * b) / fpb;

        System.out.println("\nCetak Hasil FPB = " + fpb);
        System.out.println("\nCetak Hasil KPK = " + kpk);
    }

    static int hitungFPB(int a, int b) {
        while (b != 0) {
            int sisa = a % b;
            a = b;
            b = sisa;
        }
        return a;
    }
}
