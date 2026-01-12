import java.util.Arrays;
import java.util.Scanner;

public class tugas1 {
    public static void insertionSort(int[] testArray) {
        for (int i = 1; i < testArray.length; i++) {
            int key = testArray[i];
            int j = i - 1;
            while (j >= 0 && testArray[j] < key) {
                testArray[j + 1] = testArray[j];
                j--;
            }
            testArray[j + 1] = key;
            System.out.println("Langkah Ke-" + i + " --> " + Arrays.toString(testArray));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan Banyak Angka: ");
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Masukan Data ke-" + (i + 1) + " = ");
            arr[i] = input.nextInt();
        }
        System.out.println("\nSebelum Sorting : ");
        System.out.println(Arrays.toString(arr));
        System.out.println("\n--- Proses Sorting ---");
        insertionSort(arr);
        System.out.println("\nSesudah Sorting : ");
        System.out.println(Arrays.toString(arr));
    }
}
