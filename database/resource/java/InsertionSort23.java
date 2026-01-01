public class InsertionSort {

    public static void main(String[] args) {
        int[] data = {6, 4, 1, 3, 5};

        System.out.println("=== Sebelum Insertion Sort ===");
        cetakArray(data);

        sortir(data);

        System.out.println("\n=== Hasil Akhir ===");
        cetakArray(data);
    }

    public static void sortir(int[] array) {
        int n = array.length;
        
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Logika inti Insertion Sort
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;

            // Log Iterasi (Opsional untuk tracing)
            System.out.printf("Iterasi %d [Key=%d]: ", i, key);
            cetakArray(array);
        }
    }

    public static void cetakArray(int[] array) {
        for (int nilai : array) {
            System.out.print(nilai + " ");
        }
        System.out.println();
    }
}