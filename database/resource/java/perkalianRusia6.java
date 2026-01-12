import java.util.Scanner;

public class Perkalian_rusia {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Bilangan 1 : ");
        int A = input.nextInt();
        System.out.print("Masukkan Bilangan 2 : ");
        int B = input.nextInt();
        int a = A;
        int b = B;
        int hasil = 0;
        System.out.println("\nA\tB");

        while (a > 0) {
            System.out.print(a + "\t" + b);
            if (a % 2 == 1) {
                System.out.print("\tambil " + b);
                hasil += b;
            }
            System.out.println();
            a /= 2;
            b *= 2;
        }
        System.out.println("\n" + A + " * " + B + " = " + hasil);
    }
}
