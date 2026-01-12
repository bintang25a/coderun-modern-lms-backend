import java.util.Scanner;

public class merge_sort {

    // Fungsi untuk menggabungkan dua subarray yang sudah terurut
    public static void merge(int[] arr, int l, int m, int r) {

        // --- Base Case Print ---
        if (l == r) {
            System.out.println("Base Case (Terurut): [" + arr[l] + "]");
        }

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        // --- Print Proses Merge ---
        System.out.println("-> Menggabungkan (Merge):");
        System.out.print("   Kiri: [");
        for (int x = 0; x < n1; x++) {
            System.out.print(L[x]);
            if (x < n1 - 1) System.out.print(", ");
        }
        System.out.println("]");

        System.out.print("   Kanan: [");
        for (int x = 0; x < n2; x++) {
            System.out.print(R[x]);
            if (x < n2 - 1) System.out.print(", ");
        }
        System.out.println("]");

        int i = 0, j = 0;
        int k = l;

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

        // --- Print Hasil Merge ---
        System.out.print("   Hasil Merge: [");
        for (int x = l; x <= r; x++) {
            System.out.print(arr[x]);
            if (x < r) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Fungsi untuk membagi array
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {

            // --- Print proses Divide ---
            System.out.print("Membagi (Divide): [");
            for (int i = l; i <= r; i++) {
                System.out.print(arr[i]);
                if (i < r) System.out.print(", ");
            }
            System.out.println("]");

            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    // Fungsi mencetak array
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

        // Proses Sorting
        sort(arr, 0, arr.length - 1);

        System.out.println("\n--- Proses Selesai ---");
        System.out.println("Array terurut :");
        printArray(arr);
    }
}
