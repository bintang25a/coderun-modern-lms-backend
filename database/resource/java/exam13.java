import java.util.Scanner;
import java.util.InputMismatchException;

public class exam13 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        
        do {
            tampilkanMenu();
            System.out.print("Pilih menu (1-6): ");
            
            try {
                pilihan = scanner.nextInt();
                
                switch(pilihan) {
                    case 1:
                        hitungFPBEuclidean(scanner);
                        break;
                    case 2:
                        hitungFPBSubtraction(scanner);
                        break;
                    case 3:
                        hitungFPBPrimeFactorization(scanner);
                        break;
                    case 4:
                        hitungFPBDenganKPK(scanner);
                        break;
                    case 5:
                        jelaskanAlgoritma();
                        break;
                    case 6:
                        contohPenggunaan(scanner);
                        break;
                    case 7:
                        System.out.println("\nTerima kasih telah menggunakan program FPB!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid! Silakan pilih 1-7.");
                }
                
                if(pilihan != 7) {
                    System.out.println("\nTekan Enter untuk kembali ke menu...");
                    scanner.nextLine(); // Clear buffer
                    scanner.nextLine(); // Tunggu Enter
                }
                
            } catch(InputMismatchException e) {
                System.out.println("Input harus angka!");
                scanner.next(); // Clear invalid input
                pilihan = 0;
            }
            
        } while(pilihan != 7);
        
        scanner.close();
    }
    
    public static void tampilkanMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         MENU FPB (FPB)                  ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Hitung FPB (Algoritma Euclidean)      ║");
        System.out.println("║ 2. Hitung FPB (Pengurangan Berulang)     ║");
        System.out.println("║ 3. Hitung FPB (Faktorisasi Prima)        ║");
        System.out.println("║ 4. Hitung FPB dan KPK                    ║");
        System.out.println("║ 5. Penjelasan Algoritma FPB              ║");
        System.out.println("║ 6. Contoh Penggunaan                     ║");
        System.out.println("║ 7. Keluar                                ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
    
    public static void hitungFPBEuclidean(Scanner scanner) {
        System.out.println("\n=== FPB DENGAN ALGORITMA EUCLIDEAN ===");
        
        System.out.print("Masukkan bilangan pertama: ");
        int a = scanner.nextInt();
        
        System.out.print("Masukkan bilangan kedua: ");
        int b = scanner.nextInt();
        
        // Simpan nilai asli untuk display
        int aAsli = a;
        int bAsli = b;
        
        System.out.println("\n┌────────────────────────────────────────────┐");
        System.out.println("│     PROSES ALGORITMA EUCLIDEAN           │");
        System.out.println("├──────────┬──────────┬──────────┬──────────┤");
        System.out.println("│     a    │     b    │ a mod b  │   a = b  │");
        System.out.println("│          │          │          │   b = r  │");
        System.out.println("├──────────┼──────────┼──────────┼──────────┤");
        
        int langkah = 1;
        int r;
        
        while(b != 0) {
            r = a % b;
            System.out.printf("│ %8d │ %8d │ %8d │ a = %d   │\n", 
                a, b, r, b);
            System.out.printf("│          │          │          │ b = %d   │\n", r);
            
            if(langkah > 1) {
                System.out.println("├──────────┼──────────┼──────────┼──────────┤");
            }
            
            a = b;
            b = r;
            langkah++;
        }
        
        System.out.println("└──────────┴──────────┴──────────┴──────────┘");
        System.out.printf("\nFPB(%d, %d) = %d\n", aAsli, bAsli, a);
        
        // Tampilkan hubungan FPB
        System.out.println("\nVerifikasi:");
        System.out.printf("%d ÷ %d = %d sisa %d\n", 
            aAsli, a, aAsli/a, aAsli%a);
        System.out.printf("%d ÷ %d = %d sisa %d\n", 
            bAsli, a, bAsli/a, bAsli%a);
        
        if(aAsli % a == 0 && bAsli % a == 0) {
            System.out.println("✓ " + a + " adalah faktor pembagi dari kedua bilangan");
        }
    }
    
    public static void hitungFPBSubtraction(Scanner scanner) {
        System.out.println("\n=== FPB DENGAN METODE PENGURANGAN BERULANG ===");
        
        System.out.print("Masukkan bilangan pertama: ");
        int a = scanner.nextInt();
        
        System.out.print("Masukkan bilangan kedua: ");
        int b = scanner.nextInt();
        
        int aAsli = a;
        int bAsli = b;
        
        System.out.println("\n┌────────────────────────────────────────────┐");
        System.out.println("│   PROSES PENGURANGAN BERULANG             │");
        System.out.println("├──────────┬──────────┬──────────┬──────────┤");
        System.out.println("│     a    │     b    │ a - b    │  a > b?  │");
        System.out.println("├──────────┼──────────┼──────────┼──────────┤");
        
        int langkah = 1;
        
        while(a != b) {
            System.out.printf("│ %8d │ %8d │ ", a, b);
            
            if(a > b) {
                System.out.printf("%8d │ a = %-5d │\n", a-b, a-b);
                a = a - b;
            } else {
                System.out.printf("%8d │ b = %-5d │\n", b-a, b-a);
                b = b - a;
            }
            
            langkah++;
        }
        
        System.out.println("├──────────┼──────────┼──────────┼──────────┤");
        System.out.printf("│ %8d │ %8d │   SAMA   │   SELESAI │\n", a, b);
        System.out.println("└──────────┴──────────┴──────────┴──────────┘");
        
        System.out.printf("\nFPB(%d, %d) = %d\n", aAsli, bAsli, a);
        System.out.println("Jumlah langkah: " + (langkah-1));
    }
    
    public static void hitungFPBPrimeFactorization(Scanner scanner) {
        System.out.println("\n=== FPB DENGAN FAKTORISASI PRIMA ===");
        
        System.out.print("Masukkan bilangan pertama: ");
        int a = scanner.nextInt();
        
        System.out.print("Masukkan bilangan kedua: ");
        int b = scanner.nextInt();
        
        int aAsli = a;
        int bAsli = b;
        
        System.out.println("\nFaktorisasi prima dari " + a + ":");
        String faktorA = faktorisasiPrima(a);
        System.out.println(faktorA);
        
        System.out.println("\nFaktorisasi prima dari " + b + ":");
        String faktorB = faktorisasiPrima(b);
        System.out.println(faktorB);
        
        // Hitung FPB dari faktorisasi prima
        int fpb = hitungFPBDariFaktorisasi(a, b);
        
        System.out.println("\n┌────────────────────────────────────────────┐");
        System.out.println("│   MENCARI FAKTOR PERSEKUTUAN             │");
        System.out.println("├────────────────────────────────────────────┤");
        
        // Tampilkan faktor bersama
        System.out.println("│ Faktor bersama: " + 
            tampilkanFaktorBersama(a, b) + " │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.printf("│ FPB(%d, %d) = %-28d│\n", aAsli, bAsli, fpb);
        System.out.println("└────────────────────────────────────────────┘");
    }
    
    public static void hitungFPBDenganKPK(Scanner scanner) {
        System.out.println("\n=== HITUNG FPB DAN KPK ===");
        
        System.out.print("Masukkan bilangan pertama: ");
        int a = scanner.nextInt();
        
        System.out.print("Masukkan bilangan kedua: ");
        int b = scanner.nextInt();
        
        int aAsli = a;
        int bAsli = b;
        
        // Hitung FPB menggunakan Euclidean
        int tempA = a;
        int tempB = b;
        while(tempB != 0) {
            int temp = tempB;
            tempB = tempA % tempB;
            tempA = temp;
        }
        int fpb = tempA;
        
        // Hitung KPK menggunakan rumus: KPK(a,b) = (a × b) / FPB(a,b)
        int kpk = (a * b) / fpb;
        
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║            HASIL PERHITUNGAN              ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.printf("║ Bilangan 1: %-32d║\n", a);
        System.out.printf("║ Bilangan 2: %-32d║\n", b);
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.printf("║ FPB(%d, %d) = %-30d║\n", a, b, fpb);
        System.out.printf("║ KPK(%d, %d) = %-30d║\n", a, b, kpk);
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║            VERIFIKASI                     ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.printf("║ %d ÷ %d = %d (habis dibagi)              ║\n", 
            a, fpb, a/fpb);
        System.out.printf("║ %d ÷ %d = %d (habis dibagi)              ║\n", 
            b, fpb, b/fpb);
        System.out.printf("║ %d ÷ %d = %d (habis dibagi)              ║\n", 
            kpk, a, kpk/a);
        System.out.printf("║ %d ÷ %d = %d (habis dibagi)              ║\n", 
            kpk, b, kpk/b);
        System.out.println("╚════════════════════════════════════════════╝");
        
        System.out.println("\nHubungan FPB dan KPK:");
        System.out.printf("FPB(%d, %d) × KPK(%d, %d) = %d × %d = %d\n", 
            a, b, a, b, fpb, kpk, fpb * kpk);
        System.out.printf("%d × %d = %d\n", a, b, a * b);
        System.out.println("Terbukti: FPB × KPK = a × b");
    }
    
    public static void jelaskanAlgoritma() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("          PENJELASAN ALGORITMA FPB");
        System.out.println("══════════════════════════════════════════════════");
        
        System.out.println("\nFPB (Faktor Persekutuan Terbesar) adalah bilangan");
        System.out.println("terbesar yang dapat membagi dua bilangan tanpa sisa.");
        
        System.out.println("\n┌────────────────────────────────────────────────┐");
        System.out.println("│           METODE-METODE MENCARI FPB           │");
        System.out.println("├────────────────────────────────────────────────┤");
        System.out.println("│ 1. ALGORITMA EUCLIDEAN                        │");
        System.out.println("│    - Bagi bilangan besar dengan kecil         │");
        System.out.println("│    - Ganti bilangan besar dengan sisa         │");
        System.out.println("│    - Ulangi hingga sisa = 0                   │");
        System.out.println("│    - FPB = bilangan terakhir sebelum 0        │");
        System.out.println("│                                                │");
        System.out.println("│ 2. METODE PENGURANGAN BERULANG                │");
        System.out.println("│    - Kurangi bilangan besar dengan kecil      │");
        System.out.println("│    - Ulangi hingga kedua bilangan sama        │");
        System.out.println("│    - Nilai yang sama adalah FPB               │");
        System.out.println("│                                                │");
        System.out.println("│ 3. FAKTORISASI PRIMA                          │");
        System.out.println("│    - Faktorkan kedua bilangan                 │");
        System.out.println("│    - Ambil faktor prima yang sama             │");
        System.out.println("│    - Kalikan faktor bersama dengan pangkat    │");
        System.out.println("│      terkecil                                 │");
        System.out.println("└────────────────────────────────────────────────┘");
        
        System.out.println("\nCONTOH: Mencari FPB(48, 36)");
        System.out.println("Faktor 48: 1, 2, 3, 4, 6, 8, 12, 16, 24, 48");
        System.out.println("Faktor 36: 1, 2, 3, 4, 6, 9, 12, 18, 36");
        System.out.println("Faktor bersama: 1, 2, 3, 4, 6, 12");
        System.out.println("Faktor terbesar: 12");
        System.out.println("Jadi, FPB(48, 36) = 12");
        
        System.out.println("\nMANFAAT FPB:");
        System.out.println("✓ Menyederhanakan pecahan");
        System.out.println("✓ Menyelesaikan masalah pembagian");
        System.out.println("✓ Mencari KPK (KPK = (a×b)/FPB)");
        System.out.println("✓ Dalam kriptografi dan matematika diskrit");
    }
    
    public static void contohPenggunaan(Scanner scanner) {
        System.out.println("\n=== CONTOH PENGGUNAAN FPB ===");
        
        System.out.println("\nPilih contoh:");
        System.out.println("1. Contoh sederhana (12, 18)");
        System.out.println("2. Contoh bilangan prima (17, 23)");
        System.out.println("3. Contoh bilangan besar (56, 98)");
        System.out.println("4. Contoh dengan nol (0, 15)");
        System.out.println("5. Contoh bilangan sama (24, 24)");
        System.out.print("Pilihan: ");
        
        int contoh = scanner.nextInt();
        
        switch(contoh) {
            case 1:
                jalankanContoh(12, 18);
                break;
            case 2:
                jalankanContoh(17, 23);
                break;
            case 3:
                jalankanContoh(56, 98);
                break;
            case 4:
                jalankanContoh(0, 15);
                break;
            case 5:
                jalankanContoh(24, 24);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    public static void jalankanContoh(int a, int b) {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  CONTOH: FPB(" + a + ", " + b + ")");
        System.out.println("══════════════════════════════════════════════════");
        
        if(a == 0 || b == 0) {
            System.out.println("\nKASUS KHUSUS: FPB dengan 0");
            int bukanNol = (a == 0) ? b : a;
            System.out.println("FPB(" + a + ", " + b + ") = " + bukanNol);
            System.out.println("Alasan: Setiap bilangan membagi 0");
            return;
        }
        
        if(a == b) {
            System.out.println("\nKASUS KHUSUS: Bilangan sama");
            System.out.println("FPB(" + a + ", " + b + ") = " + a);
            System.out.println("Alasan: Bilangan terbesar yang membagi " + a);
            System.out.println("       adalah " + a + " itu sendiri");
            return;
        }
        
        // Tampilkan semua faktor
        System.out.println("\nFaktor-faktor dari " + a + ":");
        System.out.println(tampilkanFaktor(a));
        
        System.out.println("\nFaktor-faktor dari " + b + ":");
        System.out.println(tampilkanFaktor(b));
        
        // Hitung FPB dengan Euclidean
        int tempA = a;
        int tempB = b;
        int langkah = 1;
        
        System.out.println("\nProses Euclidean:");
        System.out.println("+---------+---------+---------+---------+");
        System.out.println("| Langkah |    a    |    b    | a mod b |");
        System.out.println("+---------+---------+---------+---------+");
        
        while(tempB != 0) {
            int sisa = tempA % tempB;
            System.out.printf("| %7d | %7d | %7d | %7d |\n", 
                langkah, tempA, tempB, sisa);
            tempA = tempB;
            tempB = sisa;
            langkah++;
        }
        
        System.out.println("+---------+---------+---------+---------+");
        System.out.println("FPB(" + a + ", " + b + ") = " + tempA);
        
        // Verifikasi
        System.out.println("\nVerifikasi:");
        System.out.printf("%d ÷ %d = %d sisa %d\n", 
            a, tempA, a/tempA, a%tempA);
        System.out.printf("%d ÷ %d = %d sisa %d\n", 
            b, tempA, b/tempA, b%tempA);
        
        if(a % tempA == 0 && b % tempA == 0) {
            System.out.println("✓ " + tempA + " membagi habis kedua bilangan");
        }
        
        // Aplikasi: Menyederhanakan pecahan
        if(a > 0 && b > 0) {
            System.out.println("\nAplikasi: Menyederhanakan pecahan");
            System.out.printf("%d/%d = (%d÷%d)/(%d÷%d) = %d/%d\n", 
                a, b, a, tempA, b, tempA, a/tempA, b/tempA);
        }
    }
    
    // ============ HELPER METHODS ============
    
    public static String faktorisasiPrima(int n) {
        if(n <= 1) return n + " = " + n;
        
        StringBuilder result = new StringBuilder();
        result.append(n).append(" = ");
        
        int original = n;
        int divisor = 2;
        boolean first = true;
        
        while(n > 1) {
            int count = 0;
            while(n % divisor == 0) {
                n /= divisor;
                count++;
            }
            
            if(count > 0) {
                if(!first) result.append(" × ");
                result.append(divisor);
                if(count > 1) result.append("^").append(count);
                first = false;
            }
            
            divisor++;
            if(divisor * divisor > original && n > 1) {
                if(!first) result.append(" × ");
                result.append(n);
                break;
            }
        }
        
        return result.toString();
    }
    
    public static int hitungFPBDariFaktorisasi(int a, int b) {
        int fpb = 1;
        int divisor = 2;
        
        while(a > 1 && b > 1) {
            int countA = 0;
            int countB = 0;
            
            while(a % divisor == 0) {
                a /= divisor;
                countA++;
            }
            
            while(b % divisor == 0) {
                b /= divisor;
                countB++;
            }
            
            if(countA > 0 && countB > 0) {
                fpb *= Math.pow(divisor, Math.min(countA, countB));
            }
            
            divisor++;
        }
        
        return fpb;
    }
    
    public static String tampilkanFaktorBersama(int a, int b) {
        StringBuilder result = new StringBuilder();
        int fpb = hitungFPBDariFaktorisasi(a, b);
        
        // Cari semua faktor dari FPB
        for(int i = 1; i <= fpb; i++) {
            if(fpb % i == 0) {
                if(result.length() > 0) result.append(", ");
                result.append(i);
            }
        }
        
        return result.toString();
    }
    
    public static String tampilkanFaktor(int n) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        
        for(int i = 1; i <= n; i++) {
            if(n % i == 0) {
                if(count > 0) result.append(", ");
                result.append(i);
                count++;
                
                if(count % 5 == 0) result.append("\n");
            }
        }
        
        return result.toString();
    }
}