// NAMA : FATHONI ADAM ILYASA
// NIM  : 24040700060
import java.util.Scanner;

public class Soal2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("====================================");
            System.out.println("        PROGRAM SERBA GUNA JAVA     ");
            System.out.println("====================================");
            System.out.println("Menu Pilihan:");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("====================================");
            System.out.print("Pilih Menu : ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1:
                    perkalianRusia(sc);
                    break;
                case 2:
                    hitungFPB(sc);
                    break;
                case 3:
                    hitungKPK(sc);
                    break;
                case 4:
                    programMatriks(sc);
                    break;
                case 5:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
                    System.out.println("Menu yang anda masukkan = " + pilihan + " Tidak ditemukan !");
                    break;
            }
        } while (pilihan != 5);

        sc.close();
    }

    // Menu 1: Perkalian Rusia
    public static void perkalianRusia(Scanner sc) {
        System.out.println("\nProgram Perkalian Rusia Terpilih !");
        System.out.print("Masukkan Nilai Pertama : ");
        int meong = sc.nextInt();
        System.out.print("Masukkan Nilai Kedua : ");
        int guguk = sc.nextInt();

        int hasil = 0;
        System.out.println("\nProses Perkalian:");
        while (meong > 0) {
            System.out.printf("%-6d %-6d", meong, guguk);
            if (meong % 2 != 0) {
                hasil += guguk;
                System.out.println("ambil " + guguk);
            } else {
                System.out.println();
            }
            meong /= 2;
            guguk *= 2;
        }
        System.out.println("\nHasil Perkalian = " + hasil + "\n");
    }

    // Menu 2: Hitung FPB
    public static void hitungFPB(Scanner sc) {
        System.out.println("\nProgram FPB Terpilih !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = sc.nextInt();
        System.out.print("Masukkan Nilai Kedua : ");
        int b = sc.nextInt();

        int fpb = cariFPB(a, b);
        System.out.println("\nCetak Hasil FPB = " + fpb);
        System.out.println("------------------------------------\n");
    }

    public static int cariFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Menu 3: Hitung KPK
    public static void hitungKPK(Scanner sc) {
        System.out.println("\nProgram KPK Terpilih !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = sc.nextInt();
        System.out.print("Masukkan Nilai Kedua : ");
        int b = sc.nextInt();

        int kpk = (a * b) / cariFPB(a, b);
        System.out.println("\nCetak Hasil KPK = " + kpk);
        System.out.println("------------------------------------\n");
    }

    // Menu 4: Program Matriks Dinamis
    public static void programMatriks(Scanner sc) {
        System.out.println("\n--- Program Matriks Dinamis ---");
        System.out.print("Masukkan jumlah baris pada matriks = ");
        int barizz = sc.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = sc.nextInt();

        int[][] matriks = new int[barizz][kolom];

        for (int i = 0; i < barizz; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i+1) + "][" + (j+1) + "] = ");
                matriks[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nNilai Matriks :");
        for (int i = 0; i < barizz; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }
}
