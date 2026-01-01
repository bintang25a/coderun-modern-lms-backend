import java.util.Scanner;

public class QuickSortProgram {

    public static void inputArray(int[] arr, int n, Scanner sc) {
        for (int i = 0; i < n; i++) {
            System.out.print("Masukan Nilai Elemen ke-" + (i + 1) + " : ");
            arr[i] = sc.nextInt();
        }
    }

    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(int[] arr, int low, int high, int n) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        System.out.print("Langkah setelah partisi pivot " + arr[i + 1] + ": ");
        printArray(arr, n);

        return i + 1;
    }

    public static void quickSort(int[] arr, int low, int high, int n) {
        if (low < high) {
            int pi = partition(arr, low, high, n);
            quickSort(arr, low, pi - 1, n);
            quickSort(arr, pi + 1, high, n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("\tQuickSort Program");
        System.out.println("==============================");

        System.out.print("Masukan jumlah elemen dalam Array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        inputArray(arr, n, sc);

        System.out.println("==============================");
        System.out.println("Isi array sebelum diurutkan:");
        printArray(arr, n);

        quickSort(arr, 0, n - 1, n);

        System.out.println("Isi array setelah diurutkan:");
        printArray(arr, n);

        sc.close();
    }
}
