import java.util.Scanner;

public class exam3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int menu;
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan menu : ");
            menu = input.nextInt();
            input.nextLine(); // membersihkan buffer

            switch (menu) {
                case 1:
                    System.out.println("\nProgram Perkalian Rusia Terpilih !");
                    System.out.println("-----------------------------------------");
                    perkalianRusia(input);
                    break;
                case 2:
                    System.out.println("\nProgram FPB Terpilih !");
                    System.out.println("-----------------------------------------");
                    hitungFPB(input);
                    break;
                case 3:
                    System.out.println("\nProgram KPK Terpilih !");
                    System.out.println("-----------------------------------------");
                    hitungKPK(input);
                    break;
                case 4:
                    System.out.println("\nProgram Matriks Dinamis ---");
                    programMatriks(input);
                    break;
                case 5:
                    System.out.println("\nKeluar Program");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
                    break;
            }
            System.out.println();
        } while (menu != 5);

        input.close();
        System.out.println("Terima kasih telah menggunakan program ini!");
    }

    private static void tampilkanMenu() {
        System.out.println("=======================================");
        System.out.println("         PROGRAM SERBA GUNA JAVA       ");
        System.out.println("=======================================");
        System.out.println("Menu Pilihan:");
        System.out.println("[1] Perkalian Rusia");
        System.out.println("[2] Hitung FPB");
        System.out.println("[3] Hitung KPK");
        System.out.println("[4] Program Matriks");
        System.out.println("[5] Keluar Program");
        System.out.println("=======================================");
    }

    private static void perkalianRusia(Scanner input) {
        System.out.print("Masukkan Nilai Pertama : ");
        long a = input.nextLong();
        System.out.print("Masukkan Nilai Kedua   : ");
        long b = input.nextLong();

        long hasil = 0;
        long kiri = a;
        long kanan = b;

        System.out.println("\nProses Perkalian:");
        while (kiri > 0) {
            if (kiri % 2 == 1) {
                System.out.printf("%-10d %-10d ambil %d%n", kiri, kanan, kanan);
                hasil += kanan;
            } else {
                System.out.printf("%-10d %-10d%n", kiri, kanan);
            }
            kiri /= 2;
            kanan *= 2;
        }
        System.out.println("\nHasil Perkalian = " + hasil);
    }

    private static void hitungFPB(Scanner input) {
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int fpb = cariFPB(Math.abs(a), Math.abs(b));
        System.out.println("-----------------------------------------");
        System.out.println("Cetak Hasil FPB = " + fpb);
    }

    private static int cariFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static void hitungKPK(Scanner input) {
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int fpb = cariFPB(Math.abs(a), Math.abs(b));
        int kpk = (Math.abs(a) * Math.abs(b)) / fpb;
        System.out.println("-----------------------------------------");
        System.out.println("Cetak Hasil KPK = " + kpk);
    }

    private static void programMatriks(Scanner input) {
        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = input.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];

        System.out.println();
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.printf("Matriks [%d][%d] = ", i + 1, j + 1);
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