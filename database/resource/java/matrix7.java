import java.util.Scanner;
public class PenjumlahanDiagonalMatriks {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = input.nextInt();

        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();

        int[][] matrix = new int[baris][kolom];

        System.out.println("\nMasukkan nilai matriks:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "] = ");
                matrix[i][j] = input.nextInt();
            }
        }

        int jumlahDiagonal = 0;
        System.out.println("\nNilai Matriks :");
        for (int i = 0; i < baris; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {
                if (i == j) {
                    System.out.print("* ");
                    jumlahDiagonal += matrix[i][j];
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println("|");
        }

        System.out.println("Hasil penjumlahan diagonal matriks : " + jumlahDiagonal);
    }
}