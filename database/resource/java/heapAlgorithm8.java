import java.util.Arrays;
import java.util.Scanner;

public class HeapSort {

    public void sort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i; 
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PROGRAM HEAP SORT DINAMIS ===");
        System.out.print("Masukkan jumlah angka yang ingin diurutkan: ");
        
        int n = scanner.nextInt();
        
        int arr[] = new int[n];

        System.out.println("Masukkan " + n + " angka:");
        for (int i = 0; i < n; i++) {
            System.out.print("Angka ke-" + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }
        
        int[] initialArray = Arrays.copyOf(arr, n);

        System.out.println("\nArray Awal: " + Arrays.toString(initialArray));
        
        HeapSort ob = new HeapSort();
        
        int[] tempArrForMaxHeap = Arrays.copyOf(initialArray, n); 
        
        for (int i = tempArrForMaxHeap.length / 2 - 1; i >= 0; i--) {
            ob.heapify(tempArrForMaxHeap, tempArrForMaxHeap.length, i);
        }

        System.out.println("Setelah jadi Max-Heap: " + Arrays.toString(tempArrForMaxHeap));

        ob.sort(arr);

        System.out.println("Hasil Akhir (Sorted): " + Arrays.toString(arr));
        
        scanner.close();
    }
}