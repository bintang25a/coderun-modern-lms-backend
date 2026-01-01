public class insertionsort {
    static void cetakArray(int[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }
    public static void insertionSort(int[] testArray) {
        for (int i = 1; i < testArray.length; i++) {
            int key = testArray[i];
            int j = i - 1;

            // Geser elemen yang lebih besar ke kanan dan cetak pergeseran
            while (j >= 0 && testArray[j] > key) {
                System.out.println("Iterasi ke-" + i + " = " + testArray[j] + " Ditukar dengan " + key);
                testArray[j + 1] = testArray[j];
                j--;
            }
            testArray[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        int[] myArray = {6, 4, 1, 3, 5};
        System.out.println("Array sebelum sorting:");
        cetakArray(myArray);
        insertionSort(myArray);
        System.out.println("\nArray setelah sorting:");
        cetakArray(myArray);
    }
}
