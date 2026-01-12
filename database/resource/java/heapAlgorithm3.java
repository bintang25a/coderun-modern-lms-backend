import java.util.Scanner;

public class HeapMax {

    public static void heapify(int arr[], int n, int i) {
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
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    public static void heapSort(int arr[]) {
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

    public static void printArray(int arr[]) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== PROGRAM HEAP SORT DINAMIS ===");
        System.out.print("Masukkan jumlah angka yang ingin diurutkan: ");
        int n = input.nextInt();

        int[] arr = new int[n];

        System.out.println("Masukkan " + n + " angka:");
        for (int i = 0; i < n; i++) {
            System.out.print("Angka ke-" + (i + 1) + ": ");
            arr[i] = input.nextInt();
        }

        System.out.println("\n---------------------------------------");
        System.out.print("Array Awal: ");
        printArray(arr);
        System.out.println();

        int[] heapArr = arr.clone();
        for (int i = heapArr.length / 2 - 1; i >= 0; i--) {
            heapify(heapArr, heapArr.length, i);
        }
        System.out.print("Setelah jadi Max-Heap: ");
        printArray(heapArr);
        System.out.println();

        heapSort(arr);
        System.out.print("Hasil Akhir (Sorted): ");
        printArray(arr);
        System.out.println("\n---------------------------------------");
    }
}
