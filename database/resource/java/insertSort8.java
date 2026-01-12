import java.util.Arrays;
import java.util.Scanner;

public class TugasPertemuan1 {

    
    public static void insertionDesc(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

           
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;

           
            System.out.println("Langkah Ke-" + i + " --> " + key + " ditukar dengan " + arr[j + 1]);
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Masukan Banyak Angka : ");
            int n = input.nextInt();
            int[] arr = new int[n];

            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print("Masukan Data ke-" + (i + 1) + " = ");
                arr[i] = input.nextInt();
            }

            System.out.println("\nSebelum Sorting : ");
            System.out.println(Arrays.toString(arr));

            System.out.println();
            insertionDesc(arr);

            System.out.println("\nSesudah Sorting : ");
            
            
            System.out.print("[ ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("]");
        }
    }
}
