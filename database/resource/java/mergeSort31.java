import java.util.Scanner; 

public class Merge_sort { 
    
    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1; // Panjang subarray kiri
        int n2 = r - m; // Panjang subarray kanan

        
        int[] L = new int[n1];
        int[] R = new int[n2];

        
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        
        int i = 0, j = 0; 
        int k = l; 

        System.out.println("Menggabungkan subarray: ");
        System.out.print("Kiri: ");
        printArray(L);
        System.out.print("Kanan: ");
        printArray(R);

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

        System.out.print("Hasil penggabungan: ");
        printArray(arr, l, r);
    }

    
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            
            int m = l + (r - l) / 2;

            System.out.println("Membagi array menjadi dua bagian: ");
            System.out.print("Bagian kiri: ");
            printArray(arr, l, m);
            System.out.print("Bagian kanan: ");
            printArray(arr, m + 1, r);

            
            sort(arr, l, m);
            sort(arr, m + 1, r);

            
            merge(arr, l, m, r);
        }
    }

    
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    
    public static void printArray(int[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            System.out.print(arr[i] + " ");
        }
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
        printArray(arr);

        
        sort(arr, 0, arr.length - 1);

        System.out.println("\nArray terurut :");
        printArray(arr);
    }
}