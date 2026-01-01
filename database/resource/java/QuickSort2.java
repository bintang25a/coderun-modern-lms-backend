import java.util.Scanner;

public class QuickSortProgram {

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static void inputArray(int[] arr, int n, Scanner input) {
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan nilai elemen ke-" + (i + 1) + " : ");
            arr[i] = input.nextInt();
        }
    }

    static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println("\n");
    }

    static int partition(int[] arr, int low, int high, int n) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        System.out.println("Langkah setelah partisi dengan pivot " + arr[i + 1] + ":");
        printArray(arr);

        return i + 1;
    }

    static void quickSort(int[] arr, int low, int high, int n) {
        if (low < high) {
            int pi = partition(arr, low, high, n);

            quickSort(arr, low, pi - 1, n);
            quickSort(arr, pi + 1, high, n);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===========================");
        System.out.println("\tQuick Sort Program");
        System.out.println("===========================");

        System.out.print("Masukkan jumlah elemen dalam Array : ");
        int n = input.nextInt();

        int[] arr = new int[n];

        inputArray(arr, n, input);

        System.out.println("\nIsi Array sebelum diurutkan:");
        printArray(arr);

        quickSort(arr, 0, n - 1, n);

        System.out.println("Isi Array setelah diurutkan (Ascending):");
        printArray(arr);

        input.close();
    }
}
