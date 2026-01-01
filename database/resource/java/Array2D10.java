import java.util.Scanner;

/**
 *
 * @author DZAKWAN NUGROHO
 */
public class Pertemuan7 {

    /**
     */

public class Array2Dimensi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input ukuran matriks
        System.out.print("Masukkan jumlah baris matriks: ");
        int baris = scanner.nextInt();
        System.out.print("Masukkan jumlah kolom matriks: ");
        int kolom = scanner.nextInt();

        // Inisialisasi matriks
        int[][] matriksA = new int[baris][kolom];
        int[][] matriksB = new int[baris][kolom];
        int[][] hasilKali = new int[baris][kolom];

        // Input elemen matriks A
        System.out.println("Masukkan elemen matriks A:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("A[" + i + "][" + j + "]: ");
                matriksA[i][j] = scanner.nextInt();
            }
        }

        // Input elemen matriks B
        System.out.println("Masukkan elemen matriks B:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("B[" + i + "][" + j + "]: ");
                matriksB[i][j] = scanner.nextInt();
            }
        }

        // Perkalian matriks
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                hasilKali[i][j] = matriksA[i][j] * matriksB[i][j];
            }
        }

        // Menampilkan hasil perkalian
        System.out.println("Hasil perkalian matriks A dan B:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(hasilKali[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
}
