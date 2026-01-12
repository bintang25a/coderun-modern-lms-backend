import java.util.Scanner;
import java.util.InputMismatchException;

public class exam14 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        
        do {
            tampilkanMenu();
            System.out.print("Pilih menu (1-4): ");
            
            try {
                pilihan = scanner.nextInt();
                
                switch(pilihan) {
                    case 1:
                        hitungPerkalianRusia(scanner);
                        break;
                    case 2:
                        hitungPerkalianRusiaDikaliDua(scanner);
                        break;
                    case 3:
                        jelaskanAlgoritma();
                        break;
                    case 4:
                        contohPenggunaan(scanner);
                        break;
                    case 5:
                        System.out.println("\nTerima kasih telah menggunakan program Perkalian Rusia!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid! Silakan pilih 1-5.");
                }
                
                if(pilihan != 5) {
                    System.out.println("\nTekan Enter untuk kembali ke menu...");
                    scanner.nextLine(); // Clear buffer
                    scanner.nextLine(); // Tunggu Enter
                }
                
            } catch(InputMismatchException e) {
                System.out.println("Input harus angka!");
                scanner.next(); // Clear invalid input
                pilihan = 0;
            }
            
        } while(pilihan != 5);
        
        scanner.close();
    }
    
    public static void tampilkanMenu() {
        System.out.println("\n=========================================");
        System.out.println("        MENU PERKALIAN RUSIA");
        System.out.println("=========================================");
        System.out.println("1. Hitung Perkalian Rusia Biasa");
        System.out.println("2. Hitung Perkalian Rusia × 2");
        System.out.println("3. Penjelasan Algoritma");
        System.out.println("4. Contoh Penggunaan");
        System.out.println("5. Keluar");
        System.out.println("=========================================");
    }
    
    public static void hitungPerkalianRusia(Scanner scanner) {
        System.out.println("\n=== PERKALIAN RUSIA BIASA ===");
        
        System.out.print("Masukkan bilangan pertama: ");
        int a = scanner.nextInt();
        
        System.out.print("Masukkan bilangan kedua: ");
        int b = scanner.nextInt();
        
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║          PROSES PERKALIAN RUSIA           ║");
        System.out.println("╠══════════╦══════════╦══════════╦══════════╣");
        System.out.println("║   Langkah  ║     a    ║     b    ║  Hasil   ║");
        System.out.println("╠══════════╬══════════╬══════════╬══════════╣");
        
        int hasil = 0;
        int langkah = 1;
        int aTemp = a;
        int bTemp = b;
        
        while(aTemp > 0) {
            boolean ganjil = (aTemp % 2 == 1);
            
            if(ganjil) {
                hasil += bTemp;
                System.out.printf("║ %8d ║ %8d ║ %8d ║ %8d ║ ← GANJIL\n", 
                    langkah, aTemp, bTemp, hasil);
            } else {
                System.out.printf("║ %8d ║ %8d ║ %8d ║ %8d ║ ← GENAP\n", 
                    langkah, aTemp, bTemp, hasil);
            }
            
            aTemp = aTemp / 2;
            bTemp = bTemp * 2;
            langkah++;
        }
        
        System.out.println("╠══════════╩══════════╩══════════╩══════════╣");
        System.out.printf("║ HASIL AKHIR: %d × %d = %-20d║\n", a, b, hasil);
        System.out.println("╚════════════════════════════════════════════╝");
        
        // Verifikasi
        int verifikasi = a * b;
        System.out.println("\nVerifikasi perkalian biasa: " + a + " × " + b + " = " + verifikasi);
        System.out.println("Status: " + (hasil == verifikasi ? "✓ BENAR" : "✗ SALAH"));
    }
    
    public static void hitungPerkalianRusiaDikaliDua(Scanner scanner) {
        System.out.println("\n=== PERKALIAN RUSIA × 2 ===");
        
        System.out.print("Masukkan bilangan pertama: ");
        int a = scanner.nextInt();
        
        System.out.print("Masukkan bilangan kedua: ");
        int b = scanner.nextInt();
        
        // Hitung perkalian Rusia biasa dulu
        int hasilRusia = hitungTanpaTampilan(a, b);
        int hasilAkhir = hasilRusia * 2;
        
        System.out.println("\n┌────────────────────────────────────────────┐");
        System.out.println("│            PROSES PERHITUNGAN            │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.printf("│ Perkalian Rusia biasa: %d × %d = %d     │\n", a, b, hasilRusia);
        System.out.printf("│ Dikali 2: %d × 2 = %d                  │\n", hasilRusia, hasilAkhir);
        System.out.println("├────────────────────────────────────────────┤");
        System.out.printf("│ HASIL: %d × %d × 2 = %d               │\n", a, b, hasilAkhir);
        System.out.println("└────────────────────────────────────────────┘");
        
        // Tampilkan detail proses
        System.out.println("\nDetail langkah-langkah:");
        System.out.println("+---------+---------+---------+---------+");
        System.out.println("|    a    |    b    |  a % 2  |  Total  |");
        System.out.println("+---------+---------+---------+---------+");
        
        int total = 0;
        int aTemp = a;
        int bTemp = b;
        int step = 1;
        
        while(aTemp > 0) {
            boolean ganjil = (aTemp % 2 == 1);
            
            if(ganjil) {
                total += bTemp;
                System.out.printf("| %7d | %7d |  GANJIL | %7d |\n", 
                    aTemp, bTemp, total);
            } else {
                System.out.printf("| %7d | %7d |   GENAP | %7d |\n", 
                    aTemp, bTemp, total);
            }
            
            aTemp = aTemp / 2;
            bTemp = bTemp * 2;
            step++;
        }
        
        System.out.println("+---------+---------+---------+---------+");
        
        // Verifikasi
        int verifikasi = a * b * 2;
        System.out.println("\nVerifikasi: " + a + " × " + b + " × 2 = " + verifikasi);
        System.out.println("Status: " + (hasilAkhir == verifikasi ? "✓ BENAR" : "✗ SALAH"));
    }
    
    public static void jelaskanAlgoritma() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("          PENJELASAN ALGORITMA PERKALIAN RUSIA");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("\nAlgoritma Perkalian Rusia (Russian Peasant Multiplication)");
        System.out.println("adalah metode perkalian kuno yang efisien untuk mengalikan");
        System.out.println("dua bilangan tanpa menggunakan tabel perkalian.");
        
        System.out.println("\n┌────────────────────────────────────────────────┐");
        System.out.println("│            LANGKAH-LANGKAH ALGORITMA           │");
        System.out.println("├────────────────────────────────────────────────┤");
        System.out.println("│ 1. Tulis dua bilangan yang akan dikalikan      │");
        System.out.println("│ 2. Bagi bilangan pertama dengan 2 (bulat ke    │");
        System.out.println("│    bawah) dan kalikan bilangan kedua dengan 2  │");
        System.out.println("│ 3. Jika bilangan pertama ganjil, tambahkan     │");
        System.out.println("│    bilangan kedua ke hasil                     │");
        System.out.println("│ 4. Ulangi langkah 2-3 hingga bilangan pertama  │");
        System.out.println("│    menjadi 0                                   │");
        System.out.println("│ 5. Jumlah semua bilangan kedua yang ditambahkan│");
        System.out.println("│    adalah hasil perkalian                      │");
        System.out.println("└────────────────────────────────────────────────┘");
        
        System.out.println("\nCONTOH: 18 × 25");
        System.out.println("+-------+-------+-------+-------------------+");
        System.out.println("|   a   |   b   | a % 2 |      Aksi         |");
        System.out.println("+-------+-------+-------+-------------------+");
        System.out.println("|   18  |   25  |  Genap| Skip              |");
        System.out.println("|   9   |   50  |  Ganjil| Tambah 50 (50)   |");
        System.out.println("|   4   |  100  |  Genap| Skip              |");
        System.out.println("|   2   |  200  |  Genap| Skip              |");
        System.out.println("|   1   |  400  |  Ganjil| Tambah 400 (450) |");
        System.out.println("+-------+-------+-------+-------------------+");
        System.out.println("Hasil: 50 + 400 = 450");
        System.out.println("Verifikasi: 18 × 25 = 450 ✓");
        
        System.out.println("\nKEUNTUNGAN:");
        System.out.println("✓ Hanya menggunakan pembagian 2, perkalian 2, dan penjumlahan");
        System.out.println("✓ Tidak perlu menghafal tabel perkalian");
        System.out.println("✓ Cocok untuk sistem bilangan biner");
        System.out.println("✓ Dasar dari algoritma perkalian komputer modern");
    }
    
    public static void contohPenggunaan(Scanner scanner) {
        System.out.println("\n=== CONTOH PENGGUNAAN PERKALIAN RUSIA ===");
        
        System.out.println("\nPilih contoh:");
        System.out.println("1. Contoh sederhana (12 × 13)");
        System.out.println("2. Contoh dengan bilangan besar (47 × 89)");
        System.out.println("3. Contoh perkalian dengan nol (0 × 25)");
        System.out.println("4. Contoh perkalian dengan satu (1 × 100)");
        System.out.print("Pilihan: ");
        
        int contoh = scanner.nextInt();
        
        switch(contoh) {
            case 1:
                jalankanContoh(12, 13);
                break;
            case 2:
                jalankanContoh(47, 89);
                break;
            case 3:
                jalankanContoh(0, 25);
                break;
            case 4:
                jalankanContoh(1, 100);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    public static void jalankanContoh(int a, int b) {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  CONTOH: " + a + " × " + b);
        System.out.println("══════════════════════════════════════════════════");
        
        if(a == 0) {
            System.out.println("\nKASUS KHUSUS: Perkalian dengan 0");
            System.out.println("Setiap bilangan dikali 0 hasilnya 0");
            System.out.println("Hasil: " + a + " × " + b + " = 0");
            return;
        }
        
        System.out.println("\nProses Perkalian Rusia:");
        System.out.println("+-------+-------+---------+--------+");
        System.out.println("|   a   |   b   | Status  | Total  |");
        System.out.println("+-------+-------+---------+--------+");
        
        int total = 0;
        int aTemp = a;
        int bTemp = b;
        
        while(aTemp > 0) {
            boolean ganjil = (aTemp % 2 == 1);
            String status = ganjil ? "Ganjil" : "Genap";
            
            if(ganjil) {
                total += bTemp;
                System.out.printf("| %5d | %5d | %7s | %6d | ← Tambah %d\n", 
                    aTemp, bTemp, status, total, bTemp);
            } else {
                System.out.printf("| %5d | %5d | %7s | %6d |\n", 
                    aTemp, bTemp, status, total);
            }
            
            aTemp = aTemp / 2;
            bTemp = bTemp * 2;
        }
        
        System.out.println("+-------+-------+---------+--------+");
        System.out.printf("Hasil Perkalian Rusia: %d × %d = %d\n", a, b, total);
        
        // Verifikasi
        int verifikasi = a * b;
        System.out.println("Verifikasi perkalian biasa: " + verifikasi);
        System.out.println("Status: " + (total == verifikasi ? "✓ BENAR" : "✗ SALAH"));
        
        // Perkalian × 2
        System.out.println("\nPerkalian × 2: " + total + " × 2 = " + (total * 2));
        System.out.println("Atau: " + a + " × " + b + " × 2 = " + (a * b * 2));
    }
    
    // Helper method untuk menghitung tanpa tampilan
    public static int hitungTanpaTampilan(int a, int b) {
        int hasil = 0;
        
        while(a > 0) {
            if(a % 2 == 1) {
                hasil += b;
            }
            a = a / 2;
            b = b * 2;
        }
        
        return hasil;
    }
}