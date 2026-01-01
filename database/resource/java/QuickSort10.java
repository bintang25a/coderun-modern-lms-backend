import java.util.Arrays;
import java.util.Scanner;

public class QuickSortLomuto {

    // Fungsi untuk melakukan partition menggunakan Lomuto Partition Scheme
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Tukar arr[i] dan arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Tukar arr[i+1] dan arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Fungsi utama untuk menjalankan QuickSort
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Menampilkan array dan pivot setelah partition
            System.out.print("[");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println("](P: " + arr[pi] + " | R: " + arr[high] + ")");

            // Rekursif mengurutkan elemen sebelum dan sesudah partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta pengguna untuk memasukkan ukuran array
        System.out.print("Tentukan banyak data = ");
        int n = scanner.nextInt();

        // Meminta pengguna untuk memasukkan elemen array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan data ke - " + i + " = ");
            arr[i] = scanner.nextInt();
        }

        // Menampilkan array sebelum diurutkan
        System.out.print("Data Sebelum Disorting : [");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");

        // Memanggil fungsi quickSort
        quickSort(arr, 0, n - 1);

        // Menampilkan array setelah diurutkan
        System.out.print("Data Setelah Disorting : [");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");

        scanner.close();
    }
}
