import java.util.Scanner;

public class mergeSort37 {

    private static String sliceToString(int[] arr, int l, int r) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = l; i <= r; i++) {
            sb.append(arr[i]);
            if (i < r) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void merge(int[] arr, int l, int m, int r) {
        System.out.println("-> Menggabungkan (Merge):");
        System.out.println("   Kiri: " + sliceToString(arr, l, m));
        System.out.println("   Kanan: " + sliceToString(arr, m + 1, r));

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

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

        System.out.println("   Hasil Merge: " + sliceToString(arr, l, r));
    }

    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            System.out.println("Membagi (Divide): " + sliceToString(arr, l, r));
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        } else {
            System.out.println("Base Case (Terurut): " + sliceToString(arr, l, r));
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }

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
        for (int i : arr) System.out.print(i + " ");
        System.out.println();

        System.out.println("\n--- Memulai Proses Merge Sort ---");
        sort(arr, 0, arr.length - 1);

        System.out.println("\n--- Proses Selesai ---");
        System.out.println("Array terurut :");
        printArray(arr);
    }
}
