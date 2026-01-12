import java.util.Scanner;
import java.util.Arrays;

public class exam18 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input jumlah data = ");
        int n = input.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        // Salin array untuk sorting
        int[] dataTerurut = data.clone();
        Arrays.sort(dataTerurut); // Menggunakan sorting bawaan Java (TimSort)

        // Tampilkan data asli dan data terurut
        System.out.print("(");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.print(") <--- Data Belum Terurut\n");

        System.out.print("(");
        for (int i = 0; i < n; i++) {
            System.out.print(dataTerurut[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println(") <--- Data Terurut");

        // Hitung Rata-rata
        double rataRata = hitungRataRata(data);

        // Hitung Median
        double median = hitungMedian(dataTerurut);

        // Nilai Max dan Min
        int max = dataTerurut[n - 1];
        int min = dataTerurut[0];

        // Range
        double range = max - min;

        // Output hasil
        System.out.printf("Rata - Rata%8s = %.1f\n", "", rataRata);
        System.out.printf("Median%12s = %.1f\n", "", median);
        System.out.printf("Nilai Max%9s = %d\n", "", max);
        System.out.printf("Nilai Min%9s = %d\n", "", min);
        System.out.printf("Range%13s = %.0f\n", "", range);

        input.close();
    }

    public static double hitungRataRata(int[] arr) {
        int total = 0;
        for (int nilai : arr) {
            total += nilai;
        }
        return (double) total / arr.length;
    }

    public static double hitungMedian(int[] arr) {
        int n = arr.length;
        if (n % 2 == 1) {
            // Jumlah ganjil: median adalah elemen tengah
            return arr[n / 2];
        } else {
            // Jumlah genap: rata-rata dua elemen tengah
            int tengah1 = arr[n / 2 - 1];
            int tengah2 = arr[n / 2];
            return (tengah1 + tengah2) / 2.0;
        }
    }
}