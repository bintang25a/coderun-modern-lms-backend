import java.util.Arrays;
import java.util.Scanner; // Import library scanner

public class heapAlgorithm14 {
// --- Fungsi Bantuan Index ---
    public static int getParentIndex(int i) {
        return (i - 1) / 2;
    }
    public static int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }
    public static int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    // --- Fungsi Heapify ---
    public static void heapify(int[] array, int size, int i) {
        int largest = i; // inisialisasi largest sebagai root
        int left = getLeftChildIndex(i); 
        int right = getRightChildIndex(i); 

        // periksa apakah anak kiri lebih besar dari root
        if (left < size && array[left] > array[largest]) {
            largest = left;
        }
        // periksa apakah anak kanan lebih besar dari largest
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }
        // jika largest bukan root
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            // rekursif untuk subtree yg terkena dampak
            heapify(array, size, largest);
        }
    }

    // --- Fungsi Membangun Max Heap ---
    public static void buildMaxHeap(int[] array) { 
        int size = array.length;
        // dimulai dari element non daun terakhir
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Tampilan Header
        System.out.println("\n=== PROGRAM HEAP SORT DINAMIS Raihan Abi 24040700056 ===");
        
        // 2. Input Jumlah Angka
        System.out.print("Masukkan jumlah angka yang ingin diurutkan: ");
        int n = input.nextInt();
        
        int[] array = new int[n];

        // 3. Input Angka-angkanya
        System.out.println("Masukkan " + n + " angka:");
        for (int i = 0; i < n; i++) {
            System.out.print("Angka ke-" + (i + 1) + ": ");
            array[i] = input.nextInt();
        }

        // Garis Pembatas
        System.out.println("\n------------------------------");

        // 4. Cetak Array Awal
        System.out.println("Array Awal: " + Arrays.toString(array));

        // 5. Proses Membentuk Max-Heap
        buildMaxHeap(array);
        System.out.println("Setelah jadi Max-Heap: " + Arrays.toString(array));

        // 6. Proses Sorting (Ekstraksi elemen satu per satu)
        // (Logika ini sebelumnya ada di dalam fungsi heapSort, kita taruh sini agar bisa print step-stepnya)
        int size = array.length;
        for (int i = size - 1; i > 0; i--) {
            // pindahkan root element terbesar ke akhir array
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            
            // panggil heapify pada heap yang berkurang
            heapify(array, i, 0);
        }

        // 7. Cetak Hasil Akhir
        System.out.println("Hasil Akhir (Sorted): " + Arrays.toString(array));
        System.out.println("------------------------------");
    }
}

