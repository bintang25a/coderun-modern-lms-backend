import java.util.Scanner;

public class exam55 {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int menu;

        do {
            tampilMenu();
            System.out.print("Pilih Menu : ");
            menu = input.nextInt();

            switch (menu) {
                case 1:
                    perkalianRusia();
                    break;
                case 2:
                    hitungFPB();
                    break;
                case 3:
                    hitungKPK();
                    break;
                case 4:
                    programMatriks();
                    break;
                case 5:
                    System.out.println("Keluar Program...");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan!");
                    System.out.println("Menu yang anda masukkan = " + menu + " Tidak ditemukan!");
            }
        } while (menu != 5);
    }

    // ================= MENU =================
    static void tampilMenu() {
        System.out.print("==== No 2 Muhamad Ramzy Pradipta (24040700054) ====\n");
        System.out.println("=================================");
        System.out.println("      PROGRAM SERBA GUNA JAVA     ");
        System.out.println("=================================");
        System.out.println("[1] Perkalian Rusia");
        System.out.println("[2] Hitung FPB");
        System.out.println("[3] Hitung KPK");
        System.out.println("[4] Program Matriks");
        System.out.println("[5] Keluar Program");
        System.out.println("=================================");
    }

    // ================= PERKALIAN RUSIA =================
    static void perkalianRusia() {
        System.out.println("\nProgram Perkalian Rusia Terpilih!");
        System.out.println("--------------------------------");

        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int hasil = 0;

        System.out.println("\nProses Perkalian:");
        while (a > 0) {
            System.out.println(a + "\t" + b + (a % 2 != 0 ? "   ambil " + b : ""));
            if (a % 2 != 0) {
                hasil += b;
            }
            a /= 2;
            b *= 2;
        }

        System.out.println("\nHasil Perkalian = " + hasil);
        System.out.println("--------------------------------\n");
    }

    // ================= FPB =================
    static void hitungFPB() {
        System.out.println("\nProgram FPB Terpilih!");
        System.out.println("--------------------------------");

        System.out.print("Masukkan Nilai Pertama : ");
        int x = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int y = input.nextInt();

        int fpb = cariFPB(x, y);

        System.out.println("\nCetak Hasil FPB = " + fpb);
        System.out.println("--------------------------------\n");
    }

    static int cariFPB(int a, int b) {
        while (b != 0) {
            int sisa = a % b;
            a = b;
            b = sisa;
        }
        return a;
    }

    // ================= KPK =================
    static void hitungKPK() {
        System.out.println("\nProgram KPK Terpilih!");
        System.out.println("--------------------------------");

        System.out.print("Masukkan Nilai Pertama : ");
        int x = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int y = input.nextInt();

        int kpk = (x * y) / cariFPB(x, y);

        System.out.println("\nCetak Hasil KPK = " + kpk);
        System.out.println("--------------------------------\n");
    }

    // ================= MATRKS =================
    static void programMatriks() {
        System.out.println("\n--- Program Matriks Dinamis ---");

        System.out.print("Masukkan jumlah baris pada matriks : ");
        int baris = input.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks : ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "] : ");
                matriks[i][j] = input.nextInt();
            }
        }

        System.out.println("\nNilai Matriks:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------\n");
    }
}
