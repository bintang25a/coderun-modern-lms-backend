import java.util.Scanner;

public class array_dua_dimensi{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input untuk array 2 dimensi
        System.out.println("Masukkan jumlah baris dan kolom untuk matriks:");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrixA = new int[rows][cols];
        int[][] matrixB = new int[rows][cols];
        int[][] resultMatrix = new int[rows][cols];

        System.out.println("Masukkan elemen untuk Matriks A:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixA[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Masukkan elemen untuk Matriks B:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixB[i][j] = scanner.nextInt();
            }
        }

        // Perkalian elemen matrix A dan B
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix[i][j] = matrixA[i][j] * matrixB[i][j];
            }
        }

        // Menampilkan hasil perkalian
        System.out.println("Hasil Perkalian Matriks:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
