import java.util.Scanner;

public class exam19 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Program Statistika Deskriptif ===");
        System.out.println("Punya Raihan Abi Nugroho");
         System.out.println();

         // --- 1. INPUT DATA DINAMIS ---
        System.out.print("Input jumlah data = ");
        int n = input.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        System.out.println();

        // --- 2. MENAMPILKAN DATA BELUM TERURUT ---
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("] <--- Data Belum Terurut");
        System.out.println();

        // --- 3. PROSES SORTING (BUBBLE SORT) ---
        // Algoritma untuk mengurutkan data dari kecil ke besar
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

        // --- 4. MENAMPILKAN DATA TERURUT --
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("] <--- Data Terurut");
        System.out.println();

        // --- 5. MENGHITUNG NILAI STATISTIKA ---
        // A. Hitung Rata-rata (Mean)
        double total = 0;
        for (int x : data) {
            total += x;
        }
        double rataRata = total / n;

        double median;
        if (n % 2 != 0) {
            median = data[n / 2];
        } else {
            median = (data[(n / 2) - 1] + data[n / 2]) / 2.0;
        }

        int nilaiMin = data[0];
        int nilaiMax = data[n - 1];
        int range = nilaiMax - nilaiMin;

        // --- 6. OUTPUT HASIL ---
        System.out.println("Rata - Rata    = " + rataRata);
        System.out.println("Median         = " + median);
        System.out.println("Nilai Max      = " + nilaiMax);
        System.out.println("Nilai Min      = " + nilaiMin);
        System.out.println("Range          = " + range);
    }
}   

