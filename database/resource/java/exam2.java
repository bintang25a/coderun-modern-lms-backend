import java.util.Scanner;

public class exam2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input jumlah data = ");
        int n = sc.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = sc.nextInt();
        }
        System.out.println();
        tampilkanArray(data, "Data Belum Terurut");       
        int[] dataTerurut = data.clone();
        bubbleSort(dataTerurut);        
        tampilkanArray(dataTerurut, "Data Terurut");

        double rataRata = hitungRataRata(data);
        double median = hitungMedian(dataTerurut, n);
        int max = dataTerurut[n - 1];
        int min = dataTerurut[0];
        int range = max - min;
       
        System.out.printf("Rata - Rata%9s = %.1f%n", "", rataRata);
        System.out.printf("Median%13s = %.1f%n", "", median);
        System.out.printf("Nilai Max%10s = %d%n", "", max);
        System.out.printf("Nilai Min%10s = %d%n", "", min);
        System.out.printf("Range%14s = %d%n", "", range);

        sc.close();
    }    
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Tukar
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }    
    private static void tampilkanArray(int[] arr, String keterangan) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] <--- " + keterangan);
        System.out.println();
    }   
    private static double hitungRataRata(int[] data) {
        int sum = 0;
        for (int nilai : data) {
            sum += nilai;
        }
        return (double) sum / data.length;
    }
    private static double hitungMedian(int[] dataTerurut, int n) {
        if (n % 2 == 1) {
            
            return dataTerurut[n / 2];
        } else {
           
            int tengah1 = dataTerurut[n / 2 - 1];
            int tengah2 = dataTerurut[n / 2];
            return (tengah1 + tengah2) / 2.0;
        }
    }
}