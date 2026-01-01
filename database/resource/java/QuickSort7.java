import java.util.Scanner;
public class TugasLomuto {

     public static int lomutoPartition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Pilih elemen terakhir sebagai pivot
        int i = low - 1;  // Indeks untuk elemen yang lebih kecil dari pivot

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {  // Jika elemen lebih kecil atau sama dengan pivot
                i++;
                // Tukar arr[i] dan arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Tukar pivot ke posisi yang benar
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;  // Kembalikan indeks pivot
    }

    // Fungsi untuk mengurutkan array menggunakan QuickSort
    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = lomutoPartition(arr, low, high);  // Tentukan posisi pivot
            quicksort(arr, low, pi - 1);  // Sortir elemen di kiri pivot
            quicksort(arr, pi + 1, high);  // Sortir elemen di kanan pivot
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input jumlah elemen array
        System.out.print("Masukkan jumlah elemen array: ");
        int n = scanner.nextInt();

        // Array untuk menyimpan elemen
        int[] arr = new int[n];

        // Input elemen-elemen array
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan elemen ke-" + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }

        // Menampilkan array sebelum diurutkan
        System.out.println("Array sebelum diurutkan:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Mengurutkan array menggunakan QuickSort
        quicksort(arr, 0, arr.length - 1);

        // Menampilkan array setelah diurutkan
        System.out.println("Array setelah diurutkan:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        scanner.close();
    }
}
