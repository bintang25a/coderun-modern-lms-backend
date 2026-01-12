import java.util.Scanner;

public class matrix12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = input.nextInt();

        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i+1) + "] [" + (j+1) + "] = ");
                matriks[i][j] = input.nextInt();
            }
        }

        System.out.println("\nNilai Matriks :");
        for (int i = 0; i < baris; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {
                if (j > 0) System.out.print("* ");
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println("|");
        }

        int jumlahDiagonal = 0;
        int min = Math.min(baris, kolom);
        for (int i = 0; i < min; i++) {
            jumlahDiagonal += matriks[i][i];
        }

        System.out.println("\nHasil penjumlahan diagonal matriks : " + jumlahDiagonal);

        input.close();
    }
}
