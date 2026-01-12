import java.util.Scanner;

public class matrix6 {

    public static void main(String[] args) {

        // Deklarasi Scanner untuk input
        Scanner input = new Scanner(System.in);

        // Deklarasi variabel
        int baris, kolom;
        int jumlahDiagonal = 0;

        // Input jumlah baris
        System.out.print("Masukkan jumlah baris pada matriks = ");
        baris = input.nextInt();

        // Input jumlah kolom
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        kolom = input.nextInt();
        System.out.println();

        // Deklarasi array matriks 2 dimensi
        int[][] matriks = new int[baris][kolom];

        // Input nilai matriks
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "] = ");
                matriks[i][j] = input.nextInt();
            }
        }

        // Menampilkan matriks dan menghitung diagonal
        System.out.println("\nNilai Matriks : ");
        for (int i = 0; i < baris; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {

                // Jika elemen diagonal utama
                if (i == j) {
                    System.out.print("* ");
                    jumlahDiagonal += matriks[i][j];
                } 
                // Elemen selain diagonal
                else {
                    System.out.print(matriks[i][j] + " ");
                }
            }
            System.out.println("|");
        }

        // Menampilkan hasil penjumlahan diagonal
        System.out.println("Hasil penjumlahan diagonal matriks : " + jumlahDiagonal);

        // Menutup Scanner
        input.close();
    }
}
