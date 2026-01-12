import java.util.Arrays;
import java.util.Scanner;

public class Tugas1 {
    public static void insertion(int[] testArray) {
        for (int i = 1; i < testArray.length; i++) {
            int j = i - 1;
            int key = testArray[i];

            while (j >= 0 && testArray[j] > key) {
                testArray[j + 1] = testArray[j];
                j--;
            }
            testArray[j + 1] = key;

            System.out.println("Langkah " + i + " (ditukar " + key + "): " + Arrays.toString(testArray));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan banyak angka: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.print("Masukkan data ke-" + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }

        System.out.println("\nSebelum Sorting : ");
        System.out.println(Arrays.toString(arr));
        System.out.println("\n--- Proses Sorting ---");

        insertion(arr);

        System.out.println("\n--- Hasil Sorting ---");
        System.out.println("Sesudah Sorting : \n" + Arrays.toString(arr));

        scanner.close();
    }
}