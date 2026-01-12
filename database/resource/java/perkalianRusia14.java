import java.util.Scanner;

public class perkalianRusia14 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Bilangan 1 : ");
        int a = input.nextInt();
        System.out.print("Masukkan Bilangan 2 : ");
        int b = input.nextInt();
        int simpanA = a;
        int simpanB = b;
        int total = 0;
        System.out.println("\nA\t\tB");
        
        while (a >= 1) {
            System.out.print(a + "\t\t" + b);
            
            // Cek jika A ganjil
            if (a % 2 != 0) {
                System.out.print("\t\tambil " + b);
                total += b;
            }
            
            System.out.println();
            a /= 2;
            b *= 2;
        }

        System.out.println("\n" + simpanA + " * " + simpanB + " = " + total);
        System.out.println("BUILD SUCCESSFUL");
    }
}