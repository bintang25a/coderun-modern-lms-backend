
import java.util.Scanner;

public class PerkalianRusia {

     public static int perkalian(int a, int b) {
        int total = 0;
        int i = a;
        int j = b;

        while (i >= 1) {
            System.out.print(i + "\t " + j);

            if (i % 2 != 0) {
                System.out.print("\t ambil " + j);
                total += j;
            }

            System.out.println();

            i /= 2;
            j *= 2;
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan Bilangan 1 : ");
        int a = input.nextInt();

        System.out.print("Masukkan Bilangan 2 : ");
        int b = input.nextInt();

        System.out.println("\nA\t B\n");

        int hasil = perkalian(a, b);

        System.out.println("\n" + a + " * " + b + " = " + hasil);
    }
}