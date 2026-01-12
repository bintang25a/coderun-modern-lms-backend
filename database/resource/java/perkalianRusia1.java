import java.util.Scanner;

public class PerkalianRusia {

    public static int perkalianRusia(int a, int b) {
        int total = 0;

        System.out.println("\nA\tB");

        while (a >= 1) {
            System.out.print(a + "\t" + b);

            if (a % 2 == 1) {
                System.out.print("\tambil " + b);
                total += b;
            }

            System.out.println();

            a = a / 2;
            b = b * 2;
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan Bilangan 1 : ");
        int bil1 = input.nextInt();

        System.out.print("Masukkan Bilangan 2 : ");
        int bil2 = input.nextInt();

        int hasil = perkalianRusia(bil1, bil2);

        System.out.println("\n" + bil1 + " * " + bil2 + " = " + hasil);
    }
}
