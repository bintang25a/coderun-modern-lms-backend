import java.util.Scanner;

public class matrix10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = input.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();
        System.out.println();

        int[][] matriks = new int[baris][kolom];
        int jumlahDiagonal = 0;

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "] = ");
                matriks[i][j] = input.nextInt();
            }
        }

        System.out.println("\nNilai Matriks :");

        for (int i = 0; i < baris; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {
                // Cek jika elemen berada di diagonal utama (indeks baris == indeks kolom)
                if (i == j) {
                    jumlahDiagonal += matriks[i][j];
                    System.out.print("* ");
                } else {
                    System.out.print(matriks[i][j] + " ");
                }
            }
            System.out.println("|");
        }

        System.out.println("Hasil penjumlahan diagonal matriks : " + jumlahDiagonal);

        input.close();
    }
}