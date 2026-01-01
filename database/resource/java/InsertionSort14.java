public class InsertionSort {
    public static void cetakArray(int[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] A) {
        for (int i = 1; i < A.length; i++) { 
            int j = i - 1;
            int key = A[i];

            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }

            A[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] myArray = {6, 4, 1, 2, 8};

        System.out.println("Array acak:");
        cetakArray(myArray);

        insertionSort(myArray);

        System.out.println("Array urut:");
        cetakArray(myArray);
    }
}