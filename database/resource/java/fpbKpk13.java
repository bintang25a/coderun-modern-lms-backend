public class fpbKpk13 {
   
    public static int hitungFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int hitungKPK(int a, int b) {
        // Karena input A dan B adalah bilangan positif (seperti 12 dan 25), 
        // kita tidak perlu khawatir tentang nilai absolut (|a * b|).
        return (a * b) / hitungFPB(a, b);
    }

    public static void main(String[] args) {
        // Deklarasi dan inisialisasi input sesuai gambar
        final int nilaiA = 12;
        final int nilaiB = 25;

        // Cetak bagian awal program
        System.out.println("run:");
        System.out.println("Program KPK dan FPB NAMA-NIM\n");
        
        // Cetak input nilai
        System.out.println("Input Nilai A = " + nilaiA);
        System.out.println("Input Nilai B = " + nilaiB);
        System.out.println(); // Baris kosong untuk pemisah

        // Hitung dan cetak hasil FPB
        int fpbResult = hitungFPB(nilaiA, nilaiB);
        System.out.println("Cetak Hasil FPB = " + fpbResult);

        // Hitung dan cetak hasil KPK
        int kpkResult = hitungKPK(nilaiA, nilaiB);
        System.out.println("\nCetak Hasil KPK = " + kpkResult);

        // Tambahkan baris akhir seperti pada output gambar
        System.out.println("\nBUILD SUCCESSFUL (total time: 3 seconds)");
    }
}

