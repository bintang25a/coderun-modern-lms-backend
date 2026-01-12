import java.util.Scanner;

public class fpbKpk8 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB Anas Novaldi-24040700043");
        System.out.println();

        System.out.print("Input Nilai A = ");
        int a = input.nextInt();

        System.out.print("Input Nilai B = ");
        int b = input.nextInt();

        System.out.println();

        int fpb = hitungFPB(a, b);
        System.out.println("Cetak Hasil FPB = " + fpb);

        System.out.println();

        int kpk = (a * b) / fpb;
        System.out.println("Cetak Hasil KPK = " + kpk);
    }

    public static int hitungFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}