import java.util.Scanner;

public class fpbKpk2 {
    
    // Fungsi FPB (Euclidean Algorithm)
    public static int hitungFPB(int a, int b) {
        while (b != 0) {
            int sisa = a % b;
            a = b;
            b = sisa;
        }
        return a;
    }

    // Fungsi KPK menggunakan rumus: KPK = (A * B) / FPB
    public static int hitungKPK(int a, int b) {
        return (a * b) / hitungFPB(a, b);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB  Jasmine Dafrisa  24040700074\n");

        System.out.print("Input Nilai A = ");
        int A = input.nextInt();

        System.out.print("Input Nilai B = ");
        int B = input.nextInt();

        int fpb = hitungFPB(A, B);
        int kpk = hitungKPK(A, B);

        System.out.println("\nCetak Hasil FPB = " + fpb);
        System.out.println("Cetak Hasil KPK = " + kpk);
    }
}
