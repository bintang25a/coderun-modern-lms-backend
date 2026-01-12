import java.util.Scanner;
import java.util.Arrays;

public class mergeSort25 { 
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Fungsi untuk mencetak subarray
    public static String arrayToString(int[] arr, int l, int r) {
        if (l > r) return "[]";
        
        int[] subArray = Arrays.copyOfRange(arr, l, r + 1);
        return Arrays.toString(subArray).replace(", ", ",");
    }

    // Fungsi untuk menggabungkan dua subarray yang sudah terurut
    public static void merge(int[] arr, int l, int m, int r) {
        System.out.println("-> Menggabungkan (Merge):");
        
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

        System.out.println("Kiri: " + Arrays.toString(L).replace(" ", ""));
        System.out.println("Kanan: " + Arrays.toString(R).replace(" ", ""));

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

        System.out.println("Hasil Merge: " + arrayToString(arr, l, r));
    }
    
    // Fungsi untuk membagi array, lalu memanggil merge
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            
            System.out.println("Membagi (Divide): " + arrayToString(arr, l, r));
            
            sort(arr, l, m);
            sort(arr, m + 1, r);
            
            merge(arr, l, m, r);
        } else {
            System.out.println("Base Case (Terurut): " + arrayToString(arr, l, r));
        }
    }

    // Fungsi utama
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int [] arr;
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
        printArray(arr);
        
        System.out.println("\n--- Memulai Proses Merge Sort ---");
        
        sort(arr, 0, arr.length - 1);
        
        System.out.println("\n--- Proses Selesai ---");
        System.out.println("Array terurut :");
        printArray(arr);
        input.close();
    }
}