public class insertionsort {
    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 3, 5};

        System.out.println("Array sebelum sorting:");
        printArray(arr);

        insertionSort(arr);

        System.out.println("\nArray setelah sorting:");
        printArray(arr);
    }

    static void insertionSort(int array[]) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;

            System.out.println("Iterasi ke-" + i + " = " + key + " Ditukar dengan " + array[j + 1]);
        }
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    
}
