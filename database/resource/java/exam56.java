import java.util.Scanner;
import java.util.Arrays;

public class exam56 {

    public static void insertionSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("==== No 3 Muhamad Ramzy Pradipta (24040700054) ====\n");
        System.out.println("Program Statistika Deskriptif");

        System.out.print("Input jumlah data = ");
        int n = input.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        System.out.println("\n" + Arrays.toString(data) + " <--- Data Belum Terurut");

        insertionSort(data);

        System.out.println(Arrays.toString(data) + " <--- Data Terurut\n");

        double total = 0;
        for (int i = 0; i < n; i++) {
            total += data[i];
        }
        double rataRata = total / n;

        double median;
        if (n % 2 == 1) { // ganjil
            median = data[n / 2];
        } else { // genap
            median = (data[(n / 2) - 1] + data[n / 2]) / 2.0;
        }

        int min = data[0];
        int max = data[n - 1];
        int range = max - min;

        System.out.println("Rata - Rata = " + rataRata);
        System.out.println("Median     = " + median);
        System.out.println("Nilai Max  = " + max);
        System.out.println("Nilai Min  = " + min);
        System.out.println("Range      = " + range);
    }
}