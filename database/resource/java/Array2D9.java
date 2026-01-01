import java.util.Scanner;

public class example {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input ukuran array
        System.out.print("Masukkan jumlah baris: ");
        int rows = scanner.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        int cols = scanner.nextInt();

        // Deklarasi array 2 dimensi
        int[][] array = new int[rows][cols];

        // Input elemen array
        System.out.println("Masukkan elemen array:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Elemen [" + i + "][" + j + "]: ");
                array[i][j] = scanner.nextInt();
            }
        }

        // Menampilkan elemen array
        System.out.println("\nArray 2 Dimensi:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
