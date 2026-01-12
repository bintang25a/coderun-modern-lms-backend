import java.util.Scanner;

public class exam31 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Program Statistika Deskriptif");
        System.out.println("==============================");

        System.out.print("Input jumlah data = ");
        int n = scanner.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = scanner.nextInt();
        }

        System.out.print("\n");
        tampilkanArray(data);
        System.out.println("<--- Data Belum Terurut");

        bubbleSort(data);

        tampilkanArray(data);
        System.out.println("<--- Data Terurut");

        double rataRata = hitungRataRata(data);
        double median = hitungMedian(data);
        int max = data[data.length - 1];           
        int min = data[0];                        
        int range = max - min;

        System.out.printf("Rata - Rata\t= %.1f\n", rataRata);
        System.out.printf("Median\t\t= %.1f\n", median);
        System.out.println("Nilai Max\t= " + max);
        System.out.println("Nilai Min\t= " + min);
        System.out.println("Range\t\t= " + range);

        System.out.println("\nBUILD SUCCESSFUL (total time: ... seconds)");

        scanner.close();
    }

    public static void tampilkanArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] ");
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // tukar
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static double hitungRataRata(int[] arr) {
        int sum = 0;
        for (int nilai : arr) {
            sum += nilai;
        }
        return (double) sum / arr.length;
    }

    public static double hitungMedian(int[] arr) {
        int n = arr.length;
        if (n % 2 == 1) {
            return arr[n / 2];
        } else {
            int tengah1 = arr[n / 2 - 1];
            int tengah2 = arr[n / 2];
            return (tengah1 + tengah2) / 2.0;
        }
    }
}