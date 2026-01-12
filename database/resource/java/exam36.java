import java.util.Scanner;
import java.util.Arrays;

public class exam36 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("  Program Statistika Deskriptif ");
        System.out.println("-------------------------------");

        // 1. Input jumlah data
        System.out.print("Masukkan jumlah data: ");
        int n = input.nextInt();

        // LOGIKA VALIDASI (Sesuai permintaan Anda)
        // Contoh: Jika Anda ingin mendemonstrasikan kasus ganjil/genap secara spesifik
        System.out.println("Pilih Jenis Input:");
        System.out.println("1. Input Harus Ganjil");
        System.out.println("2. Input Harus Genap");
        System.out.print("Pilihan: ");
        int jenis = input.nextInt();

        if (jenis == 1 && n % 2 == 0) {
            System.out.println("Error: Anda memilih Ganjil tapi jumlah data Genap. Program Berhenti (Break)!");
            return; // Berhenti
        } else if (jenis == 2 && n % 2 != 0) {
            System.out.println("Error: Anda memilih Genap tapi jumlah data Ganjil. Program Berhenti (Break)!");
            return; // Berhenti
        }

        // 2. Input data array
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        // Cetak data asli
        System.out.print("\n" + Arrays.toString(data) + " <--- Data Belum Terurut\n");

        // 3. Algoritma Sorting (Bubble Sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

        // Cetak data setelah urut
        System.out.print(Arrays.toString(data) + " <--- Data Terurut\n\n");

        // 4. Perhitungan Statistik
        double sum = 0;
        for (int nilai : data) sum += nilai;
        double rataRata = sum / n;

        // Logika Median Otomatis
        double median;
        if (n % 2 != 0) {
            median = (double) data[n / 2];
        } else {
            median = (double) (data[(n / 2) - 1] + data[n / 2]) / 2.0;
        }

        int nilaiMax = data[n - 1];
        int nilaiMin = data[0];
        int range = nilaiMax - nilaiMin;

        // 5. Output Sesuai Format Gambar
        System.out.printf("Rata - Rata     = %.1f\n", rataRata);
        System.out.printf("Median          = %.1f\n", median);
        System.out.println("Nilai Max       = " + nilaiMax);
        System.out.println("Nilai Min       = " + nilaiMin);
        System.out.println("Range           = " + range);
        
        System.out.println("\nBUILD SUCCESSFUL");
    }
}