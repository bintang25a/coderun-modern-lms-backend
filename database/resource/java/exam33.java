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

        System.out.print("\n");
        tampilArray(data, "Data Belum Terurut");

        bubbleSort(data);

        tampilArray(data, "Data Terurut");

        double rata = hitungRata(data);
        double median = hitungMedian(data);
        int max = data[data.length - 1];
        int min = data[0];
        int range = max - min;

        System.out.println("\nRata - Rata     = " + rata);
        System.out.println("Median         = " + median);
        System.out.println("Nilai Max      = " + max);
        System.out.println("Nilai Min      = " + min);
        System.out.println("Range          = " + range);
    }

    static void bubbleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    static void tampilArray(int[] data, String keterangan) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] <--- " + keterangan);
    }

    static double hitungRata(int[] data) {
        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total += data[i];
        }
        return (double) total / data.length;
    }

    static double hitungMedian(int[] data) {
        int n = data.length;
        if (n % 2 == 1) {
            return data[n / 2];
        } else {
            return (data[(n / 2) - 1] + data[n / 2]) / 2.0;
        }
    }
}