import java.util.Scanner;

public class exam23 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        
        do {
            // Tampilan Menu Utama
            System.out.println("========================================");
            System.out.println("        PROGRAM SERBA GUNA JAVA");
            System.out.println("========================================");
            System.out.println("Punya Raihan Abi Nugroho 24040700056");
            System.out.println("Menu Pilihan:");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("========================================");
            System.out.print("Pilih Menu : ");
            
            pilihan = scanner.nextInt();

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
                    System.out.println("Terima kasih, program selesai.");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
                    System.out.println("Menu yang anda masukkan = "+pilihan+" Tidak ditemukan ! ");
            }
            System.out.println(); 
        } while (pilihan != 5);
    }

    // --- MENU 1: PERKALIAN RUSIA ---
    public static void perkalianRusia() {
        System.out.println("\nProgram Perkalian Rusia Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = scanner.nextInt();
        System.out.print("Masukkan Nilai Kedua : ");
        int b = scanner.nextInt();

        System.out.println("\nProses Perkalian:");
        
        int total = 0;
        int currentA = a;
        int currentB = b;

        while (currentA > 0) {
            String keterangan = "";
            if (currentA % 2 != 0) {
                total += currentB;
                keterangan = "ambil " + currentB;
            }
            
            System.out.printf("%-5d %-5d   %s\n", currentA, currentB, keterangan);
            
            currentA /= 2;
            currentB *= 2;
        }
        
        System.out.println("\nHasil Perkalian = " + total);
    }

    // --- MENU 2: HITUNG FPB ---
    public static void hitungFPB() {
        System.out.println("\nProgram FPB Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = scanner.nextInt();
        System.out.print("Masukkan Nilai Kedua : ");
        int b = scanner.nextInt();

        int hasil = cariFPB(a, b);
        
        System.out.println("\nCetak Hasil FPB = " + hasil);
        System.out.println("----------------------------------------");
    }

    public static int cariFPB(int a, int b) {
        if (b == 0) return a;
        return cariFPB(b, a % b);
    }

    // --- MENU 3: HITUNG KPK ---
    public static void hitungKPK() {
        System.out.println("\nProgram KPK Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = scanner.nextInt();
        System.out.print("Masukkan Nilai Kedua : ");
        int b = scanner.nextInt();

        int fpb = cariFPB(a, b);
        int kpk = (a * b) / fpb;

        System.out.println("\nCetak Hasil KPK = " + kpk);
        System.out.println("----------------------------------------");
    }

    // --- MENU 4: PROGRAM MATRIKS (DIPERBAIKI) ---
    public static void programMatriks() {
        System.out.println("\n--- Program Matriks Dinamis ---");
        
        // Input Ukuran Matriks
        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris = scanner.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = scanner.nextInt();

        // Inisialisasi Array Matriks
        int[][] matriks = new int[baris][kolom];

        System.out.println(); 

        // Input Elemen Matriks
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "]  = ");
                matriks[i][j] = scanner.nextInt();
            }
        }

        // Output Menampilkan Matriks
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