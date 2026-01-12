import java.util.Scanner;

public class HeapSort {

    static void heapify(int[] arr, int n, int i) {
        int terbesar = i;
        int kiri = 2 * i + 1;
        int kanan = 2 * i + 2;

        if (kiri < n && arr[kiri] > arr[terbesar])
            terbesar = kiri;

        if (kanan < n && arr[kanan] > arr[terbesar])
            terbesar = kanan;

        if (terbesar != i) {
            int temp = arr[i];
            arr[i] = arr[terbesar];
            arr[terbesar] = temp;

            heapify(arr, n, terbesar);
        }
    }

    static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    static void tampilArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("PROGRAM HEAP SORT");
        System.out.println("========================");
        System.out.print("Masukkan jumlah data : ");

        int n = input.nextInt();
        int[] data = new int[n];
        System.out.println("Masukkan data array:");
        for (int i = 0; i < n; i++) {
            System.out.print("Data ke-" + (i + 1) + " : ");
            data[i] = input.nextInt();
        }

        System.out.println("\nData sebelum diurutkan:");
        tampilArray(data);
        heapSort(data);
        System.out.println("\nData setelah diurutkan (Heap Sort):");
        tampilArray(data);

        input.close();
    }
}

