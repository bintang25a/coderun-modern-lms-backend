

import java.util.Arrays;
import java.util.Scanner;


public class SOAL3 {

     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program Statistika Deskriptif\n");

        
        System.out.print("Input jumlah data = ");
        int n = input.nextInt();
        int[] data = new int[n];
        int[] dataOriginal = new int[n];

        
        double total = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextInt();
            dataOriginal[i] = data[i]; // Menyimpan data asli untuk ditampilkan nanti
            total += data[i];
        }

        
        for (int i = 1; i < n; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }

        
        System.out.println("\n" + Arrays.toString(dataOriginal) + " <--- Data Belum Terurut");
        System.out.println(Arrays.toString(data) + " <--- Data Terurut\n");

        
        // Rata-rata
        double rataRata = total / n;

        // Median
        double median;
        if (n % 2 == 0) {
            // Jika genap, rata-rata dari dua nilai tengah
            median = (double) (data[n / 2 - 1] + data[n / 2]) / 2;
        } else {
            // Jika ganjil, ambil nilai tengah secara langsung
            median = (double) data[n / 2];
        }

        // Max, Min, dan Range
        int nilaiMax = data[n - 1]; // Karena sudah terurut, max ada di akhir
        int nilaiMin = data[0];     // Karena sudah terurut, min ada di awal
        int range = nilaiMax - nilaiMin;

       
        System.out.printf("Rata - Rata     = %.1f\n", rataRata);
        System.out.printf("Median          = %.1f\n", median);
        System.out.println("Nilai Max       = " + nilaiMax);
        System.out.println("Nilai Min       = " + nilaiMin);
        System.out.println("Range           = " + range);
    }
}
   