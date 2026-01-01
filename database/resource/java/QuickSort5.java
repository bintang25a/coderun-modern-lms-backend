import java.util.Scanner;

public class QuickSortProgram {

    static void inputArray(int[] arr, int n, Scanner scan) {
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan nilai elemen ke-" + (i + 1) + " : ");
            arr[i] = scan.nextInt();
        }
    }

    static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
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

        System.out.print("Langkah setelah partisi dengan pivot " + arr[i + 1] + ": ");
        printArray(arr, n);

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

        Scanner scan = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("\tQuick Sort Program");
        System.out.println("=================================");

        System.out.print("\nMasukkan jumlah elemen dalam Array: ");
        int n = scan.nextInt();
        System.out.println();

        int[] arr = new int[n];

        inputArray(arr, n, scan);

        System.out.println("\nIsi Array sebelum diurutkan:");
        printArray(arr, n);

        quickSort(arr, 0, n - 1, n);

        System.out.println("Isi Array setelah diurutkan (Ascending):");
        printArray(arr, n);

        scan.close();
    }
}
