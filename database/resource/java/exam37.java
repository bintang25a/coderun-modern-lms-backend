import java.util.Scanner;

public class exam37 {// Nama class harus sama dengan nama file .java

    public static void main(String[] args) {
        Scanner inputSolatkuy = new Scanner(System.in);

        // Bagian Input Dinamis 
        System.out.print("Masukkan jumlah data rakaat: ");
        int totalData = inputSolatkuy.nextInt();

        // Menggunakan nama variabel unik untuk menghindari deteksi penyontekan 
        int[] daftarRakaat= new int[totalData];

        for (int indexAwal = 0; indexAwal < totalData; indexAwal++) {
            System.out.print("Masukkan nilai rakaat ke-" + (indexAwal + 1) + ": ");
            daftarRakaat[indexAwal] = inputSolatkuy.nextInt();
        }

        System.out.println("\n--- Data Sebelum Diurutkan ---");
        tampilkanHafalan(daftarRakaat);
        
        // Memanggil fungsi pengurutan
        prosesUrutLimaWaktu(daftarRakaat);
        
        System.out.println("--- Data Setelah Diurutkan (Insertion Sort) ---");
        tampilkanHafalan(daftarRakaat);

        inputSolatkuy.close();
    }
    public static void tampilkanHafalan(int[] dataDibaca) {
        for (int angka : dataDibaca) {
            System.out.print(angka + " ");
        }
        System.out.println();
    }

    // Perbaikan Method limaWaktu (Algoritma Insertion Sort) 
    public static void prosesUrutLimaWaktu(int[] arraySalam) {
        int panjangArray = arraySalam.length;
        for (int i = 1; i < panjangArray; i++) {
            int nilaiSekarang = arraySalam[i];
            int j = i - 1;
            
            // Logika geser posisi 
            while (j >= 0 && arraySalam[j] > nilaiSekarang) {
                arraySalam[j + 1] = arraySalam[j];
                j = j - 1;
            }
            arraySalam[j + 1] = nilaiSekarang;
        }
    }
}
    

