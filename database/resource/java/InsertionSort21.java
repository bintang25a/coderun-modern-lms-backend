public class insertionsort {

 
    public static void cetakArray(int[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] testArray) {
        for (int i = 1; i < testArray.length; i++) {
            int key = testArray[i];
            int j = i - 1;

            System.out.println("Iterasi " + i + " - Key: " + key);
            System.out.print("Array sebelum pengurutan: ");
            cetakArray(testArray);

            while (j >= 0 && testArray[j] > key) {
                testArray[j + 1] = testArray[j];
                j--;
            }
            testArray[j + 1] = key;

            System.out.print("Array setelah pengurutan: ");
            cetakArray(testArray);
        }
    }

    public static void main(String[] args) {
        int[] myArray = {6, 4, 1, 3, 5};

        System.out.print("Array sebelum diurutkan: ");
        cetakArray(myArray);

        insertionSort(myArray);

        System.out.print("Array setelah diurutkan: ");
        cetakArray(myArray);
    }
}
