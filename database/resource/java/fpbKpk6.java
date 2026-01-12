
import java.util.Scanner;

public class fpbKpk6 {

    // Fungsi untuk menghitung FPB (GCD)
    static int fpb(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Fungsi untuk menghitung KPK
    static int kpk(int a, int b) {
        return (a * b) / fpb(a, b);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB Hanifuddin-24040700062\n");

        System.out.print("Input Nilai A = ");
        int a = input.nextInt();

        System.out.print("Input Nilai B = ");
        int b = input.nextInt();

        System.out.println();

        int hasilFPB = fpb(a, b);
        int hasilKPK = kpk(a, b);

        System.out.println("Cetak Hasil FPB = " + hasilFPB);
        System.out.println();
        System.out.println("Cetak Hasil KPK = " + hasilKPK);
    }
}
