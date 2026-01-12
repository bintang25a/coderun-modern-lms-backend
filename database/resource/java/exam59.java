import java.util.Scanner;

public class soal2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        // Loop utama agar menu muncul kembali setelah selesai satu tugas
        while (true) {
            System.out.println("========================================");
            System.out.println("        PROGRAM SERBA GUNA JAVA");
            System.out.println("========================================");
            System.out.println("Menu Pilihan:");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("========================================");
            System.out.print("Pilih Menu : ");
            
            // Validasi jika input bukan angka
            if (!input.hasNextInt()) {
                System.out.println("Masukkan angka!");
                input.next();
                continue;
            }
            
            pilihan = input.nextInt();

            if (pilihan == 1) {
                // LOGIKA PERKALIAN RUSIA
                System.out.println("\nProgram Perkalian Rusia Terpilih !");
                System.out.println("----------------------------------------");
                System.out.print("Masukkan Nilai Pertama : ");
                int a = input.nextInt();
                System.out.print("Masukkan Nilai Kedua : ");
                int b = input.nextInt();
                System.out.println("\nProses Perkalian:");
                
                int hasilRusia = 0;
                while (a >= 1) {
                    System.out.print(a + "\t" + b);
                    if (a % 2 != 0) {
                        System.out.print("\tambil " + b);
                        hasilRusia += b;
                    }
                    System.out.println();
                    a /= 2;
                    b *= 2;
                }
                System.out.println("\nHasil Perkalian = " + hasilRusia + "\n");

            } else if (pilihan == 2) {
                // LOGIKA FPB
                System.out.println("\nProgram FPB Terpilih !");
                System.out.println("----------------------------------------");
                System.out.print("Masukkan Nilai Pertama : ");
                int n1 = input.nextInt();
                System.out.print("Masukkan Nilai Kedua : ");
                int n2 = input.nextInt();
                
                int x = n1, y = n2;
                while (y != 0) {
                    int temp = y;
                    y = x % y;
                    x = temp;
                }
                System.out.println("\nCetak Hasil FPB = " + x);
                System.out.println("----------------------------------------\n");

            } else if (pilihan == 3) {
                // LOGIKA KPK
                System.out.println("\nProgram KPK Terpilih !");
                System.out.println("----------------------------------------");
                System.out.print("Masukkan Nilai Pertama : ");
                int k1 = input.nextInt();
                System.out.print("Masukkan Nilai Kedua : ");
                int k2 = input.nextInt();
                
                int a = k1, b = k2;
                while (b != 0) {
                    int temp = b;
                    b = a % b;
                    a = temp;
                }
                int kpk = (k1 * k2) / a;
                System.out.println("\nCetak Hasil KPK = " + kpk);
                System.out.println("----------------------------------------\n");

            } else if (pilihan == 4) {
                // LOGIKA MATRIKS
                System.out.println("\n--- Program Matriks Dinamis ---");
                System.out.print("Masukkan jumlah baris pada matriks = ");
                int baris = input.nextInt();
                System.out.print("Masukkan jumlah kolom pada matriks = ");
                int kolom = input.nextInt();
                System.out.println();
                
                int[][] m = new int[baris][kolom];
                for (int i = 0; i < baris; i++) {
                    for (int j = 0; j < kolom; j++) {
                        System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "]  = ");
                        m[i][j] = input.nextInt();
                    }
                }
                
                System.out.println("\nNilai Matriks :");
                for (int i = 0; i < baris; i++) {
                    System.out.print("| ");
                    for (int j = 0; j < kolom; j++) {
                        System.out.print(m[i][j] + " ");
                    }
                    System.out.println("|");
                }
                System.out.println("----------------------------------------\n");

            } else if (pilihan == 5) {
                // KELUAR
                break; 
            } else {
                // JIKA INPUT TIDAK ADA DI MENU (CONTOH INPUT 6)
                System.out.println("Menu tidak di temukan !");
                System.out.println("Menu yang anda masukkan = " + pilihan + " Tidak ditemukan !");
                System.out.println("----------------------------------------");
                // Program berhenti sesuai screenshot "BUILD SUCCESSFUL"
                break; 
            }
        }
    }
}