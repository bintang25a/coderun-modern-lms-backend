import java.util.Scanner;
import java.util.Arrays;

public class exam39 {

    public static void main(String[] args) {
        Scanner scanData = new Scanner(System.in);
        System.out.print("Input jumlah data: ");
        int n = scanData.nextInt();
        double[] dataArr = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            dataArr[i] = scanData.nextDouble();
        }

        System.out.println(Arrays.toString(dataArr) + " < Data Belum Terurut");

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (dataArr[j] > dataArr[j + 1]) {
                    double temp = dataArr[j];
                    dataArr[j] = dataArr[j + 1];
                    dataArr[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(dataArr) + " < Data Terurut");
        double sum = 0;
        for (double d : dataArr) sum += d;
        double rataRata = sum / n;

        double median;
        if (n % 2 == 0) {
            median = (dataArr[n / 2 - 1] + dataArr[n / 2]) / 2.0;
        } else {
            median = dataArr[n / 2];
        }

        double max = dataArr[n - 1];
        double min = dataArr[0];
        double range = max - min;

        System.out.println("Rata Rata = " + rataRata);
        System.out.println("Median = " + median);
        System.out.println("Nilai Max = " + (int)max);
        System.out.println("Nilai Min = " + (int)min);
        System.out.println("Range = " + (int)range);
    }
}

    