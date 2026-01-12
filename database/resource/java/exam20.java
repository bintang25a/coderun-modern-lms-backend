

import java.util.Scanner;


public class SOAL2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        // Perulangan agar menu terus muncul sampai user memilih keluar
        do {
            System.out.println("===============================");
            System.out.println("    PROGRAM SERBA GUNA JAVA    ");
            System.out.println("-------------------------------");
            System.out.println("Menu Pilihan:");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("===============================");
            System.out.print("Pilih Menu : ");
            
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    perkalianRusia(input);
                    break;
                case 2:
                    hitungFPB(input);
                    break;
                case 3:
                    hitungKPK(input);
                    break;
                case 4:
                    programMatriks(input);
                    break;
                case 5:
                    System.out.println("\nKeluar Program...");
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid! Silakan pilih 1-5.");
            }
            System.out.println(); // Baris kosong untuk kerapihan
            
        } while (pilihan != 5); // Program berhenti jika pilihan adalah 5

        input.close();
        System.exit(0); // Memutus proses program sepenuhnya
    }

    // --- 1. Method Perkalian Rusia ---
    public static void perkalianRusia(Scanner input) {
        System.out.println("\nPROGRAM PERKALIAN RUSIA TERPILIH !");
        System.out.println("----------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        System.out.println("\nProses Perkalian:");
        int hasil = 0;
        while (a >= 1) {
            String ambil = (a % 2 != 0) ? "ambil " + b : "";
            System.out.printf("%-10d %-10d %s\n", a, b, ambil);
            if (a % 2 != 0) {
                hasil += b;
            }
            a /= 2;
            b *= 2;
        }
        System.out.println("\nHasil Perkalian = " + hasil);
    }

    // --- 2. Method FPB ---
    public static void hitungFPB(Scanner input) {
        System.out.println("\nPROGRAM FPB TERPILIH !");
        System.out.println("----------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int n1 = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int n2 = input.nextInt();

        int a = n1, b = n2;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        System.out.println("\nCetak Hasil FPB = " + a);
    }

    // --- 3. Method KPK ---
    public static void hitungKPK(Scanner input) {
        System.out.println("\nPROGRAM KPK TERPILIH !");
        System.out.print("Masukkan Nilai Pertama : ");
        int n1 = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int n2 = input.nextInt();

        int a = n1, b = n2;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        int kpk = (n1 * n2) / a;
        System.out.println("\nCetak Hasil KPK = " + kpk);
    }

    // --- 4. Method Matriks ---
    public static void programMatriks(Scanner input) {
        System.out.println("\nPROGRAM MATRIKS TERPILIH !");
        System.out.print("Masukkan jumlah baris: ");
        int baris = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Isi elemen [" + i + "][" + j + "]: ");
                matriks[i][j] = input.nextInt();
            }
        }

        System.out.println("\nHasil Matriks:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

        
 
