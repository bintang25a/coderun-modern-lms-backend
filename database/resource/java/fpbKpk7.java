import java.util.Scanner;

public class fpbKpk7 {

    public static int hitungFPB(int a, int b) {
        return (b == 0) ? a : hitungFPB(b, a % b);
    }

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            System.out.println("Program KPK dan FPB NAMA-NIM\n");

            System.out.print("Input Nilai A = ");
            int A = input.nextInt();

            System.out.print("Input Nilai B = ");
            int B = input.nextInt();

            int fpb = hitungFPB(A, B);
            int kpk = (A * B) / fpb;

            System.out.println("\nCetak Hasil FPB = " + fpb);
            System.out.println("\nCetak Hasil KPK = " + kpk);
        }
    }
}
