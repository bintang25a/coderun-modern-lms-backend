import java.util.Arrays;
import java.util.Scanner;


public class Merge_sort {
    public static String formatArray(int[] arr) {
        return Arrays.toString(arr);
    }
    public static void merge(int[] arr, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

       
        System.out.println("    -> Menggabungkan (Merge):");
        System.out.println("       Kiri:  " + formatArray(L));
        System.out.println("       Kanan: " + formatArray(R));

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            arr[k] = (L[i] <= R[j]) ? L[i++] : R[j++];
            k++;
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];

        
        int[] hasil = Arrays.copyOfRange(arr, l, r + 1);
        System.out.println("       Hasil Merge: " + formatArray(hasil));
    }

   
    public static void sort(int[] arr, int l, int r) {

        if (l < r) {
            int m = l + (r - l) / 2;

        
            int[] bagian = Arrays.copyOfRange(arr, l, r + 1);
            System.out.println("Membagi (Divide): " + formatArray(bagian));

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        } else {
          
            int[] base = { arr[l] };
            System.out.println("Base Case (Terurut): " + formatArray(base));
        }
    }

    
    public static void printArray(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int bil;
        System.out.print("Masukkan Angka : ");
        bil = input.nextInt();

        int[] arr = new int[bil];

        for (int i = 0; i < bil; i++) {
            System.out.print("Masukan Data ke-" + (i + 1) + " = ");
            arr[i] = input.nextInt();
        }

        System.out.println("\nArray Belum Terurut : ");
        printArray(arr);

        System.out.println("\n--- Memulai Proses Merge Sort ---");

        sort(arr, 0, arr.length - 1);

        System.out.println("\n--- Proses Selesai ---");
        System.out.println("Array terurut :");
        printArray(arr);
    }
}
