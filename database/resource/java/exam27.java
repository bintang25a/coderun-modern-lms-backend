import java.util.Scanner;

public class exam27 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("----------------------------------------");
            System.out.println("        PROGRAM SERBA GUNA JAVA         ");
            System.out.println("----------------------------------------");
            System.out.println("Menu Pilihan:");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("----------------------------------------");
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
                    System.out.println("Terima kasih telah menggunakan program!");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
                    System.out.println("Menu yang anda masukkan = " + pilihan + " Tidak ditemukan !");
            }
            System.out.println();
        } while (pilihan != 5);
    }

    // 1. Logika Perkalian Rusia
    public static void perkalianRusia(Scanner input) {
        System.out.println("\nProgram Perkalian Rusia Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();
        
        System.out.println("\nProses Perkalian:");
        int hasil = 0;
        while (a >= 1) {
            System.out.print(a + "\t" + b);
            if (a % 2 != 0) {
                System.out.print("\tambil " + b);
                hasil += b;
            }
            System.out.println();
            a /= 2;
            b *= 2;
        }
        System.out.println("\nHasil Perkalian = " + hasil);
    }

    // 2. Logika FPB (Greatest Common Divisor)
    public static void hitungFPB(Scanner input) {
        System.out.println("\nProgram FPB Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int n1 = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int n2 = input.nextInt();

        int fpb = cariFPB(n1, n2);
        System.out.println("\nCetak Hasil FPB = " + fpb);
        System.out.println("----------------------------------------");
    }

    // 3. Logika KPK (Least Common Multiple)
    public static void hitungKPK(Scanner input) {
        System.out.println("\nProgram KPK Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int n1 = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int n2 = input.nextInt();

        // Rumus KPK: (n1 * n2) / FPB
        int kpk = (n1 * n2) / cariFPB(n1, n2);
        System.out.println("\nCetak Hasil KPK = " + kpk);
        System.out.println("----------------------------------------");
    }

    // Helper untuk mencari FPB menggunakan Algoritma Euclidean
    public static int cariFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 4. Logika Matriks Dinamis
    public static void programMatriks(Scanner input) {
        System.out.println("\n--- Program Matriks Dinamis ---");
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

        System.out.println("\nNilai Matriks :");
        for (int i = 0; i < baris; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("----------------------------------------");
    }
}