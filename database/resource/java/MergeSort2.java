import java.util.Scanner;
public class tugasarraystatis {

    public static void main(String[] args) {
     
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen: ");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.println("Masukkan elemen:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemen ke-" + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        System.out.println("Array sebelum diurutkan:");
        printArray(array);

        // Proses Merge Sort
        mergeSort(array, 0, n - 1);

        System.out.println("Array setelah diurutkan:");
        printArray(array);

        scanner.close();
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Rekursi untuk membagi array
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Menggabungkan hasil
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Membuat array sementara
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Menyalin data ke array sementara
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        // Menggabungkan array sementara
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Menyalin sisa elemen
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
