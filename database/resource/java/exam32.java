import java.util.Arrays;
import java.util.Scanner;

public class StatistikaDeskriptif {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program Statistika Deskriptif");
        System.out.println();

        System.out.print("Input jumlah data = ");
        int n = input.nextInt();
        double[] data = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextDouble();
        }

        System.out.println("\n" + formatArray(data) + " <--- Data Belum Terurut");

        bubbleSort(data);

        System.out.println(formatArray(data) + " <--- Data Terurut\n");


        double total = 0;
        for (double d : data) total += d;
        double rataRata = total / n;

        double median;
        if (n % 2 != 0) {

            median = data[n / 2];
        } else {

            median = (data[(n / 2) - 1] + data[n / 2]) / 2.0;
        }

        double nilaiMax = data[n - 1];
        double nilaiMin = data[0];

        double range = nilaiMax - nilaiMin;

        System.out.printf("Rata - Rata    = %.1f\n", rataRata);
        System.out.printf("Median         = %.1f\n", median);
        System.out.println("Nilai Max      = " + (int)nilaiMax);
        System.out.println("Nilai Min      = " + (int)nilaiMin);
        System.out.println("Range          = " + (int)range);
    }

    public static void bubbleSort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static String formatArray(double[] arr) {
        String res = "[";
        for (int i = 0; i < arr.length; i++) {
            res += (int)arr[i] + (i == arr.length - 1 ? "" : ", ");
        }
        return res + "]";
    }
}