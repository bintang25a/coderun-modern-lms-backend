import java.util.Scanner;

public class perkalianRusia12 {

    public static int perkalian(int i, int j) {
        int total = 0;

        while (i > 0) {
            System.out.print(i + "\t " + j);

            //Cek apakah i ialah Ganjil
            if (i % 2 != 0) {
                // Jika ganjil, cetak tulisan "ambil" dan tambahkan ke total
                System.out.print("\t ambil " + j);
                total = total + j;
            }

            System.out.println();

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
        
        System.out.println("\nA \t B \n");
        
        // Memanggil fungsi
        int hasil = perkalian(a, b);
        
        System.out.println("\n " + a + " * " + b + " = " + hasil);
    }
}