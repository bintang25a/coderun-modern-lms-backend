import java.util.Scanner;

public class QuickSortProgram {

    
    public static void swapValue(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swapValue(arr, i, j);
            }
        }

        swapValue(arr, i + 1, high);
        return i + 1;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            System.out.print("Langkah setelah partisi dengan pivot " + arr[pi] + ": ");
            printArray(arr);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("          Quick Sort Program");
        System.out.println("=======================================\n");

        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen dalam Array: ");
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan nilai elemen ke-" + (i + 1) + ": ");
            arr[i] = input.nextInt();
        }

        System.out.println("\nIsi Array sebelum diurutkan:");
        printArray(arr);

        quickSort(arr, 0, n - 1);

        System.out.println("\nIsi Array setelah diurutkan (Ascending):");
        printArray(arr);

        input.close();
    }
}
