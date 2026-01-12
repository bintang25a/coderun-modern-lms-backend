import java.util.Scanner;

public class exam16 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("==============================");
            System.out.println("   PROGRAM SERBA GUNA JAVA    ");
            System.out.println("==============================");
            System.out.println("Menu Pilihan:");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("------------------------------");
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
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
            }
            System.out.println();
        } while (pilihan != 5);

        sc.close();
    }

    public static void perkalianRusia(Scanner sc) {
        System.out.println("------------------------------");
        System.out.println("Program Perkalian Rusia Terpilih !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = sc.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = sc.nextInt();

        System.out.println("------------------------------");
        System.out.println("Proses Perkalian:");
        System.out.printf("%-6s %-6s %s\n", "A", "B", "");
        int hasil = 0;

        while (a > 0) {
            if (a % 2 == 1) {
                hasil += b;
                System.out.printf("%-6d %-6d ambil %d\n", a, b, b);
            } else {
                System.out.printf("%-6d %-6d\n", a, b);
            }
            a /= 2;
            b *= 2;
        }

        System.out.println("------------------------------");
        System.out.println("Hasil Perkalian = " + hasil);
    }

    public static void hitungFPB(Scanner sc) {
        System.out.println("------------------------------");
        System.out.println("Program FPB Terpilih !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = sc.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = sc.nextInt();

        int fpb = cariFPB(a, b);
        System.out.println("------------------------------");
        System.out.println("Cetak Hasil FPB = " + fpb);
    }

    public static int cariFPB(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void hitungKPK(Scanner sc) {
        System.out.println("------------------------------");
        System.out.println("Program KPK Terpilih !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = sc.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = sc.nextInt();

        int kpk = (a * b) / cariFPB(a, b);
        System.out.println("------------------------------");
        System.out.println("Cetak Hasil KPK = " + kpk);
    }

    public static void programMatriks(Scanner sc) {
        System.out.println("------------------------------");
        System.out.println("Program Matriks Dinamis");
        System.out.print("Masukkan jumlah baris pada matriks : ");
        int baris = sc.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks : ");
        int kolom = sc.nextInt();

        int[][] matriks = new int[baris][kolom];

        System.out.println("Matriks (" + baris + "x" + kolom + ") :");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "] = ");
                matriks[i][j] = sc.nextInt();
            }
        }

        System.out.println("------------------------------");
        System.out.println("Nilai Matriks :");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }
    }
}