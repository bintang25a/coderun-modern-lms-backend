import java.util.Scanner;

public class Matriks {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = input.nextInt();

        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];

        System.out.println();
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "] = ");
                matriks[i][j] = input.nextInt();
            }
        }

        int jumlahDiagonal = 0;
        System.out.println("\nNilai Matriks :");

        for (int i = 0; i < baris; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {
                if (i == j) {
                    System.out.print("* ");
                    jumlahDiagonal += matriks[i][j];
                } else {
                    System.out.print(matriks[i][j] + " ");
                }
            }
            System.out.println("|");
        }

        System.out.println("\nHasil penjumlahan diagonal matriks : " + jumlahDiagonal);
    }
}
