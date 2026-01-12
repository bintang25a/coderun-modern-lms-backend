import java.util.Scanner;

public class fpbKpk17 {
    static int hitungFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int hitungKPK(int a, int b) {
        return (a * b) / hitungFPB(a, b);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB Muhamad Ramzy - 24040700054\n");

        System.out.print("Input Nilai A = ");
        int A = input.nextInt();

        System.out.print("Input Nilai B = ");
        int B = input.nextInt();
        System.out.println();

        int fpb = hitungFPB(A, B);
        int kpk = hitungKPK(A, B);

        System.out.println("Cetak Hasil FPB = " + fpb + "\n");
        System.out.println("Cetak Hasil KPK = " + kpk);
    }
}
