import java.util.Scanner;
import java.util.Arrays;

public class InsertionSort {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukan Banyak Angka : ");
        int n = input.nextInt();
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Masukan Data ke-" + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        System.out.println("\nSebelum Sorting :");
        System.out.println(Arrays.toString(data));
        System.out.println();

        for (int i = 0; i < n; i++) {
            int key = data[i];
            int j = i - 1;

            int ditukarDengan = key; 
            while (j >= 0 && data[j] < key) {
                ditukarDengan = data[j];
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;

            System.out.println("Langkah Ke-" + (i + 1) + " --> " + key + " ditukar dengan " + ditukarDengan);
        }

        System.out.println("\nSesudah Sorting :");
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println("]");
        
        input.close();
    }
}