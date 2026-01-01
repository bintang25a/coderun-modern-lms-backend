public class QuickSortLomuto {
     public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Memilih elemen terakhir sebagai pivot
        int i = (low - 1); // Indeks elemen yang lebih kecil

        for (int j = low; j < high; j++) {
            // Jika elemen saat ini lebih kecil atau sama dengan pivot
            if (arr[j] <= pivot) {
                i++; // Increment indeks elemen yang lebih kecil
                // Tukar arr[i] dan arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Tukar arr[i + 1] dan arr[high] (atau pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Kembalikan indeks pivot
    }

    // Metode utama untuk menerapkan Quicksort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Temukan indeks pivot
            int pi = partition(arr, low, high);

            // Rekursif untuk bagian kiri dan kanan dari pivot
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Metode untuk mencetak array
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input ukuran array
        System.out.print("Masukkan ukuran array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        // Input elemen array
        System.out.println("Masukkan elemen array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Menampilkan array sebelum diurutkan
        System.out.println("Array sebelum diurutkan:");
        printArray(arr);

        // Mengurutkan array
        quickSort(arr, 0, n - 1);

        // Menampilkan array setelah diurutkan
        System.out.println("Array setelah diurutkan:");
        printArray(arr);

        scanner.close();
    }
    
}
