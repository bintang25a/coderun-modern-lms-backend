// NAMA : FATHONI ADAM ILYASA
// NIM  : 24040700060
import java.util.Scanner;

public class exam28 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Program Statistika Deskriptif");
        System.out.print("Input jumlah data = ");
        int n = sc.nextInt();

        int[] data = new int[n];

        // Input data
        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = sc.nextInt();
        }

        // Tampilkan data belum terurut
        System.out.print("\nData Belum Terurut: [");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("]");

        // Sorting dengan Insertion Sort
        for (int i = 1; i < n; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }

        // Tampilkan data terurut
        System.out.print("Data Terurut: [");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("]");

        // Hitung rata-rata
        double total = 0;
        for (int i = 0; i < n; i++) {
            total += data[i];
        }
        double rata = total / n;

        // Hitung median
        double median;
        if (n % 2 == 1) {
            median = data[n / 2];
        } else {
            median = (data[n / 2 - 1] + data[n / 2]) / 2.0;
        }

        // Nilai max dan min
        int min = data[0];
        int max = data[n - 1];
        int range = max - min;

        // Cetak hasil
        System.out.println("\nRata - Rata     = " + rata);
        System.out.println("Median          = " + median);
        System.out.println("Nilai Max       = " + max);
        System.out.println("Nilai Min       = " + min);
        System.out.println("Range           = " + range);

        sc.close();
    }
}
