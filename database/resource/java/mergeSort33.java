
import java.util.Scanner;
import java.util.Arrays;

public class mergeSort33 {

    public static void insertionDescending(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) { // descending: ubah < untuk membalik urutan
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;

            System.out.println("Langkah Ke-" + i + " ---> " + arr[i] + " ditukar dengan " + key);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukan Banyak Angka : ");
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Masukan Data ke-" + (i + 1) + " = ");
            arr[i] = input.nextInt();
        }

        System.out.println("\nSebelum Sorting : ");
        System.out.println(Arrays.toString(arr));

        System.out.println("\n--- Proses Sorting ---");
        insertionDescending(arr);

        System.out.println("\nSesudah Sorting : ");
        System.out.println(Arrays.toString(arr));
    }
}



