public class InsertionSortStatic {
    public static void cetakArray() {
        int[] myArray = {6, 4, 1, 2, 8}; // Array tetap
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort() {
        int[] myArray = {6, 4, 1, 2, 8}; // Array tetap
        for (int i = 1; i < myArray.length; i++) {
            int j = i - 1;
            int key = myArray[i];

            while (j >= 0 && myArray[j] > key) {
                myArray[j + 1] = myArray[j];
                j--;
            }

            myArray[j + 1] = key;
        }

        // Cetak array setelah diurutkan
        System.out.print("Array urut = ");
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Cetak array awal
        System.out.print("Array acak = ");
        cetakArray();

        // Lakukan sorting
        insertionSort();
    }
}
