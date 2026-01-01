public class insertionsort {
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;

            System.out.print("Iterasi ke-" + i + ": ");
            printArray(array);
        }
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 8, 6, 2, 7};
        
        System.out.println("Array sebelum disortir:");
        printArray(data);

        System.out.println("\nProses iterasi:");
        insertionSort(data);

        System.out.println("\nArray setelah disortir:");
        printArray(data);
    }
}
