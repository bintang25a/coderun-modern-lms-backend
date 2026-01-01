import java.util.Scanner;

public class QuickSortProgram {
    
    // Fungsi swap
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Fungsi partition
    static int partition(int[] arr, int low, int high, int n) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        
        System.out.print("Langkah setelah partisi dengan pivot " + pivot + ": ");
        for (int k = 0; k < n; k++) {
            System.out.print(arr[k] + " ");
        }
        System.out.println();
        
        return i + 1;
    }

    // Fungsi quickSort
    static void quickSort(int[] arr, int low, int high, int n) {
        if (low < high) {
            int pi = partition(arr, low, high, n);
            quickSort(arr, low, pi - 1, n);
            quickSort(arr, pi + 1, high, n);
        }
    }
    
    // Fungsi input array
    static void inputArray(int[] arr, int n, Scanner sc) {
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan nilai elemen ke-" + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }
    }
    
    // Fungsi print array
    static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    // Fungsi main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=============================");
        System.out.println("      Program QuickSort");
        System.out.println("=============================\n");
        
        System.out.print("Masukkan jumlah elemen dalam array: ");
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        
        inputArray(arr, n, sc);
        
        System.out.print("Isi array sebelum diurutkan: ");
        printArray(arr, n);
        
        quickSort(arr, 0, n - 1, n);
        
        System.out.print("Isi array setelah diurutkan: ");
        printArray(arr, n);
        
        sc.close();
    }
}