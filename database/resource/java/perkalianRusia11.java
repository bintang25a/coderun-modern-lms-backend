import java.util.Scanner;

public class Perkalian_rusia {

    static int perkalian(int a, int b) {
        int i = a;
        int j = b;
        int total = 0;


        System.out.print("\nA\tB\n");

        while (i >= 1) {
            if (i % 2 != 0) {

                System.out.print(i + "\t" + j + "\t+\n");
                total = total + j;
            } else {

                System.out.print(i + "\t" + j + "\n");
            }

            i /= 2;   
            j *= 2;   
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int a, b;

        System.out.print("Masukkan Bilangan 1 : ");
        a = input.nextInt();

        System.out.print("Masukkan Bilangan 2 : ");
        b = input.nextInt();

        System.out.print("\nA \t B\n");
        System.out.println("\n" + a + " * " + b + " = " + perkalian(a, b));
    }
}
