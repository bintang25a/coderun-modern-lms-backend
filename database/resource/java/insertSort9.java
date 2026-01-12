import java.util.Arrays;
import java.util.Scanner;

public class insertion_short_raden {
    public static void insertionDesc(int[] testArray) {
        for (int i = 1; i < testArray.length; i++) {
            int j = i - 1;
            int key = testArray[i];

            while (j >= 0 && testArray[j] < key) {
                testArray[j + 1] = testArray[j];
                j--;
            }
            testArray[j + 1] = key;

            System.out.println("Langkah " + i + " (Menyisipkan " + key + "): " + Arrays.toString(testArray));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen array: ");
        int n = input.nextInt();
        int[] arr = new int[n];

        System.out.println("Masukkan elemen-elemen array:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemen ke-" + (i + 1) + ": ");
            arr[i] = input.nextInt();
        }

        System.out.println("\nSebelum Sorting : ");
        System.out.println(Arrays.toString(arr));
        System.out.println("\n--- Proses Sorting (Descending) ---");

        insertionDesc(arr);

        System.out.println("\n--- Hasil Sorting ---");
        System.out.println("Sesudah Sorting (Descending): \n" + Arrays.toString(arr));

        input.close();
    }
}
