import java.util.Scanner;
public class exam4 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("===============================");
            System.out.println("    FPB DAN KPK    ");
            System.out.println("===============================");
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
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
                    System.out.println("Menu yang anda masukkan = " + pilihan + " Tidak ditemukan !");
                    return;
            }
            System.out.println();
        } while (pilihan != 5);
    }

    static void perkalianRusia() {
        System.out.println("\nProgram Perkalian Rusia Terpilih !");
        System.out.println("---------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int hasil = 0;
        System.out.println("\nProses Perkalian:");
        while (a >= 1) {
            String ambil = (a % 2 != 0) ? "ambil " + b : "";
            System.out.printf("%-10d %-10d %s\n", a, b, ambil);
            if (a % 2 != 0) hasil += b;
            a /= 2;
            b *= 2;
        }
        System.out.println("\nHasil Perkalian = " + hasil);
    }

    static void hitungFPB() {
        System.out.println("\nProgram FPB Terpilih !");
        System.out.println("---------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int n1 = a, n2 = b;
        while (n2 != 0) {
            int temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        System.out.println("\nCetak Hasil FPB = " + n1);
    }

    static void hitungKPK() {
        System.out.println("\nProgram KPK Terpilih !");
        System.out.println("---------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int n1 = a, n2 = b;
        while (n2 != 0) {
            int temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        int kpk = (a * b) / n1;
        System.out.println("\nCetak Hasil KPK = " + kpk);
    }

    static void programMatriks() {
        System.out.println("\n--- Program Matriks Dinamis ---");
        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = input.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];
        System.out.println();

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "]  = ");
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
    }
}