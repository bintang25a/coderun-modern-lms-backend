import java.util.Arrays;
import java.util.Scanner;

public class mergeSort40 {

    // Fungsi untuk menggabungkan dua subarray yang sudah terurut
    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1; // Panjang subarray kiri
        int n2 = r - m;     // Panjang subarray kanan

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        // Tambahan: tampilkan proses penggabungan
        System.out.println("-> Menggabungkan (Merge):");
        System.out.println("   Kiri : " + Arrays.toString(L));
        System.out.println("   Kanan: " + Arrays.toString(R));

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        // Menampilkan hasil setelah merge selesai
        int[] hasil = Arrays.copyOfRange(arr, l, r + 1);
        System.out.println("   Hasil Merge: " + Arrays.toString(hasil));
    }

    // Fungsi untuk membagi array, lalu memanggil merge
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            // Tambahan: tampilkan proses pembagian
            System.out.println("Membagi (Divide): " + Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)));

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        } else {
            // Tambahan: tampilkan base case
            int[] base = Arrays.copyOfRange(arr, l, r + 1);
            System.out.println("Base Case (Terurut): " + Arrays.toString(base));
        }
    }

    // Fungsi untuk mencetak array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Fungsi utama
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr;
        int bil;

        System.out.print("Masukkan Angka : ");
        bil = input.nextInt();
        System.out.println();

        arr = new int[bil];
        for (int i = 0; i < bil; i++) {
            System.out.print("Masukan Data ke-" + (i + 1) + " = ");
            arr[i] = input.nextInt();
        }

        System.out.println("\nArray Belum Terurut : ");
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println("\n\n--- Memulai Proses Merge Sort ---");
        sort(arr, 0, arr.length - 1);

        System.out.println("\n--- Proses Selesai ---");
        System.out.println("Array terurut :");
        printArray(arr);
    }
}
