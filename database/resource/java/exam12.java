import java.util.Scanner;

public class exam12 {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int menu;

        do {
            System.out.println("================================");
            System.out.println("        PROGRAM SERBA GUNA JAVA  ");
            System.out.println("================================");
            System.out.println("Menu Pilihan:");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("================================");
            System.out.print("Pilih Menu : ");
            menu = input.nextInt();
            System.out.println();

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
                    System.out.println("Menu tidak di temukan !");
                    System.out.println("Menu yang anda masukkan = " + menu + " Tidak ditemukan !");
            }
            System.out.println();

        } while (menu != 5);
    }

    // ================= PERKALIAN RUSIA =================
    static void perkalianRusia() {
        System.out.println("Program Perkalian Rusia Terpilih !");
        System.out.println("--------------------------------");

        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int hasil = 0;
        System.out.println("\nProses Perkalian:");
        while (a > 0) {
            if (a % 2 == 1) {
                System.out.println(a + "\t" + b + "\tambil " + b);
                hasil += b;
            } else {
                System.out.println(a + "\t" + b);
            }
            a /= 2;
            b *= 2;
        }

        System.out.println("\nHasil Perkalian = " + hasil);
    }

    // ================= FPB =================
    static void hitungFPB() {
        System.out.println("Program FPB Terpilih !");
        System.out.println("--------------------------------");

        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int fpb = cariFPB(a, b);
        System.out.println("\nCetak Hasil FPB = " + fpb);
    }

    // ================= KPK =================
    static void hitungKPK() {
        System.out.println("Program KPK Terpilih !");
        System.out.println("--------------------------------");

        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int kpk = (a * b) / cariFPB(a, b);
        System.out.println("\nCetak Hasil KPK = " + kpk);
    }

    // ================= CARI FPB =================
    static int cariFPB(int a, int b) {
        while (b != 0) {
            int sisa = a % b;
            a = b;
            b = sisa;
        }
        return a;
    }

    // ================= PROGRAM MATRIKS =================
    static void programMatriks() {
        System.out.println("--- Program Matriks Dinamis ---");

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
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }
    }
}
