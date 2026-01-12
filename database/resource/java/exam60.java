import java.util.Scanner;
import java.util.Arrays;

public class soal3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Program Statistika Deskriptif");
        System.out.println();
        System.out.print("Input jumlah data = ");
        int n = sc.nextInt();

        int[] data = new int[n];
        double total = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = sc.nextInt();
            total += data[i];
        }

        System.out.println();
        System.out.println(Arrays.toString(data) + " <--- Data Belum Terurut");
        countingSort(data);
        System.out.println(Arrays.toString(data) + " <--- Data Terurut");
        System.out.println();
        double rataRata = total / n;
        double median;
        if (n % 2 == 0) {
            median = (data[n / 2 - 1] + data[n / 2]) / 2.0;
        } else {
            median = data[n / 2];
        }

        int nilaiMax = data[n - 1];
        int nilaiMin = data[0];
        int range = nilaiMax - nilaiMin;
        System.out.printf("Rata - Rata    = %.1f\n", rataRata);
        System.out.printf("Median         = %.1f\n", median);
        System.out.println("Nilai Max      = " + nilaiMax);
        System.out.println("Nilai Min      = " + nilaiMin);
        System.out.println("Range          = " + range);
        
        System.out.println("BUILD SUCCESSFUL");
    }
    public static void countingSort(int[] array) {
        if (array.length == 0) return;
        int max = array[0];
        for (int val : array) {
            if (val > max) max = val;
        }

        int[] count = new int[max + 1];
        for (int val : array) {
            count[val]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                array[index++] = i;
                count[i]--;
            }
        }
    }
}