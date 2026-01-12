import java.util.Arrays;
import java.util.Scanner;


public class heapAlgorithm15 {
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        System.out.println("Setelah jadi Max-Heap: " + Arrays.toString(arr));
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    public static void heapify(int[] arr, int n, int i) {
        int largest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PROGRAM HEAP SORT DINAMIS ===");
        
        System.out.print("Masukkan jumlah angka yang ingin diurutkan: ");
        int n = 0;
        try {
            n = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Input tidak valid. Harap masukkan bilangan bulat.");
            scanner.close();
            return;
        }

        int[] arr = new int[n];
        System.out.println("Masukkan " + n + " angka:");

        for (int i = 0; i < n; i++) {
            System.out.print("Angka ke-" + (i + 1) + ": ");
            try {
                arr[i] = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Input tidak valid. Harap masukkan bilangan bulat.");
                scanner.close();
                return;
            }
        }
        System.out.println("\n-------------------------------------");
        System.out.println("Array Awal: " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("Hasil Akhir (Sorted): " + Arrays.toString(arr));
        System.out.println("\nBUILD SUCCESSFUL"); 
        scanner.close();
    } 
}