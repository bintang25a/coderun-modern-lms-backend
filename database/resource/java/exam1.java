import java.util.Scanner;

public class exam1 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n=== MENU PROGRAM ===");
            System.out.println("1. Perkalian Rusia");
            System.out.println("2. Perhitungan FPB");
            System.out.println("3. Perhitungan KPK");
            System.out.println("4. Perhitungan Matriks");
            System.out.println("5. Keluar Program");
            System.out.print("Pilih menu (1-5): ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1 -> perkalianRusia();
                case 2 -> hitungFPB();
                case 3 -> hitungKPK();
                case 4 -> perhitunganMatriks();
                case 5 -> System.out.println("Keluar program. Sampai jumpa!");
                default -> {
                    System.out.println("Menu tidak ditemukan!");
                    // Program akan berhenti jika pilihan salah sesuai permintaan
                    return; 
                }
            }
        } while (pilihan != 5);
    }

    // 1. Fungsi Perkalian Rusia
    static void perkalianRusia() {
        System.out.print("Masukkan bilangan pertama: ");
        int a = input.nextInt();
        System.out.print("Masukkan bilangan kedua: ");
        int b = input.nextInt();
        int hasil = 0;

        System.out.println("Proses:");
        while (a >= 1) {
            if (a % 2 != 0) hasil += b;
            System.out.println(a + "\t" + b + (a % 2 != 0 ? " (Simpan)" : ""));
            a /= 2;
            b *= 2;
        }
        System.out.println("Hasil Perkalian Rusia: " + hasil);
    }

    // 2. Fungsi FPB (Menggunakan Algoritma Euclidean)
    static void hitungFPB() {
        System.out.print("Masukkan bilangan pertama: ");
        int a = input.nextInt();
        System.out.print("Masukkan bilangan kedua: ");
        int b = input.nextInt();
        int fpb = cariFPB(a, b);
        System.out.println("FPB dari " + a + " dan " + b + " adalah: " + fpb);
    }

    static int cariFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 3. Fungsi KPK
    static void hitungKPK() {
        System.out.print("Masukkan bilangan pertama: ");
        int a = input.nextInt();
        System.out.print("Masukkan bilangan kedua: ");
        int b = input.nextInt();
        // Rumus KPK: (a * b) / FPB
        int kpk = (a * b) / cariFPB(a, b);
        System.out.println("KPK dari " + a + " dan " + b + " adalah: " + kpk);
    }

    // 4. Fungsi Perhitungan Matriks (Sesuai Gambar Tugas Anda)
    static void perhitunganMatriks() {
        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = input.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "] = ");
                matriks[i][j] = input.nextInt();
            }
        }

        System.out.println("\nNilai Matriks :");
        int totalDiagonal = 0;
        for (int i = 0; i < baris; i++) {
            System.out.print("| ");
            for (int j = 0; j < kolom; j++) {
                if (i == j) {
                    totalDiagonal += matriks[i][j];
                    System.out.print("* ");
                } else {
                    System.out.print(matriks[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("Hasil penjumlahan diagonal matriks : " + totalDiagonal);
    }
}