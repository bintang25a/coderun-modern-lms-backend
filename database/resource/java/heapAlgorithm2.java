import java.util.Arrays;
import java.util.Scanner;

public class HeapMax {

    public static int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    public static int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    public static int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    public static void heapify(int[] array, int size, int i) {
        int largest = i;
        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);

        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            heapify(array, size, largest);
        }
    }

    public static void buildMaxHeap(int[] array) {
        int size = array.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
        }
    }

    public static void heapSort(int[] array) {
        int size = array.length;

        buildMaxHeap(array);

        for (int i = size - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== PROGRAM HEAP SORT DINAMIS ===");

        System.out.print("Masukkan jumlah angka yang ingin diurutkan: ");
        int n = input.nextInt();

        int[] array = new int[n];

        System.out.println("Masukkan " + n + " angka:");
        for (int i = 0; i < n; i++) {
            System.out.print("Angka ke-" + (i + 1) + ": ");
            array[i] = input.nextInt();
        }

        System.out.println("\n----------------------------------");
        System.out.println("Array Awal: " + Arrays.toString(array));

        buildMaxHeap(array);
        System.out.println("Setelah jadi Max-Heap: " + Arrays.toString(array));

        heapSort(array);
        System.out.println("Hasil Akhir (Sorted): " + Arrays.toString(array));
        System.out.println("----------------------------------");
    }
}
