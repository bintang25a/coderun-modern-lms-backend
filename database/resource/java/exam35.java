import java.util.Arrays;
import java.util.Scanner;

public class StatistikaDeskriptif {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program Statistika Deskriptif\n");

        System.out.print("Input jumlah data = ");
        int n = input.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        // Tampilkan data belum terurut
        System.out.println();
        System.out.println(Arrays.toString(data) + " <--- Data Belum Terurut");

        // Urutkan data
        Arrays.sort(data);
        System.out.println(Arrays.toString(data) + " <--- Data Terurut\n");

        // Hitung rata-rata
        double total = 0;
        for (int i = 0; i < n; i++) {
            total += data[i];
        }
        double rataRata = total / n;

        // Hitung median
        double median;
        if (n % 2 == 1) {
            median = data[n / 2];
        } else {
            median = (data[(n / 2) - 1] + data[n / 2]) / 2.0;
        }

        // Nilai max, min, range
        int min = data[0];
        int max = data[n - 1];
        int range = max - min;

        // Output hasil
        System.out.println("Rata - Rata    = " + rataRata);
        System.out.println("Median         = " + median);
        System.out.println("Nilai Max      = " + max);
        System.out.println("Nilai Min      = " + min);
        System.out.println("Range          = " + range);
    }
}
