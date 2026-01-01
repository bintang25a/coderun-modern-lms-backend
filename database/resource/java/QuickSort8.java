import java.util.Scanner;

public class QuickSortLomuto {
    
    // Fungsi untuk melakukan partition menggunakan Lomuto Partition
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Pilih elemen terakhir sebagai pivot
        int i = low - 1; // Indeks elemen lebih kecil
        
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                // Tukar array[i] dan array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Tukar array[i+1] dengan pivot
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1; // Kembalikan indeks pivot
    }
    
    // Fungsi QuickSort
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1); // Rekursi ke kiri
            quickSort(array, pivotIndex + 1, high); // Rekursi ke kanan
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input jumlah elemen array
        System.out.print("Masukkan jumlah elemen array: ");
        int n = scanner.nextInt();
        
        // Input elemen array
        int[] array = new int[n];
        System.out.println("Masukkan elemen array:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemen ke-" + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
        
        // Tampilkan array sebelum disortir
        System.out.println("Array sebelum disortir:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Panggil QuickSort
        quickSort(array, 0, n - 1);
        
        // Tampilkan array setelah disortir
        System.out.println("Array setelah disortir:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        scanner.close();
    }
}
