import java.util.Scanner;

public class insertionsort {

public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;

            System.out.print("Iterasi " + i + ": ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen array: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan elemen ke-" + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }

        System.out.println("Array sebelum diurutkan:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        insertionSort(arr);

        System.out.println("Array setelah diurutkan:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        scanner.close();
    }
}