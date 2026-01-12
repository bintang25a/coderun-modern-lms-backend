
import java.util.Scanner;

public class fpbKpk5 {

     // ===== Fungsi KPK (dari program KPK) =====
    public static int kpk(int x, int y) {
        int a = x;
        int b = y;
        while (a != b) {
            if (a < b) {
                a = a + x;
            } else {
                b = b + y;
            }
        }
        return a;
    }

    // ===== Fungsi FPB (dari program FPB) =====
    public static int fpb(int x, int y) {
        int temp;
        if (x < y) {
            temp = x;
            x = y;
            y = temp;
        }
        while (y > 0) {
            temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }

    // ===== Program Utama =====
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB NAMA-NIM\n");

        System.out.print("Input Nilai A = ");
        int a = input.nextInt();

        System.out.print("Input Nilai B = ");
        int b = input.nextInt();

        int hasilFPB = fpb(a, b);
        int hasilKPK = kpk(a, b);

        System.out.println("\nCetak Hasil FPB = " + hasilFPB);
        System.out.println("\nCetak Hasil KPK = " + hasilKPK);
    }
}
        
    

