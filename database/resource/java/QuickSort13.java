import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static int lomutoPartition(int[] A, int p, int r) {
        int pivot = A[r]; // Choosing the last element as pivot
        int i = p - 1; // Pointer for the smaller element

        for (int j = p; j < r; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r); // Place the pivot in the correct position
        return i + 1; // Return the index of the pivot
    }

    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            int pivotIndex = lomutoPartition(A, p, r);
            quicksort(A, p, pivotIndex - 1); // Sort elements before pivot
            quicksort(A, pivotIndex + 1, r); // Sort elements after pivot
        }
    }

    public static void main(String[] args) {
        int batas;

        Scanner input = new Scanner(System.in);

        // Input the number of elements in the array
        System.out.print("Tentukan banyak data = ");
        batas = input.nextInt();

        // Input data into the array
        int[] data = new int[batas];
        for (int i = 0; i < batas; i++) {
            System.out.print("Masukan data ke - " + i + " = ");
            data[i] = input.nextInt();
        }

        // Data Before Sorting
        System.out.println("Data Sebelum Disorting : " + Arrays.toString(data) + "\n");
        // Process data to be sorted with QuickSort algorithm
        quicksort(data, 0, data.length - 1);
        // Data After Sorting
        System.out.println("\nData Setelah Disorting : " + Arrays.toString(data));
    }
}