import java.util.Arrays; // Import Arrays class untuk mencetak array
import java.util.Scanner; // Import Scanner class

public class QuickSort {

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static int lomutoPartition(int[] A, int p, int r) {
        int pivot = A[r]; // Pivot diambil dari elemen terakhir
        int i = p - 1; // Indeks elemen yang lebih kecil dari pivot

        for (int j = p; j < r; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r); // Menempatkan pivot di posisi yang benar
        return i + 1; // Mengembalikan indeks pivot
    }

    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            int pivot = lomutoPartition(A, p, r);
            quicksort(A, p, pivot - 1); // Sorting bagian kiri pivot
            quicksort(A, pivot + 1, r); // Sorting bagian kanan pivot
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Menginput jumlah elemen array
        System.out.print("Tentukan banyak data = ");
        int batas = input.nextInt();

        // Menginput data pada array
        int[] data = new int[batas];
        for (int i = 0; i < batas; i++) {
            System.out.print("Masukan data ke-" + i + " = ");
            data[i] = input.nextInt();
        }

        // Data sebelum diurutkan
        System.out.println("Data Sebelum Disorting: " + Arrays.toString(data) + "\n");

        // Proses data diurutkan dengan algoritma Quick Sort
        quicksort(data, 0, data.length - 1);

        // Data setelah diurutkan
        System.out.println("\nData Setelah Disorting: " + Arrays.toString(data));

        input.close(); // Menutup Scanner untuk menghindari kebocoran resource
    }
}
